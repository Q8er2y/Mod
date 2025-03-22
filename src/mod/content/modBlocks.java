package mod.content;
import arc.graphics.Color;
import mindustry.world.blocks.liquid.Conduit;
import mod.content.modLiquids;
import mindustry.content.Liquids;
import mod.content.modFx;
import arc.graphics.Color;
import arc.graphics.gl.Shader;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.entities.pattern.ShootMulti;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.distribution.BufferedItemBridge;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.distribution.Router;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.environment.StaticWall;
import mindustry.world.blocks.environment.SteamVent;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.liquid.LiquidBridge;
import mindustry.world.blocks.liquid.LiquidRouter;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.units.UnitCargoLoader;
import mindustry.world.blocks.units.UnitCargoUnloadPoint;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
public class modBlocks {
    public static Block
            //power
            steamTurbine,
    //liquids
    liquidPipe, liquidRouter, liquidBridge, liquidJunction;

    public static void load() {

        steamTurbine = new ConsumeGenerator("steam-turbine") {{
                group = BlockGroup.liquids;
                powerProduction = 3f / 9f;
                generateEffect = modFx.steamturbinegenerate;
                effectChance = 0.6f;
                size = 3;
                ambientSound = Sounds.hum;
                ambientSoundVolume = 0.3f;
                solid = true;

                drawer = new DrawMulti(new DrawDefault(), new DrawBlurSpin("-rotator", 9f) {{
                    blurThresh = 0.10f;


                    hasLiquids = true;
                    outputLiquid = new LiquidStack(Liquids.water, 0.25f);
                    consumeLiquid(modLiquids.steam, 1f);
                    liquidCapacity = 20f;
                    fogRadius = 2;
                    alwaysUnlocked = true;
                }});
                liquidPipe = new Conduit("liquid-pipe") {{
                    health = 100;
                    botColor = Color.valueOf("262525");
                    bridgeReplacement = liquidBridge;
                    junctionReplacement = liquidJunction;
                    liquidCapacity = 10f;
                    liquidPressure = 10f;
                    size = 1;
                }};
                liquidRouter = new LiquidRouter("liquid-router") {{
                    health = 143;
                    size = 1;
                }};

                liquidBridge = new LiquidBridge("liquid-bridge") {{
                    fadeIn = moveArrows = false;
                    arrowSpacing = 6f;
                    range = 5;
                    hasPower = false;
                }};

            }};
    }
}


