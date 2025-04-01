package mod.content.custom;

import arc.Core;
import arc.graphics.g2d.TextureRegion;
import arc.struct.Seq;
import mindustry.type.Item;

public class Modifier {
        public String name;
        public Seq<Item> cost = new Seq<>();
        public Seq<Integer> costPrices;
        public TextureRegion texture;

        public Modifier(String name, Seq<Item> items, String texturePath, Seq<Integer> costPrices) {
            this.name = name;
            this.costPrices = costPrices;
            this.cost = items;
            this.texture = Core.atlas.find(texturePath);
        }
    private final Modifier[] modifiers = {
            new Modifier("speed",
                    new Seq<Item>().addAll(),
                    "mod-speed-icon-I",
                    new Seq<Integer>().addAll(1, 5)
            )
    };
}
