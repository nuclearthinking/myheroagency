package com.nuclearthinking.myheroagency.model.skills;

import com.nuclearthinking.myheroagency.model.actor.GameObject;
import lombok.*;

/**
 * Created by Izonami on 03.08.2016.
 */
@RequiredArgsConstructor
@NoArgsConstructor
public final class Env {

    @NonNull private @Getter @Setter GameObject character;
    private @Getter @Setter GameObject target;
    private @Getter @Setter double value;

}
