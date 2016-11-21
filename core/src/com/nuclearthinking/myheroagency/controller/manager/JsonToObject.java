package com.nuclearthinking.myheroagency.controller.manager;

import com.badlogic.gdx.files.FileHandle;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.MappingIterator;
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
            load(questFile, jsonFile.extension());
            log.info("Loaded: " + questParser.getBaseQuest().size() + " quest(s)");
        }
        if(npcFile.exists()){
            load(npcFile, jsonFile.extension());
            log.info("Loaded: " + npcParser.getBaseNpc().size() + " npc");
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
            }
        }
        catch (IOException e){}
    }
}
