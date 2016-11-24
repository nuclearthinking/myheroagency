package com.nuclearthinking.myheroagency.model;

import com.badlogic.ashley.core.ComponentMapper;
import com.nuclearthinking.myheroagency.model.actor.base.*;
import com.nuclearthinking.myheroagency.model.actor.monster.MonsterComponent;
import com.nuclearthinking.myheroagency.model.actor.npc.NameComponent;
import com.nuclearthinking.myheroagency.model.actor.npc.NpcComponent;
import com.nuclearthinking.myheroagency.model.actor.player.PlayerComponent;
import com.nuclearthinking.myheroagency.model.effect.LightComponent;
import com.nuclearthinking.myheroagency.model.ui.hud.HudComponent;
import com.nuclearthinking.myheroagency.model.ui.hud.PlayerHudComponent;
import com.nuclearthinking.myheroagency.model.ui.hud.StatHudComponent;
import com.nuclearthinking.myheroagency.model.ui.hud.UtilsHudComponent;

/**
 * Created by mkuksin on 02.09.2016.
 *
 * ComponentMapper - выполняется за O(1)
 * Одиночный вызов например AnimationComponent velocity = entity.getComponent(AnimationComponent.class);
 * выполнится за O(log n) что существенно дольше.
 */
public final class Components {

    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.actor.base.AnimationComponent> ANIMATION = ComponentMapper.getFor(AnimationComponent.class);
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.actor.player.CameraComponent> CAMERA = ComponentMapper.getFor(com.nuclearthinking.myheroagency.model.actor.player.CameraComponent.class);
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.world.GravityComponent> GRAVITY = ComponentMapper.getFor(com.nuclearthinking.myheroagency.model.world.GravityComponent.class);
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.world.MapComponent> MAP = ComponentMapper.getFor(com.nuclearthinking.myheroagency.model.world.MapComponent.class);
    public static final ComponentMapper<MovementComponent> MOVEMENT = ComponentMapper.getFor(MovementComponent.class);
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.actor.player.PlayerComponent> PLAYER = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.actor.npc.NpcComponent> NPC = ComponentMapper.getFor(NpcComponent.class);
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.actor.monster.MonsterComponent> MONSTER = ComponentMapper.getFor(MonsterComponent.class);
    public static final ComponentMapper<BodyComponent> BODY = ComponentMapper.getFor(BodyComponent.class);
    public static final ComponentMapper<StateComponent> STATE = ComponentMapper.getFor(StateComponent.class);
    public static final ComponentMapper<TextureComponent> TEXTURE = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<LightComponent> LIGHT = ComponentMapper.getFor(LightComponent.class);
    public static final ComponentMapper<HudComponent> HUD = ComponentMapper.getFor(HudComponent.class);
    public static final ComponentMapper<UtilsHudComponent> UHC = ComponentMapper.getFor(UtilsHudComponent.class);
    public static final ComponentMapper<PlayerHudComponent> PHC = ComponentMapper.getFor(PlayerHudComponent.class);
    public static final ComponentMapper<StatHudComponent> SHC = ComponentMapper.getFor(StatHudComponent.class);
    public static final ComponentMapper<NameComponent> NAME = ComponentMapper.getFor(NameComponent.class);
}
