package com.nuclearthinking.myheroagency.model.quest;

import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.manager.JsonToObject;
import com.nuclearthinking.myheroagency.controller.systems.NpcSystem;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.*;

/**
 * Created by Izonami on 20.06.2016.
 */
@Slf4j(topic = "Quest")
public abstract class Quest {

    protected static final ArrayList<QuestBase> questInfo = Asset.getInstance().get("quest/quest.json", JsonToObject.class).getQuestParser().getBaseQuest();
    protected @Getter final String name;
    protected @Getter final int questId;
    protected @Getter QuestBase quest;

    //TODO: Костыль
    private static Map<String, Quest> q = new HashMap<String, Quest>();
    public static Quest getQ(final String qu){
        return q.get(qu);
    }

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
        q.put(Integer.toString(id), this); //TODO: Костыль
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

    public boolean showDialog(final String text){
        log.info(text);

        return true;
    }

    public boolean notifyTalk(@NonNull final NpcSystem npc) {
        String res;
        try {
            res = onTalk(npc);
        } catch (Exception e) {
            return true;
        }
        return showDialog(res);
    }

    public abstract String onTalk(@NonNull final NpcSystem npc);

    @Override
    public String toString() {
        return "Quest{" +
                "name='" + name + '\'' +
                ", questId=" + questId +
                ", questItems=" + questItems +
                '}';
    }
}
