package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.actor.Player;
import com.nuclearthinking.myheroagency.model.entity.AnimationState;
import com.nuclearthinking.myheroagency.model.entity.World;
import com.nuclearthinking.myheroagency.model.entity.components.MovementComponent;
import com.nuclearthinking.myheroagency.model.entity.components.PlayerComponent;
import com.nuclearthinking.myheroagency.model.entity.components.StateComponent;
import com.nuclearthinking.myheroagency.model.entity.components.TransformComponent;
import com.nuclearthinking.myheroagency.model.skills.Calculator;
import com.nuclearthinking.myheroagency.model.skills.Env;
import com.nuclearthinking.myheroagency.model.skills.Function;
import com.nuclearthinking.myheroagency.model.skills.Stats;
import com.nuclearthinking.myheroagency.model.skills.funcs.Func;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class PlayerSystem extends IteratingSystem {
    private static final Family family = Family.all(StateComponent.class,
                                                    TransformComponent.class,
                                                    MovementComponent.class).get();

    private @Setter float accelX = 0.0f;

    private ComponentMapper<StateComponent> sm;
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<MovementComponent> mm;

    private final Calculator[] _calculators;

    public PlayerSystem() {
        super(family);

        sm = ComponentMapper.getFor(StateComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);

        _calculators = new Calculator[Stats.NUM_STATS];

        Function.addFuncToChar(this);
    }

    public final void addStatFunc(@NonNull final Func f) {
        val stat = f.getStat().ordinal();
        synchronized (_calculators) {
            if(_calculators[stat] == null)
                _calculators[stat] = new Calculator(f.getStat(), this);

            _calculators[stat].addFunc(f);
        }
    }

    public final double calcStat(@NonNull final Stats stat, final double init){
        val id = stat.ordinal();
        val calculator = _calculators[id];

        if(calculator == null || calculator.size() == 0)
            return init;

        val env = new Env(this);
        env.setValue(init);

        calculator.calculate(env);

        return env.getValue();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        accelX = 0.0f;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        val t = tm.get(entity);
        val state = sm.get(entity);
        val mov = mm.get(entity);

        if(accelX == 0.0f){
            mov.getVelocity().x = 0;
            state.set(AnimationState.IDLE.getValue());
        }

        if (accelX > 0.0f && state.getState() != AnimationState.RIGHT.getValue()) {
            mov.getVelocity().x = getSpeed();
            state.set(AnimationState.RIGHT.getValue());
        }

        if (accelX < 0.0f && state.getState() != AnimationState.LEFT.getValue()) {
            mov.getVelocity().x = -getSpeed();
            state.set(AnimationState.LEFT.getValue());
        }

        if (t.getPos().x <= World.WORLD_BORDER_L) {
            t.getPos().x = World.WORLD_BORDER_L;
        }

        if (t.getPos().x > World.WORLD_BORDER_R) {
            t.getPos().x = World.WORLD_BORDER_R;
        }
    }

    public int getDEX(){
        return PlayerComponent.getInstance().getBaseDEX();
    }

    public int getCON(){
        return PlayerComponent.getInstance().getBaseCON();
    }

    public int getMEN(){
        return PlayerComponent.getInstance().getBaseMEN();
    }


    public int getSpeed(){
        return (int) calcStat(Stats.RUN_SPEED, PlayerComponent.getInstance().getBaseRunSpd());
    }

    public boolean isPlayer(){
        return true;
    }

}
