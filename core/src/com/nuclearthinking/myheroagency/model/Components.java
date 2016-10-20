package com.nuclearthinking.myheroagency.model;

import com.badlogic.ashley.core.ComponentMapper;
import com.nuclearthinking.myheroagency.model.components.hud.HudComponent;
import com.nuclearthinking.myheroagency.model.components.hud.PlayerHudComponent;
import com.nuclearthinking.myheroagency.model.components.hud.StatHudComponent;
import com.nuclearthinking.myheroagency.model.components.hud.UtilsHudComponent;

/**
 * Created by mkuksin on 02.09.2016.
 */
public final class Components {
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.components.AnimationComponent> ANIMATION = ComponentMapper.getFor(com.nuclearthinking.myheroagency.model.components.AnimationComponent.class);
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.components.CameraComponent> CAMERA = ComponentMapper.getFor(com.nuclearthinking.myheroagency.model.components.CameraComponent.class);
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.components.GravityComponent> GRAVITY = ComponentMapper.getFor(com.nuclearthinking.myheroagency.model.components.GravityComponent.class);
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.components.MapComponent> MAP = ComponentMapper.getFor(com.nuclearthinking.myheroagency.model.components.MapComponent.class);
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.components.MovementComponent> MOVEMENT = ComponentMapper.getFor(com.nuclearthinking.myheroagency.model.components.MovementComponent.class);
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.components.PlayerComponent> PLAYER = ComponentMapper.getFor(com.nuclearthinking.myheroagency.model.components.PlayerComponent.class);
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.components.NpcComponent> NPC = ComponentMapper.getFor(com.nuclearthinking.myheroagency.model.components.NpcComponent.class);
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.components.MonsterComponent> MONSTER = ComponentMapper.getFor(com.nuclearthinking.myheroagency.model.components.MonsterComponent.class);
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.components.BodyComponent> BODY = ComponentMapper.getFor(com.nuclearthinking.myheroagency.model.components.BodyComponent.class);
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.components.StateComponent> STATE = ComponentMapper.getFor(com.nuclearthinking.myheroagency.model.components.StateComponent.class);
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.components.TextureComponent> TEXTURE = ComponentMapper.getFor(com.nuclearthinking.myheroagency.model.components.TextureComponent.class);
    public static final ComponentMapper<com.nuclearthinking.myheroagency.model.components.LightComponent> LIGHT = ComponentMapper.getFor(com.nuclearthinking.myheroagency.model.components.LightComponent.class);
    public static final ComponentMapper<HudComponent> HUD = ComponentMapper.getFor(HudComponent.class);
    public static final ComponentMapper<UtilsHudComponent> UHC = ComponentMapper.getFor(UtilsHudComponent.class);
    public static final ComponentMapper<PlayerHudComponent> PHC = ComponentMapper.getFor(PlayerHudComponent.class);
    public static final ComponentMapper<StatHudComponent> SHC = ComponentMapper.getFor(StatHudComponent.class);
}
