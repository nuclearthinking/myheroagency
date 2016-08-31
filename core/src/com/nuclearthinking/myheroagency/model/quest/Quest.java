package com.nuclearthinking.myheroagency.model.quest;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Izonami on 20.06.2016.
 */
@Slf4j(topic = "Quest")
public class Quest {

    protected @Getter final String name;
    protected @Getter final int questId;

    private Set<Integer> questItems = new HashSet<Integer>();

    public Quest(){
        name = getClass().getSimpleName();
        questId = Integer.parseInt(name.split("_")[1]);
        log.info("Quest Name: " + name + " QuestID: " + questId);
        QuestManager.addQuest(this);
    }

    /**
     * Добавляем квестовые вещи которые будут участвовать в квесте
     * @param ids
     */
    public void addQuestItem(final int... ids) {
        for(val id : ids){
            if (id != 0){
                questItems.add(id);
            }
        }
    }

    /**
     * Проверяем что вещь квестовая
     * @param id
     * @return
     */
    public boolean isQuestItem(final int id) {
        return questItems.contains(id);
    }

    @Override
    public String toString(){
        return name;
    }

}
