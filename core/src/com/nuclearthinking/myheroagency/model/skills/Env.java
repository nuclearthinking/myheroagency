package com.nuclearthinking.myheroagency.model.skills;

import com.nuclearthinking.myheroagency.model.entity.components.PlayerComponent;
import lombok.*;

/**
 * Created by Izonami on 03.08.2016.
 */
@RequiredArgsConstructor
@NoArgsConstructor
public final class Env {

    @NonNull private @Getter @Setter PlayerComponent character;
    private @Getter @Setter PlayerComponent target;
    private @Getter @Setter double value;

}
