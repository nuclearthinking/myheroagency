package com.nuclearthinking.myheroagency.model.stat.funcs;

import com.nuclearthinking.myheroagency.model.stat.Env;
import com.nuclearthinking.myheroagency.model.stat.Stats;
import lombok.Getter;
import lombok.NonNull;

/**
 * Created by Izonami on 03.08.2016.
 */
public abstract class Func implements Comparable<Func>{

    private @Getter final Stats stat;
    private @Getter final int order;
    private @Getter final Object funcOwner;

    public Func(@NonNull final Stats stat, final int order, final Object funcOwner) {
        this.stat = stat;
        this.order = order;
        this.funcOwner = funcOwner;
    }

    public abstract void calc(@NonNull final Env env);

    @Override
    public int compareTo(@NonNull final Func f){
        return order - f.order;
    }

}
