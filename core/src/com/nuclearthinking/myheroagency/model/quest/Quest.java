package com.nuclearthinking.myheroagency.model.quest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Izonami on 20.06.2016.
 */
public class Quest {

    private static final Logger LOG = LoggerFactory.getLogger(Quest.class.getSimpleName());

    protected final String _name;
    protected final int _questId;

    private Set<Integer> _questItems = new HashSet<Integer>();

    public Quest(){
        _name = getClass().getSimpleName();
        _questId = Integer.parseInt(_name.split("_")[1]);
        LOG.info("Quest Name: " + _name + " QuestID: " + _questId);
        QuestManager.addQuest(this);
    }

    /**
     * Добавляем квестовые вещи которые будут участвовать в квесте
     * @param ids
     */
    public void addQuestItem(final int... ids) {
        for(int id : ids){
            if (id != 0){
                _questItems.add(id);
            }
        }
    }

    /**
     * Проверяем что вещь квестовая
     * @param id
     * @return
     */
    public boolean isQuestItem(final int id) {
        return _questItems.contains(id);
    }

    public String getName() {
        return _name;
    }

    public int getQuestIntId() {
        return _questId;
    }

}
