package com.nuclearthinking.myheroagency.scripts;

import com.nuclearthinking.myheroagency.model.components.NpcComponent;
import com.nuclearthinking.myheroagency.model.quest.Quest;
import lombok.NonNull;

/**
 * Created by mkuksin on 02.11.2016.
 */
public final class _001_QuestOfFate extends Quest {
    
    @Override
    public String onTalk(@NonNull NpcComponent npc) {
        String text = "Test nothing";
        return text;
    }
}
