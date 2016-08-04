package com.nuclearthinking.myheroagency.model.skills.funcs;

import com.nuclearthinking.myheroagency.model.skills.Env;
import com.nuclearthinking.myheroagency.model.skills.Stats;
import com.nuclearthinking.myheroagency.model.skills.conditions.Condition;

/**
 * Created by Izonami on 03.08.2016.
 */
public abstract class Func implements Comparable<Func>{

    public static final Func[] EMPTY_FUNCTION_SET = new Func[0];

    public final Stats _stat;
    public final int _order;
    public final Object _funcOwner;
    public final Lambda _lambda;

    protected Condition _cond;

    public Func(final Stats stat, final int order, final Object owner) {
        this(stat, order, owner, null);
    }

    public Func(final Stats stat, final int order, final Object owner, final Lambda lambda) {
        _stat = stat;
        _order = order;
        _funcOwner = owner;
        _lambda = lambda;
    }

    public void setCondition(final Condition cond)
    {
        _cond = cond;
    }

    /**
     * Для отладки
     */
    public Condition getCondition()
    {
        return _cond;
    }

    public abstract void calc(Env env);

    @Override
    public int compareTo(final Func f) throws NullPointerException {
        return _order - f._order;
    }

}
