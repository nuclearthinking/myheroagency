package com.nuclearthinking.myheroagency.model.quest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Общее хранилище для всех квестов. Предоставляет доступ ко всем квестам
 * Created by Izonami on 20.06.2016.
 */
public class QuestManager {

    private static Map<String, Quest> _questsByName = new ConcurrentHashMap<String, Quest>();
    private static Map<Integer, Quest> _questsById = new ConcurrentHashMap<Integer, Quest>();

    public static Quest getQuestByName(final String name) {
        return _questsByName.get(name);
    }

    public static Quest getQuestById(final int questId) {
        return _questsById.get(questId);
    }

    public static void addQuest(Quest newQuest) {
        _questsByName.put(newQuest.getName(), newQuest);
        _questsById.put(newQuest.getQuestId(), newQuest);
    }

}
