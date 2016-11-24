package com.nuclearthinking.myheroagency.model.stat;

import com.nuclearthinking.myheroagency.model.actor.base.GameActor;
import lombok.*;

/**
 * Created by Izonami on 03.08.2016.
 */
@RequiredArgsConstructor
@NoArgsConstructor
public final class Env {

    @NonNull private @Getter @Setter GameActor character;
    private @Getter @Setter GameActor target;
    private @Getter @Setter double value;

}
