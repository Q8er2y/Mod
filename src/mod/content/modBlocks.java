package mod.content;
import mod.content.modLiquids;
import mod.content.modFx;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.draw.DrawBlurSpin;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawMulti;
import mindustry.world.meta.BlockGroup;

public class modBlocks {
    public static Block
            //power
            steamTurbine;

    public static void load(){
        steamTurbine = new ConsumeGenerator("steam-turbine") {{
            group = BlockGroup.liquids;
            powerProduction = 3f / 9f;
            generateEffect = modFx.steamturbinegenerate;
            effectChance = 0.6f;
            size = 3;
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.1f;

            drawer = new DrawMulti(new DrawDefault(), new DrawBlurSpin("-rotator", 0.6f * 9f) {{
                blurThresh = 0.01f;


            hasLiquids = true;
            outputLiquid = new LiquidStack(Liquids.water, 0.25f);
            consumeLiquid(modLiquids.steam, 1f);
            liquidCapacity = 20f;
            fogRadius = 3;
            alwaysUnlocked = true;
        }});
        }};
    }
}