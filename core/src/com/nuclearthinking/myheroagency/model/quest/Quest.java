package com.nuclearthinking.myheroagency.model.quest;

import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.manager.JsonToObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Izonami on 20.06.2016.
 */
@Slf4j(topic = "Quest")
public class Quest {

    protected static final ArrayList<QuestBase> questInfo = Asset.getInstance().get("quest/quest.json", JsonToObject.class).getQuestParser().getBaseQuest();
    protected @Getter final String name;
    protected @Getter final int questId;
    protected @Getter QuestBase quest;

    private Set<Integer> questItems = new HashSet<Integer>();


    public Quest(){
        final int id = Integer.parseInt(getClass().getSimpleName().split("_")[1]);

        this.name = questInfo.get(id).getName();
        this.questId = id;
        try {
            this.quest = questInfo.get(id);
        }
        catch (IndexOutOfBoundsException e){
            log.error("Incorrect quest class name " + getClass().getSimpleName());
        }

        log.info("Quest Name: " + name + " QuestID: " + questId);
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

    public void showDialog(final String text){
        System.out.println(text);
    }

    @Override
    public String toString() {
        return "Quest{" +
                "name='" + name + '\'' +
                ", questId=" + questId +
                ", questItems=" + questItems +
                '}';
    }
}
