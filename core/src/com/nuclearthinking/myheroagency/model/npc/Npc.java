package com.nuclearthinking.myheroagency.model.npc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
        "STR",
        "CON",
        "DEX",
        "INT",
        "WIT",
        "MEN",
        "skillId",
        "dropInfo"
})
@ToString
public class Npc {

    @JsonProperty("id")
    private @Getter int id;
    @JsonProperty("name")
    private @Getter String name;
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

    @JsonProperty("STR")
    private @Getter int STR;
    @JsonProperty("CON")
    private @Getter int CON;
    @JsonProperty("DEX")
    private @Getter int DEX;
    @JsonProperty("INT")
    private @Getter int INT;
    @JsonProperty("WIT")
    private @Getter int WIT;
    @JsonProperty("MEN")
    private @Getter int MEN;

    @JsonProperty("skillId")
    private @Getter int[] skillId;

    @JsonProperty("dropInfo")
    private @Getter DropInfo dropInfo;
}
