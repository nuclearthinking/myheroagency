package com.nuclearthinking.myheroagency.controller.manager;

import com.badlogic.gdx.files.FileHandle;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuclearthinking.myheroagency.model.npc.NpcParser;
import com.nuclearthinking.myheroagency.model.quest.QuestParser;
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

    public JsonToObject(@NonNull final FileHandle jsonFile) {
        val questFile = jsonFile.sibling(jsonFile.nameWithoutExtension() + ".quest");
        val npcFile = jsonFile.sibling(jsonFile.nameWithoutExtension() + ".npc");

        if(questFile.exists()){
            loadQuest(questFile);
        }
        if(npcFile.exists()){
            loadNpc(npcFile);
        }
    }

    public JsonToObject(){}

    private void loadQuest(@NonNull final FileHandle questFile) {
        log.info("File: " + questFile + " loading");
        readJsonQuestFile(questFile);
        log.info("Loaded: " + questParser.getBaseQuest().size() + " quest(s)");
    }

    private void loadNpc(@NonNull final FileHandle npcFile){
        log.info("File: " + npcFile + " loading");
        readJsonNpcFile(npcFile);
        log.info("Loaded: " + npcParser.getBaseNpc().size() + " npc");
    }

    private void readJsonQuestFile(@NonNull final FileHandle jsonFile){
        try {
            val jsonFactory = new JsonFactory();
            val jsonParser = jsonFactory.createParser(jsonFile.file());
            val mapper = new ObjectMapper();
            val questParserMappingIterator = mapper.readValues(jsonParser, QuestParser.class);
            questParser = questParserMappingIterator.next();
        }
        catch (IOException e){}
    }

    private void readJsonNpcFile(@NonNull final FileHandle jsonFile){
        try {
            val jsonFactory = new JsonFactory();
            val jsonParser = jsonFactory.createParser(jsonFile.file());
            val mapper = new ObjectMapper();
            val questParserMappingIterator = mapper.readValues(jsonParser, NpcParser.class);
            npcParser = questParserMappingIterator.next();
        }
        catch (IOException e){}
    }
}
