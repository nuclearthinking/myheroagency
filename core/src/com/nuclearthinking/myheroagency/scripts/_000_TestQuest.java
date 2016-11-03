package com.nuclearthinking.myheroagency.scripts;

import com.nuclearthinking.myheroagency.controller.systems.NpcSystem;
import com.nuclearthinking.myheroagency.model.components.NpcComponent;
import com.nuclearthinking.myheroagency.model.quest.Quest;
import lombok.NonNull;

/**
 * Created by Izonami on 20.06.2016.
 */
public final class _000_TestQuest extends Quest {

    public _000_TestQuest(){
        super();
        addQuestItem(quest.getQuestItemId());
    }

    @Override
    public String onTalk(@NonNull NpcSystem npc) {
        String text = "nothing";
        if(((NpcComponent)npc.getActor()).getId() == quest.getNpcId()){
            text = quest.getText().get(0).getStartText();
        }

        return text;
    }
}
