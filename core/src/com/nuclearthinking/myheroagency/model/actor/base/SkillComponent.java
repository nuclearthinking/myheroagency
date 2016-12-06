package com.nuclearthinking.myheroagency.model.actor.base;

import com.badlogic.ashley.core.Component;
import com.nuclearthinking.myheroagency.model.skills.Skill;
import com.nuclearthinking.myheroagency.model.skills.SkillInstance;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mkuksin on 05.12.2016.
 */
public class SkillComponent implements Component {

    private @Getter @Setter Map<Integer, Skill> skillList = new HashMap<Integer, Skill>();

    public SkillComponent(){
        skillList.put(1, SkillInstance.getInstance().getSkill(1));
    }
}
