package com.nuclearthinking.myheroagency.model.skills;

import com.nuclearthinking.myheroagency.model.actor.GameObject;
import com.nuclearthinking.myheroagency.model.skills.funcs.Func;

import java.util.ArrayList;

/**
 * Created by Izonami on 04.08.2016.
 */
public final class Calculator {

    private ArrayList<Func> _functions;

    public final Stats _stat;
    public final GameObject _character;

    public Calculator(final Stats stat, final GameObject character) {
        _stat = stat;
        _character = character;
        _functions = new ArrayList<Func>();
    }

    public int size() {
        return _functions.size();
    }

    public void addFunc(final Func f) {
        _functions.add(f);
    }

    public void removeFunc(final Func f) {
        int index = _functions.indexOf(f);

        if(index >= 0){
            _functions.remove(index);
        }
    }


    public void removeOwner(final Object owner) {
        for(final Func element : _functions)
            if(element._funcOwner == owner)
                removeFunc(element);
    }

    public void calculate(final Env env) {
        for(final Func func : _functions)
            func.calc(env);
    }

}
