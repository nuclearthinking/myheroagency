package com.nuclearthinking.myheroagency.model.quest;

import lombok.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Общее хранилище для всех квестов. Предоставляет доступ ко всем квестам
 * Created by Izonami on 20.06.2016.
 */
public class QuestManager {

    private static Map<String, Quest> questsByName = new ConcurrentHashMap<String, Quest>();
    private static Map<Integer, Quest> questsById = new ConcurrentHashMap<Integer, Quest>();

    public static Quest getQuestByName(final String name) {
        return questsByName.get(name);
    }

    public static Quest getQuestById(final int questId) {
        return questsById.get(questId);
    }

    public static void addQuest(@NonNull final Quest newQuest) {
        questsByName.put(newQuest.getName(), newQuest);
        questsById.put(newQuest.getQuestId(), newQuest);
    }

}
