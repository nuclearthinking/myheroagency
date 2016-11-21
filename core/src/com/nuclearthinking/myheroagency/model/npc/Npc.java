package com.nuclearthinking.myheroagency.model.npc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mkuksin on 21.11.2016.
 */
@Slf4j(topic = "Npc")
@JsonPropertyOrder({"id"})
public class Npc {

    @JsonProperty("id")
    private @Getter int id;
}
