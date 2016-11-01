package com.nuclearthinking.myheroagency.controller.manager;

import com.badlogic.gdx.files.FileHandle;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuclearthinking.myheroagency.model.quest.QuestParser;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;

/**
 * Created by mkuksin on 01.11.2016.
 */
@Slf4j(topic = "JsonToQuest")
public class JsonToQuest {

    private @Getter QuestParser questParser = null;

    public JsonToQuest (@NonNull final FileHandle jsonFile) {
        val questFile = jsonFile.sibling(jsonFile.nameWithoutExtension() + ".json");

        if(questFile.exists()){
            load(questFile);
        }
    }

    public JsonToQuest(){}

    public void load(@NonNull final FileHandle questFile) {
        log.info("File: " + questFile + " loading");

        readJsonFile(questFile);
        log.info("Loaded: " + questParser.getBaseQuest().size() + " quest(s)");
    }

    private void readJsonFile(@NonNull final FileHandle questJsonFile){
        try {
            val jsonFactory = new JsonFactory();
            val jsonParser = jsonFactory.createParser(questJsonFile.file());
            val mapper = new ObjectMapper();
            val questParserMappingIterator = mapper.readValues(jsonParser, QuestParser.class);
            questParser = questParserMappingIterator.next();
        }
        catch (IOException e){}
    }
}
