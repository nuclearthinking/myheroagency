package com.nuclearthinking.myheroagency.model.quest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mkuksin on 01.11.2016.
 */
@Slf4j(topic = "QuestReward")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id","count"})
public class QuestReward {

    @JsonProperty("id")
    private @Getter Integer id;
    @JsonProperty("count")
    private @Getter Integer count;
}
