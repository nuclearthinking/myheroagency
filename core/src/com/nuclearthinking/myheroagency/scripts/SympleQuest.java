package com.nuclearthinking.myheroagency.scripts;

import com.nuclearthinking.myheroagency.model.components.NpcComponent;
import lombok.NonNull;

/**
 * Created by mkuksin on 02.11.2016.
 */
public interface SympleQuest {

    void dialog(@NonNull final NpcComponent npc);
}
