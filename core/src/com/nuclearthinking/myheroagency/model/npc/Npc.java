package com.nuclearthinking.myheroagency.model.npc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nuclearthinking.myheroagency.model.GameObject;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mkuksin on 21.11.2016.
 */
@Slf4j(topic = "Npc")
@JsonPropertyOrder({
        "id",
        "name",
        "level",
        "baseHpReg",
        "baseMpReg",
        "baseHpMax",
        "baseMpMax",
        "basePAtk",
        "baseMAtk",
        "basePDef",
        "baseMDef",
        "basePAtkSpd",
        "baseMAtkSpd",
        "basePCritRate",
        "baseMCritRate",
        "basePCritChance",
        "baseMCritChance",
        "baseRunSpd",
        "skillId"
})
@ToString
public class Npc implements GameObject {

    @JsonProperty("id")
    private @Getter int id;
    @JsonProperty("name")
    private @Getter String name;
    @JsonProperty("level")
    private @Getter int level;
    @JsonProperty("baseHpReg")
    private @Getter int baseHpReg;
    @JsonProperty("baseMpReg")
    private @Getter int baseMpReg;
    @JsonProperty("baseHpMax")
    private @Getter int baseHpMax;
    @JsonProperty("baseMpMax")
    private @Getter int baseMpMax;
    @JsonProperty("basePAtk")
    private @Getter int basePAtk;
    @JsonProperty("baseMAtk")
    private @Getter int baseMAtk;
    @JsonProperty("basePDef")
    private @Getter int basePDef;
    @JsonProperty("baseMDef")
    private @Getter int baseMDef;
    @JsonProperty("basePAtkSpd")
    private @Getter int basePAtkSpd;
    @JsonProperty("baseMAtkSpd")
    private @Getter int baseMAtkSpd;
    @JsonProperty("basePCritRate")
    private @Getter int basePCritRate;
    @JsonProperty("baseMCritRate")
    private @Getter int baseMCritRate;
    @JsonProperty("basePCritChance")
    private @Getter int basePCritChance;
    @JsonProperty("baseMCritChance")
    private @Getter int baseMCritChance;
    @JsonProperty("baseRunSpd")
    private @Getter int baseRunSpd;
    @JsonProperty("skillId")
    private @Getter int[] skills;
}
