package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.nuclearthinking.myheroagency.model.entity.World;
import com.nuclearthinking.myheroagency.model.entity.components.*;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 02.09.2016.
 */
public class CollisionSystem extends EntitySystem {
    private Engine engine;
    private World world;

    private ImmutableArray<Entity> players;
    private ImmutableArray<Entity> npc;

    public CollisionSystem(@NonNull final World world){
        this.world = world;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;

        players = engine.getEntitiesFor(Family.all(PlayerComponent.class, BoundComponent.class, TransformComponent.class, StateComponent.class).get());
        npc = engine.getEntitiesFor(Family.all(NpcComponent.class, BoundComponent.class, TransformComponent.class, StateComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        val playerSystem = engine.getSystem(PlayerSystem.class);
        val npcSystem = engine.getSystem(NpcSystem.class);

        for (int i = 0; i < players.size(); ++i) {
            val player = players.get(i);

            val playerBounds = Components.BOUND.get(player);
            val playerPos = Components.TRANSFORM.get(player);

            for (int j = 0; j < npc.size(); ++j) {
                val _npc = npc.get(j);
                val npcPos = Components.TRANSFORM.get(_npc);

                if (playerPos.getPos().x + 5 > npcPos.getPos().x) {
                    val npcBounds = Components.BOUND.get(_npc);

                    if (playerBounds.getBounds().overlaps(npcBounds.getBounds())) {
                        npcSystem.dialog();
                        playerSystem.setAccelX(0.0f);
                        break;
                    }
                }
            }
        }
    }

}
