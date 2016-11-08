package com.nuclearthinking.myheroagency.controller.systems;

import lombok.NonNull;

/**
 * Created by mkuksin on 08.11.2016.
 */
public interface Speaker {

    void showDialog(@NonNull final PlayerSystem player, final String command);
}
