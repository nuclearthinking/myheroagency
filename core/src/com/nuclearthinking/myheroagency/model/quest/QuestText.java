package com.nuclearthinking.myheroagency.model.quest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mkuksin on 01.11.2016.
 */
@Slf4j(topic = "QuestText")
@JsonPropertyOrder({
        "startText",
        "nextText",
        "endText"})
public class QuestText {

    @JsonProperty("startText")
    private @Getter String startText;
    @JsonProperty("nextText")
    private @Getter String nextText;
    @JsonProperty("endText")
    private @Getter String endText;
}
