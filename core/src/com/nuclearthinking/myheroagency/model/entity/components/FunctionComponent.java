package com.nuclearthinking.myheroagency.model.entity.components;

import com.badlogic.ashley.core.Component;
import com.nuclearthinking.myheroagency.model.skills.Calculator;
import com.nuclearthinking.myheroagency.model.skills.Stats;
import lombok.Getter;

/**
 * Created by mkuksin on 26.09.2016.
 */
public class FunctionComponent implements Component {
    private @Getter static final Calculator[] calculators = new Calculator[Stats.NUM_STATS];
}
