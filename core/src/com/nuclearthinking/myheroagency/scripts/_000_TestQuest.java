package com.nuclearthinking.myheroagency.scripts;

import com.nuclearthinking.myheroagency.model.components.NpcComponent;
import com.nuclearthinking.myheroagency.model.quest.Quest;
import lombok.NonNull;

/**
 * Created by Izonami on 20.06.2016.
 */
public final class _000_TestQuest extends Quest implements SympleQuest {

    public _000_TestQuest(){
        super();
        addQuestItem(quest.getQuestItemId());
    }

    @Override
    public void dialog(@NonNull NpcComponent npc) {
        if(npc.getId() == quest.getNpcId()){
            showDialog(quest.getText().get(0).getStartText());
        }
    }
}
