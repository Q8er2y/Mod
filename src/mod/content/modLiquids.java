package mod.content;

import arc.graphics.*;
import mindustry.type.*;

public class modLiquids{
    public static Liquid steam;

    public static void load() {

        steam = new Liquid("steam", Color.valueOf("bfc4c9")){{
            heatCapacity = 0.25f;
            gasColor = Color.grays(0.9f);
            alwaysUnlocked = true;
        }};
    }
}