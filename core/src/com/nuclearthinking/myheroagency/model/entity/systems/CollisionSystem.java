package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.nuclearthinking.myheroagency.model.entity.Components;
import com.nuclearthinking.myheroagency.model.entity.GameWorld;
import com.nuclearthinking.myheroagency.model.entity.components.*;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 02.09.2016.
 */
public class CollisionSystem extends EntitySystem {
    private Engine engine;
    private GameWorld gameWorld;

    private ImmutableArray<Entity> players;
    private ImmutableArray<Entity> npc;

    public CollisionSystem(@NonNull final GameWorld gameWorld){
        this.gameWorld = gameWorld;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;

        players = engine.getEntitiesFor(Family.all(PlayerComponent.class, BoundComponent.class, TransformComponent.class, StateComponent.class).get());
        npc = engine.getEntitiesFor(Family.all(NpcComponent.class, BoundComponent.class, TransformComponent.class, StateComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        @NonNull val npcSystem = engine.getSystem(NpcSystem.class);

        for (int i = 0; i < players.size(); ++i) {
            @NonNull val player = players.get(i);
            @NonNull val playerBounds = Components.BOUND.get(player);

            for (int j = 0; j < npc.size(); ++j) {
                @NonNull val _npc = npc.get(j);
                @NonNull val npcBounds = Components.BOUND.get(_npc);

                if (playerBounds.getBounds().overlaps(npcBounds.getBounds())) {
                    npcSystem.dialog();
                    break;
                }
            }
        }
    }

}
