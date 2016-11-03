package com.nuclearthinking.myheroagency.scripts;

import com.nuclearthinking.myheroagency.controller.systems.NpcSystem;
import com.nuclearthinking.myheroagency.model.quest.Quest;
import lombok.NonNull;

/**
 * Created by mkuksin on 02.11.2016.
 */
public final class _001_QuestOfFate extends Quest {
    
    @Override
    public String onTalk(@NonNull NpcSystem npc) {
        String text = "Test nothing";
        return text;
    }
}
