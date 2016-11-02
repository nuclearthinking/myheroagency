package com.nuclearthinking.myheroagency.model.quest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mkuksin on 01.11.2016.
 */
@Slf4j(topic = "QuestBase")
@JsonPropertyOrder({
        "id",
        "name",
        "description",
        "type",
        "npcId",
        "monsterId",
        "monsterKillCount",
        "questItemId",
        "questItemCount",
        "text",
        "rewardId",
        "rewardCount"})
public class QuestBase {

    public enum QuestType{
        ONES,
        REPEAT,
        EPIC
    }

    @JsonProperty("id")
    private @Getter int id;
    @JsonProperty("name")
    private @Getter String name;
    @JsonProperty("description")
    private @Getter String description;
    @JsonProperty("type")
    private @Getter QuestType type;
    @JsonProperty("npcId")
    private @Getter int npcId;
    @JsonProperty("monsterId")
    private @Getter int[] monsterId;
    @JsonProperty("monsterKillCount")
    private @Getter int[] monsterCount;
    @JsonProperty("questItemId")
    private @Getter int[] questItemId;
    @JsonProperty("questItemCount")
    private @Getter int[] questItemCount;
    @JsonProperty("text")
    private @Getter ArrayList<QuestText> text;
    @JsonProperty("rewardId")
    private @Getter int[] rewardId;
    @JsonProperty("rewardCount")
    private @Getter int[] rewardCount;

    @Override
    public String toString() {
        return "QuestInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description +
                ", type=" + type +
                ", npcId=" + npcId +
                ", monsterId=" + Arrays.toString(monsterId) +
                ", monsterCount=" + Arrays.toString(monsterCount) +
                ", questItemId=" + Arrays.toString(questItemId) +
                ", questItemCount=" + Arrays.toString(questItemCount) +
                ", text=" + text +
                ", rewardId=" + Arrays.toString(rewardId) +
                ", rewardCount=" + Arrays.toString(rewardCount) +
                '}';
    }
}
