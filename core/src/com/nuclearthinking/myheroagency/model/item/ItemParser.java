package com.nuclearthinking.myheroagency.model.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * Created by mkuksin on 22.11.2016.
 */
@Slf4j(topic = "ItemParser")
@JsonPropertyOrder("weapon")
@ToString
public class ItemParser {

    @JsonProperty("weapon")
    private @Getter ArrayList<Weapon> weapons;
}
