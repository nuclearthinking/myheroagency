package com.nuclearthinking.myheroagency.scripts;

import com.nuclearthinking.myheroagency.controller.manager.QuestManager;

/**
 * Created by Izonami on 20.06.2016.
 */
public final class QuestLoader {

    //Сюда добавляются квеста которы нужно загружать
    public static void load(){
        QuestManager.addQuest(new _000_TestQuest());
        QuestManager.addQuest(new _001_QuestOfFate());
    }

}
