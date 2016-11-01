package com.nuclearthinking.myheroagency.model.quest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mkuksin on 01.11.2016.
 */
@Slf4j(topic = "QuestNpc")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"startNpcId","nextNpcId","endNpcId"})
public class QuestNpc {

    @JsonProperty("startNpcId")
    private @Getter Integer startNpcId;
    @JsonProperty("nextNpcId")
    private @Getter Integer nextNpcId;
    @JsonProperty("endNpcId")
    private @Getter Integer endNpcId;
}
