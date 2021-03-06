package com.nuclearthinking.myheroagency.model.actor.npc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * Created by mkuksin on 21.11.2016.
 */
@Slf4j(topic = "NpcParser")
@JsonPropertyOrder("npc")
public class NpcParser {

    @JsonProperty("npc")
    private @Getter ArrayList<Npc> baseNpc;
}
