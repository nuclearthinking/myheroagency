package com.nuclearthinking.myheroagency.model.skills.funcs;

import com.nuclearthinking.myheroagency.model.skills.Env;

/**
 * Created by Izonami on 03.08.2016.
 */
public abstract class Lambda {

    public double _value = 0;

    public Lambda(final double value)
    {
        _value = value;
    }

    public abstract double calc(Env env);

}
