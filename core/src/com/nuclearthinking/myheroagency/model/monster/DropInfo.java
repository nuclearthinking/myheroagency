package com.nuclearthinking.myheroagency.model.monster;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mkuksin on 21.11.2016.
 */
@Slf4j(topic = "DropInfo")
@JsonPropertyOrder({"itemId","itemCount","chance"})
@ToString
public class DropInfo {

    @JsonProperty("itemId")
    private @Getter int[] itemId;
    @JsonProperty("itemCount")
    private @Getter int[] itemCount;
    @JsonProperty("chance")
    private @Getter int[] chance;
}
