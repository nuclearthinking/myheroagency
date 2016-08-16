package com.nuclearthinking.myheroagency.controller;

import com.nuclearthinking.myheroagency.ui.hud.layer.ObserverStats;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Izonami on 15.08.2016.
 */
public interface ObservableStats {
    final List<ObserverStats> statsList = new ArrayList<ObserverStats>();

    void subscribe(ObserverStats stats);
    void unsubscribe(ObserverStats stats);
    void notifyLayer();
}
