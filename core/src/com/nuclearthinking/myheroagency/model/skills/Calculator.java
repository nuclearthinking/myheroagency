package com.nuclearthinking.myheroagency.model.skills;

import com.nuclearthinking.myheroagency.controller.systems.ActorSystem;
import com.nuclearthinking.myheroagency.model.skills.funcs.Func;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

import java.util.ArrayList;

/**
 * Created by Izonami on 04.08.2016.
 */
public final class Calculator {

    private ArrayList<Func> functions;

    private @Getter final Stats stat;
    private @Getter final ActorSystem character;

    public Calculator(@NonNull final Stats stat, @NonNull final ActorSystem character) {
        this.stat = stat;
        this.character = character;
        this.functions = new ArrayList<Func>();
    }

    public int size() {
        return functions.size();
    }

    public void addFunc(@NonNull final Func f) {
        functions.add(f);
    }

    public void removeFunc(@NonNull final Func f) {
        int index = functions.indexOf(f);

        if(index >= 0){
            functions.remove(index);
        }
    }

    public void removeOwner(@NonNull final Object owner) {
        for(val element : functions)
            if(element.getFuncOwner() == owner)
                removeFunc(element);
    }

    public void calculate(@NonNull final Env env) {
        for(val func : functions)
            func.calc(env);
    }

}
