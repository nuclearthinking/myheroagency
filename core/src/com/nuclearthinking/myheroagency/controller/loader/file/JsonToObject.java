package com.nuclearthinking.myheroagency.controller.loader.file;

import com.badlogic.gdx.files.FileHandle;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuclearthinking.myheroagency.model.actor.monster.MonsterParser;
import com.nuclearthinking.myheroagency.model.actor.npc.NpcParser;
import com.nuclearthinking.myheroagency.model.item.ItemParser;
import com.nuclearthinking.myheroagency.model.quest.QuestParser;
import com.nuclearthinking.myheroagency.model.skills.SkillParser;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;

/**
 * Created by mkuksin on 01.11.2016.
 */
@Slf4j(topic = "JsonToObject")
public class JsonToObject {

    private @Getter QuestParser questParser = null;
    private @Getter NpcParser npcParser = null;
    private @Getter MonsterParser monsterParser = null;
    private @Getter ItemParser itemParser = null;
    private @Getter SkillParser skillParser = null;

    public JsonToObject(@NonNull final FileHandle jsonFile) {
        val questFile = jsonFile.sibling(jsonFile.nameWithoutExtension() + ".quest");
        val npcFile = jsonFile.sibling(jsonFile.nameWithoutExtension() + ".npc");
        val monsterFile = jsonFile.sibling(jsonFile.nameWithoutExtension() + ".monster");
        val itemFile = jsonFile.sibling(jsonFile.nameWithoutExtension() + ".item");
        val skillFile = jsonFile.sibling(jsonFile.nameWithoutExtension() + ".skill");

        if(questFile.exists()){
            load(questFile, jsonFile.extension());
            log.info("Loaded: " + questParser.getBaseQuest().size() + " quest(s)");
        }
        if(npcFile.exists()){
            load(npcFile, jsonFile.extension());
            log.info("Loaded: " + npcParser.getBaseNpc().size() + " npc");
        }
        if(monsterFile.exists()){
            load(monsterFile, jsonFile.extension());
            log.info("Loaded: " + monsterParser.getBaseMonster().size() + " monster(s)");
        }
        if(itemFile.exists()){
            load(itemFile, jsonFile.extension());
            log.info("Loaded: " + itemParser.getWeapons().size() + " weapon(s)");
        }
        if(skillFile.exists()){
            load(skillFile, jsonFile.extension());
            log.info("Loaded: " + skillParser.getSkills().size() + " skill(s)");
        }
    }

    private void load(@NonNull final FileHandle file, String type){
        log.info("File: " + file + " loading");
        readJsonFile(file, type);
    }

    private void readJsonFile(@NonNull final FileHandle fileHandle, final String type){
        try {
            val jsonFactory = new JsonFactory();
            val jsonParser = jsonFactory.createParser(fileHandle.file());
            val mapper = new ObjectMapper();
            MappingIterator<?> parseMapping;

            switch (type){
                case "quest" :
                    parseMapping = mapper.readValues(jsonParser, QuestParser.class);
                    questParser = (QuestParser) parseMapping.next();
                    break;
                case "npc" :
                    parseMapping = mapper.readValues(jsonParser, NpcParser.class);
                    npcParser = (NpcParser) parseMapping.next();
                    break;
                case "monster" :
                    parseMapping = mapper.readValues(jsonParser, MonsterParser.class);
                    monsterParser = (MonsterParser) parseMapping.next();
                    break;
                case "item" :
                    parseMapping = mapper.readValues(jsonParser, ItemParser.class);
                    itemParser = (ItemParser) parseMapping.next();
                    break;
                case "skill" :
                    parseMapping = mapper.readValues(jsonParser, SkillParser.class);
                    skillParser = (SkillParser) parseMapping.next();
                    break;
                default:log.error("WTF! Give me correct file type");
                    break;
            }
        }
        catch (IOException e){}
    }
}
