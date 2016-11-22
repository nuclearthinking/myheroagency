package com.nuclearthinking.myheroagency.model.skills;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * Created by mkuksin on 22.11.2016.
 */
@Slf4j(topic = "SkillParser")
@JsonPropertyOrder("skills")
public class SkillParser {

    @JsonProperty("skills")
    private @Getter ArrayList<Skill> skills;
}
