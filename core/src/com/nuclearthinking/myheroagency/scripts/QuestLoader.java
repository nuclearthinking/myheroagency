package com.nuclearthinking.myheroagency.scripts;

import com.nuclearthinking.myheroagency.controller.manager.QuestManager;

/**
 * Created by Izonami on 20.06.2016.
 */
public final class QuestLoader {

    public static void load(){
        QuestManager.addQuest(new _001_TestQuest());
    }

}
