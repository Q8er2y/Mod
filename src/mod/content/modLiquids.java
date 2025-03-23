package mod.content;

import arc.graphics.Color;
import mindustry.type.Liquid;

public class modLiquids {
    public static Liquid steam;

    public static void load() {
        steam = new Liquid("steam") {{
            temperature = 1.0f; // High temperature, steam-like behavior
            heatCapacity = 0.5f; // Lower heat capacity for quicker heat transfer
            viscosity = 0.2f; // Steam has low viscosity
            color = Color.valueOf("ffffff"); // White color, representing steam
            gas = true; // Defines the liquid as a gas
            lightColor = Color.valueOf("f0f0f0").a(0.6f); // Light white, slightly transparent
            explosiveness = 0.1f; // Slightly explosive (optional)
        }};
    }
}
