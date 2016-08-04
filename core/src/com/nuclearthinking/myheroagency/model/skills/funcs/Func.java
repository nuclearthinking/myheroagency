package com.nuclearthinking.myheroagency.model.skills.funcs;

import com.nuclearthinking.myheroagency.model.skills.Env;
import com.nuclearthinking.myheroagency.model.skills.Stats;

/**
 * Created by Izonami on 03.08.2016.
 */
public abstract class Func implements Comparable<Func>{

    public final Stats _stat;
    public final int _order;
    public final Object _funcOwner;

    public Func(final Stats stat, final int order, final Object owner) {
        _stat = stat;
        _order = order;
        _funcOwner = owner;
    }

    public abstract void calc(Env env);

    @Override
    public int compareTo(final Func f) throws NullPointerException {
        return _order - f._order;
    }

}
