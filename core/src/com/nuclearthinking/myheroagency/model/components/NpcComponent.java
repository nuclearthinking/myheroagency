package com.nuclearthinking.myheroagency.model.components;

import com.nuclearthinking.myheroagency.controller.systems.PlayerSystem;
import com.nuclearthinking.myheroagency.controller.systems.Speaker;
import com.nuclearthinking.myheroagency.model.quest.Quest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * Created by mkuksin on 26.09.2016.
 */
@Slf4j(topic = "NpcComponent")
public final class NpcComponent extends GameActor implements Speaker {

    private @Getter @Setter int id = 1;
    private @Getter @Setter String name = "Василий";
    public static final float BASE_HP_REG = 10;
    public static final float BASE_MP_REG = 10;
    public static final int BASE_HP_MAX = 100;
    public static final int BASE_MP_MAX = 100;
    public static final int BASE_PATK = 10;
    public static final int BASE_MATK = 10;
    public static final int BASE_PDEF = 10;
    public static final int BASE_MDEF = 10;
    public static final int BASE_PATK_SPD = 10;
    public static final int BASE_MATK_SPD = 10;
    public static final int BASE_CRIT_RATE = 10;
    public static final int BASE_MCRIT_RATE = 10;
    public static final int BASE_RUN_SPD = 100;

    public NpcComponent(){
        super();
    }

    @Override
    public void showDialog(@NonNull PlayerSystem player, String command) {
        try {
            if(command == null || command.length() == 0){
                log.info("Player " + player.getLevel() + " starting dialog with " + name);
            }
            else if(command.equalsIgnoreCase("openWindow")){
                log.info("Player " + player.getLevel() + " use command " + "[" + command + "]");
            }
            else if (command.startsWith("quest")){
                log.info("Player " + player.getLevel() + " use command " + "[" + command + "]");

                final String quest = command.substring(5).trim();
                if (quest.length() > 0) {
                    showQuestWindow(player, quest);
                }
            }
        }
        catch (StringIndexOutOfBoundsException e){
            log.info("Incorrect command: " + "[" + command + "]" + id);
        }
    }

    private void showQuestWindow(@NonNull final  PlayerSystem player, final String questId){
        // TODO: должно быть что то такое
        //Quest q = ((PlayerComponent)player.getActor()).getQuest(questId);
        //q.notifyTalk(this);

        val quest = Quest.getQ(questId);
        quest.notifyTalk(this);
    }
}
