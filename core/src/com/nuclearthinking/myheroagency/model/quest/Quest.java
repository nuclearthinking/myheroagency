package com.nuclearthinking.myheroagency.model.quest;

import com.nuclearthinking.myheroagency.controller.loader.file.JsonToObject;
import com.nuclearthinking.myheroagency.controller.manager.AssetsManager;
import com.nuclearthinking.myheroagency.model.actor.npc.NpcComponent;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.*;

/**
 * Created by Izonami on 20.06.2016.
 */
@Slf4j(topic = "Quest")
@ToString
public abstract class Quest {

    protected @Getter final String name;
    protected @Getter final int questId;
    protected @Getter QuestBase quest;

    private static final ArrayList<QuestBase> questInfo = AssetsManager.getInstance().get(Constants.QUEST_JSON, JsonToObject.class).getQuestParser().getBaseQuest();

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

    public boolean notifyTalk(@NonNull final NpcComponent npc) {
        return showDialog(onTalk(npc));
    }

    public abstract String onTalk(@NonNull final NpcComponent npc);
}
