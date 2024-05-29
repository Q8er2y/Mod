package mod.content;

import arc.graphics.g2d.Fill;
import arc.math.Rand;
import arc.math.geom.Vec2;
import mindustry.entities.Effect;
import mindustry.graphics.Pal;
import mod.graphics.modPal;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Draw.alpha;

public class modFx {
    public static final Rand rand = new Rand();
    public static final Vec2 v = new Vec2();

    public static final Effect

            steamturbinegenerate = new Effect(100, e -> {
        color(modPal.steam);
        alpha(e.fslope() * 0.8f);

        rand.setSeed(e.id);
        for (int i = 0; i < 3; i++) {
            v.trns(rand.random(360f), rand.random(e.finpow() * 14f)).add(e.x, e.y);
            Fill.circle(v.x, v.y, rand.random(1.4f, 3.4f));
        }
    });
}
