package com.nuclearthinking.myheroagency.model.skills.funcs;

import com.nuclearthinking.myheroagency.model.skills.Env;
import com.nuclearthinking.myheroagency.model.skills.Stats;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Izonami on 03.08.2016.
 */
@Slf4j
public abstract class Func implements Comparable<Func>{

    private @Getter final Stats stat;
    private @Getter final int order;
    private @Getter final Object funcOwner;

    public Func(@NonNull final Stats stat, final int order, final Object funcOwner) {
        this.stat = stat;
        this.order = order;
        this.funcOwner = funcOwner;
    }

    public abstract void calc(Env env);

    @Override
    public int compareTo(@NonNull final Func f){
        return order - f.order;
    }

}
