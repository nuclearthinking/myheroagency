package com.nuclearthinking.myheroagency.model.skills;

import com.nuclearthinking.myheroagency.model.actor.GameObject;
import com.nuclearthinking.myheroagency.model.skills.funcs.Func;
import lombok.Getter;
import lombok.val;

import java.util.ArrayList;

/**
 * Created by Izonami on 04.08.2016.
 */
public final class Calculator {

    private ArrayList<Func> functions;

    private @Getter final Stats stat;
    private @Getter final GameObject character;

    public Calculator(final Stats stat, final GameObject character) {
        this.stat = stat;
        this.character = character;
        this.functions = new ArrayList<Func>();
    }

    public int size() {
        return functions.size();
    }

    public void addFunc(final Func f) {
        functions.add(f);
    }

    public void removeFunc(final Func f) {
        int index = functions.indexOf(f);

        if(index >= 0){
            functions.remove(index);
        }
    }

    public void removeOwner(final Object owner) {
        for(val element : functions)
            if(element.getFuncOwner() == owner)
                removeFunc(element);
    }

    public void calculate(final Env env) {
        for(val func : functions)
            func.calc(env);
    }

}
