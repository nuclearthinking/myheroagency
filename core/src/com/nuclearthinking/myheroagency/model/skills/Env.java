package com.nuclearthinking.myheroagency.model.skills;

import com.nuclearthinking.myheroagency.controller.systems.ActorSystem;
import lombok.*;

/**
 * Created by Izonami on 03.08.2016.
 */
@RequiredArgsConstructor
@NoArgsConstructor
public final class Env {

    @NonNull private @Getter @Setter
    ActorSystem character;
    private @Getter @Setter
    ActorSystem target;
    private @Getter @Setter double value;

}
