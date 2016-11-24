package com.nuclearthinking.myheroagency.model.skills;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mkuksin on 22.11.2016.
 */
@Slf4j(topic = "Skill")
@JsonPropertyOrder({
        "id",
        "name",
        "lvlUp",
        "skillType",
        "effectType",
        "level",
        "power"
})
@ToString
public class Skill {

    public enum SkillType {
        PASSIVE,
        ACTIVE
    }

    public enum EffectType {
        ADDPATK,
        ADDMATK
    }

    @JsonProperty("id")
    private @Getter int id;
    @JsonProperty("name")
    private @Getter String name;
    @JsonProperty("lvlUp")
    private @Getter int[] lvlUp;
    @JsonProperty("skillType")
    private @Getter SkillType skillType;
    @JsonProperty("effectType")
    private @Getter EffectType effectType;
    @JsonProperty("level")
    private @Getter int[] level;
    @JsonProperty("power")
    private @Getter int[] power;
}
