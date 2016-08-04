package com.nuclearthinking.myheroagency.model.skills;

import com.nuclearthinking.myheroagency.model.actor.GameObject;
import com.nuclearthinking.myheroagency.model.skills.funcs.Func;
import com.nuclearthinking.myheroagency.utils.ArrayUtil;

/**
 * Created by Izonami on 04.08.2016.
 */
public final class Calculator {

    /** Table of Func object */
    private Func[] _functions;

    private double _base;

    public final Stats _stat;
    public final GameObject _character;

    /**
     * Constructor of Calculator (Init value : emptyFuncs).
     */
    public Calculator(final Stats stat, final GameObject character)
    {
        _stat = stat;
        _character = character;
        _functions = ArrayUtil.EMPTY_FUNCTION_SET;
    }

    /**
     * Return the number of Funcs in the Calculator.<BR>
     * <BR>
     */
    public int size()
    {
        return _functions.length;
    }

    /**
     * Add a Func to the Calculator.<BR>
     * <BR>
     */
    public void addFunc(final Func f)
    {
        _functions = ArrayUtil.add(_functions, f);
        ArrayUtil.eqSort(_functions);
    }

    /**
     * Remove a Func from the Calculator.<BR>
     * <BR>
     */
    public void removeFunc(final Func f)
    {
        _functions = ArrayUtil.remove(_functions, f);
        if(_functions.length == 0)
            _functions = ArrayUtil.EMPTY_FUNCTION_SET;
        else
            ArrayUtil.eqSort(_functions);
    }

    /**
     * Remove each Func with the specified owner of the Calculator.<BR>
     * <BR>
     */
    public void removeOwner(final Object owner)
    {
        final Func[] funcs = _functions;
        for(final Func element : funcs)
            if(element._funcOwner == owner)
                removeFunc(element);
    }

    /**
     * Run each Func of the Calculator.<BR>
     * <BR>
     */
    public void calc(final Env env)
    {
        final Func[] funcs = _functions;
        _base = env.value;
        for(final Func func : funcs)
            if(func.getCondition() == null)
                func.calc(env);
    }

}
