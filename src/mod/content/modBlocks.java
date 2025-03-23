package mod.content;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;

import mindustry.world.blocks.power.PowerGenerator;


public class modBlocks {
    public static Block exampleWall;

    public static void load() {
        exampleWall = new PowerGenerator("steam-turbine") {{
            requirements(Category.power, ItemStack.with());
            size = 1; // 1x1 block size
            health = 400; // Wall health
        }};
    }
}
