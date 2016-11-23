package com.nuclearthinking.myheroagency.model.components;

import com.nuclearthinking.myheroagency.controller.systems.Speaker;
import com.nuclearthinking.myheroagency.model.npc.Npc;
import com.nuclearthinking.myheroagency.model.quest.Quest;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mkuksin on 26.09.2016.
 */
@Slf4j(topic = "NpcComponent")
@ToString
public final class NpcComponent extends GameActor implements Speaker {

    private @Getter @Setter int id;
    private @Getter @Setter String name;
    private @Setter Npc template;

    public NpcComponent(){
        super();
    }

    public void init(){
        id = template.getId();
        name = template.getName();
        level = template.getLevel();
        baseHpMax = template.getBaseHpMax();
        baseMpMax = template.getBaseMpMax();
        baseHpReg = template.getBaseHpReg();
        baseMpReg = template.getBaseMpReg();
        basePAtk = template.getBasePAtk();
        baseMAtk = template.getBaseMAtk();
        basePDef = template.getBasePDef();
        baseMDef = template.getBaseMDef();
        basePAtkSpd = template.getBasePAtkSpd();
        baseMAtkSpd = template.getBaseMAtkSpd();
        basePCritRate = template.getBasePCritRate();
        baseMCritRate = template.getBaseMCritRate();
        basePCritChance = template.getBasePCritChance();
        baseMCritChance = template.getBaseMCritChance();
        baseRunSpd = template.getBaseRunSpd();
    }

    @Override
    public void showDialog(@NonNull GameActor player, String command) {
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

    private void showQuestWindow(@NonNull final  GameActor player, final String questId){
        // TODO: должно быть что то такое
        //Quest q = ((PlayerComponent)player.getActor()).getQuest(questId);
        //q.notifyTalk(this);

        val quest = Quest.getQ(questId);
        quest.notifyTalk(this);
    }

    @Override
    public boolean isNpc(){
        return true;
    }
}
