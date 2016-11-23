package com.nuclearthinking.myheroagency.controller.systems;

import com.nuclearthinking.myheroagency.model.components.GameActor;
import lombok.NonNull;

/**
 * Created by mkuksin on 08.11.2016.
 */
public interface Speaker {

    void showDialog(@NonNull final GameActor player, final String command);
}
