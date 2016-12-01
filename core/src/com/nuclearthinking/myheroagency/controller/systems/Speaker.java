package com.nuclearthinking.myheroagency.controller.systems;

import com.nuclearthinking.myheroagency.model.actor.base.DialogComponent;
import lombok.NonNull;

/**
 * Created by mkuksin on 08.11.2016.
 */
public interface Speaker {

    void showDialog(@NonNull final PlayerSystem player, @NonNull final DialogComponent dialog, final String command);
}
