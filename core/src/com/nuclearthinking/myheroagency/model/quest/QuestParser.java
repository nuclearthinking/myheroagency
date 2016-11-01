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
@Slf4j(topic = "QuestParser")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"quest_base"})
public class QuestParser {
    @JsonProperty("quest_base")
    private @Getter ArrayList<QuestBase> baseQuest;
}
