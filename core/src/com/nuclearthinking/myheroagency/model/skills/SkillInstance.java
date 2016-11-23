package com.nuclearthinking.myheroagency.model.skills;

import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.manager.JsonToObject;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Created by mkuksin on 23.11.2016.
 */
public class SkillInstance {

    private static final SkillInstance instance = new SkillInstance();
    private @Getter final ArrayList<Skill> skills = Asset.getInstance().get(Constants.SKILL_JSON, JsonToObject.class).getSkillParser().getSkills();

    public Skill getSkill(final int id){
        return skills.get(id);
    }

    private SkillInstance(){}

    public static SkillInstance getInstance(){
        return instance;
    }
}
