package com.nuclearthinking.myheroagency.model;

import com.badlogic.ashley.core.ComponentMapper;
import com.nuclearthinking.myheroagency.model.components.*;
import com.nuclearthinking.myheroagency.model.components.hud.HudComponent;
import com.nuclearthinking.myheroagency.model.components.hud.PlayerHudComponent;
import com.nuclearthinking.myheroagency.model.components.hud.StatHudComponent;
import com.nuclearthinking.myheroagency.model.components.hud.UtilsHudComponent;

/**
 * Created by mkuksin on 02.09.2016.
 *
 * ComponentMapper - выполняется за O(1)
 * Одиночный вызов например AnimationComponent velocity = entity.getComponent(AnimationComponent.class);
 * выполнится за O(log n) что существенно дольше.
 */
public final class Components {

    public static final ComponentMapper<AnimationComponent> ANIMATION = ComponentMapper.getFor(AnimationComponent.class);
    public static final ComponentMapper<CameraComponent> CAMERA = ComponentMapper.getFor(CameraComponent.class);
    public static final ComponentMapper<GravityComponent> GRAVITY = ComponentMapper.getFor(GravityComponent.class);
    public static final ComponentMapper<MapComponent> MAP = ComponentMapper.getFor(MapComponent.class);
    public static final ComponentMapper<MovementComponent> MOVEMENT = ComponentMapper.getFor(MovementComponent.class);
    public static final ComponentMapper<PlayerComponent> PLAYER = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<NpcComponent> NPC = ComponentMapper.getFor(NpcComponent.class);
    public static final ComponentMapper<MonsterComponent> MONSTER = ComponentMapper.getFor(MonsterComponent.class);
    public static final ComponentMapper<BodyComponent> BODY = ComponentMapper.getFor(BodyComponent.class);
    public static final ComponentMapper<StateComponent> STATE = ComponentMapper.getFor(StateComponent.class);
    public static final ComponentMapper<TextureComponent> TEXTURE = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<LightComponent> LIGHT = ComponentMapper.getFor(LightComponent.class);
    public static final ComponentMapper<HudComponent> HUD = ComponentMapper.getFor(HudComponent.class);
    public static final ComponentMapper<UtilsHudComponent> UHC = ComponentMapper.getFor(UtilsHudComponent.class);
    public static final ComponentMapper<PlayerHudComponent> PHC = ComponentMapper.getFor(PlayerHudComponent.class);
    public static final ComponentMapper<StatHudComponent> SHC = ComponentMapper.getFor(StatHudComponent.class);
}
