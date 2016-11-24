package com.nuclearthinking.myheroagency.scripts;

import com.nuclearthinking.myheroagency.model.actor.npc.NpcComponent;
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
    public String onTalk(@NonNull NpcComponent npc) {
        String text = "nothing";
        System.out.println("NPC" + npc.getId() + " Quest" + quest.getNpcId());
        if(npc.getId() == quest.getNpcId()){
            text = quest.getText().getStartText();
        }

        return text;
    }
}
