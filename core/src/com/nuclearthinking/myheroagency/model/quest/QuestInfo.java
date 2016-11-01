package com.nuclearthinking.myheroagency.model.quest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * Created by mkuksin on 01.11.2016.
 */
@Slf4j(topic = "QuestInfo")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type","npcId","text","reward"})
public class QuestInfo {

    @JsonProperty("type")
    private @Getter QuestType type;
    @JsonProperty("npcId")
    private @Getter ArrayList<QuestNpc> npcId;
    @JsonProperty("text")
    private @Getter ArrayList<QuestText> text;
    @JsonProperty("reward")
    private @Getter ArrayList<QuestReward> reward;
}
