package com.nuclearthinking.myheroagency.model.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mkuksin on 22.11.2016.
 */
@Slf4j(topic = "Weapon")
@JsonPropertyOrder({
        "id",
        "name",
        "type",
        "pAtk",
        "mAtk",
        "distance",
        "skill"
})
@ToString
public class Weapon {

    public enum WeaponType{
        SWORD,
        DIGGER,
        BOW,
        STAFF
    }

    @JsonProperty("id")
    private @Getter int id;
    @JsonProperty("name")
    private @Getter String name;
    @JsonProperty("type")
    private @Getter WeaponType type;
    @JsonProperty("pAtk")
    private @Getter int pAtk;
    @JsonProperty("mAtk")
    private @Getter int mAtk;
    @JsonProperty("distance")
    private @Getter int distance;
    @JsonProperty("skill")
    private @Getter int[] skill;
}
