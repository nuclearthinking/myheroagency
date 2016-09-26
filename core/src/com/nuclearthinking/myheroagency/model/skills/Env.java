package com.nuclearthinking.myheroagency.model.skills;

import com.nuclearthinking.myheroagency.model.entity.systems.ObjectSystem;
import lombok.*;

/**
 * Created by Izonami on 03.08.2016.
 */
@RequiredArgsConstructor
@NoArgsConstructor
public final class Env {

    @NonNull private @Getter @Setter ObjectSystem character;
    private @Getter @Setter ObjectSystem target;
    private @Getter @Setter double value;

}
