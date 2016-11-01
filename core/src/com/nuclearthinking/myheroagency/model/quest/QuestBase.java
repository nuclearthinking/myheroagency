package com.nuclearthinking.myheroagency.model.quest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mkuksin on 01.11.2016.
 */
@Slf4j(topic = "QuestBase")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id","name","questInfo"})
public class QuestBase {

    @JsonProperty("id")
    private @Getter Integer id;
    @JsonProperty("name")
    private @Getter String name;
    @JsonProperty("questInfo")
    private @Getter QuestInfo questInfo;

    @Override
    public String toString() {
        return "QuestBase{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
