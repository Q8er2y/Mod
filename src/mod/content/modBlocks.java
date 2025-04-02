package mod.content;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.Item;
import mindustry.world.meta.BuildVisibility;
import mod.content.custom.Modifier;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mod.world.blocks.*;
import mod.world.blocks.distribution.*;
import mod.world.blocks.power.*;
import mod.world.blocks.production.TorqueDrill;
import mod.world.graph.*;
import mod.world.graph.HeatGraphNode;
import mod.world.meta.*;

import mindustry.world.blocks.power.PowerGenerator;

import static mindustry.content.Items.*;
import static mindustry.ctype.ContentType.item;
import static mindustry.logic.LAccess.config;
import static mindustry.type.ItemStack.with;


public class modBlocks {
    public static Block
    //power - kinetic
    crankShaft, flywheel, torqueSource, steamgenerator, heatSource, heatPipe, combustionHeater, seebeckGenerator, smallRadiator,
    //other
    kineticDrill, driveShaft, driveBeltSmall, driveBeltLarge, shaftRouter, smallTransmission, torqueMeter;

    public static void load() {

        driveShaft = new DriveShaft("drive-shaft"){{
            health = 300;

            config.nodeConfig.put(TorqueGraph.class, b -> new TorqueGraphNode(0.005f, 3f, b));
            config.fixedConnection(TorqueGraph.class, 1, 0, 1, 0);
            requirements(Category.power, with(Items.copper, 10, Items.lead, 10));
            underBullets = true;
        }};
        shaftRouter = new GenericGraphBlock("shaft-router"){{
            requirements(Category.power, with(Items.copper, 20, Items.lead, 20));
            health = 350;
            solid = true;

            config.nodeConfig.put(TorqueGraph.class, b -> new TorqueGraphNode(0.04f, 4f, b));
            config.fixedConnection(TorqueGraph.class, 1, 1, 1, 1);
            underBullets = true;
        }};
        smallTransmission = new SimpleTransmission("small-transmission"){{
            requirements(Category.power, with(Items.copper, 20, Items.lead, 20));
            health = 1100;
            size = 2;
            config.nodeConfig.put(TorqueGraph.class, b -> new TransmissionTorqueGraphNode(0.05f, 8f, 2,b));
            config.fixedConnection(TorqueGraph.class, 0, 0, 0, 1, 0, 0, 1, 0);
            config.fixedConnection(TorqueGraph.class, 0, 0, 1, 0, 0, 0, 0, 1);

        }};
        torqueMeter = new TorqueMeter("torque-meter"){{
            requirements(Category.power, with(Items.lead, 30));
            health = 250;
            rotate = true;
            solid = true;

            config.nodeConfig.put(TorqueGraph.class, b -> new TorqueMeterGraphNode(0.01f, 5f, b));
            config.fixedConnection(TorqueGraph.class, 1, 0, 1, 0);

        }};
        driveBeltSmall = new DriveBelt("small-drive-belt"){{
            requirements(Category.power, with(Items.graphite, 20));
            health = 150;
            rotate = true;
            solid = true;
            config.nodeConfig.put(TorqueGraph.class, b -> new SimpleTransmission.TransmissionTorqueGraphNode(0.03f, 8f, 1,b));
            config.fixedConnection(TorqueGraph.class, 0, 0, 1, 0);
            config.distanceConnection(TorqueGraph.class, 1);
        }};
        driveBeltLarge = new DriveBelt("large-drive-belt"){{
            requirements(Category.power, with(Items.silicon, 40, Items.graphite, 50));
            health = 1750;
            size = 3;
            maxRange = 10;
            wheelSize = 8;
            rotate = true;
            solid = true;
            config.nodeConfig.put(TorqueGraph.class, b -> new SimpleTransmission.TransmissionTorqueGraphNode(0.05f, 30f, 1,b));
            config.fixedConnection(TorqueGraph.class, 0, 0, 0,  0, 0, 0,  0, 1, 0,  0, 0, 0);
            config.distanceConnection(TorqueGraph.class, 6);
        }};
        torqueSource = new TorqueSource("torque-source"){{
            solid = true;
            requirements(Category.power, BuildVisibility.sandboxOnly,with());
            config.nodeConfig.put(TorqueGraph.class, b -> new TorqueGraphNode(0.001f, 999999f, b));
            config.fixedConnection(TorqueGraph.class, 1, 1, 1, 1);
        }};
        crankShaft = new CrankShaft("hand-crank"){{
            requirements(Category.power, with(Items.lead, 5, Items.copper, 15));
            health = 250;
            solid = true;
            rotate = true;
            config.nodeConfig.put(TorqueGraph.class, b -> new TorqueGraphNode(0.02f, 8f, 10,60,b));
            config.fixedConnection(TorqueGraph.class, 0, 0, 1, 0);
        }};

        kineticDrill = new TorqueDrill("kinetic-drill"){{
            health = 2600;
            size = 3;
            tier = 2;
            drillTime = 600;
            requirements(Category.production, with(Items.lead, 60, Items.copper, 150));
            consumeLiquid(Liquids.water, 0.08f).boost();

            config.nodeConfig.put(TorqueGraph.class, b -> new TorqueGraphNode(0.13f, 50f, 60,b));
            config.fixedConnection(TorqueGraph.class, 0, 1, 0,  0, 0, 0,  0, 1, 0,  0, 0, 0);
        }};
        flywheel = new FlyWheel("fly-wheel"){{
            health = 1000;
            size = 3;
            requirements(Category.power, with(Items.lead, 100, Items.copper, 150));

            config.nodeConfig.put(TorqueGraph.class, b -> new TorqueGraphNode(0.1f, 100f, 100,b));
            config.fixedConnection(TorqueGraph.class, 0, 1, 0,  0, 0, 0,  0, 1, 0,  0, 0, 0);

        }};
        steamgenerator = new SteamPiston("steam-piston"){{
            health = 2500;
            size = 3;
            rotate = true;
            requirements(Category.production, with(Items.lead, 60, Items.copper, 150));
            consumeLiquid(Liquids.water, 1f);

            config.nodeConfig.put(TorqueGraph.class, b -> new TorqueGraphNode(0.2f, 50f, 100,b));
            config.fixedConnection(TorqueGraph.class, 0, 1, 0,  0, 0, 0,  0, 0, 0,  0, 0, 0);

            config.nodeConfig.put(HeatGraph.class, b -> new HeatGraphNode(b,0,1,1,2000));
            config.fixedConnection(HeatGraph.class, 0, 0, 0,  0, 0, 0,  0, 1, 0,  0, 0, 0);
        }};
        heatPipe = new HeatPipe("heat-pipe"){{
            requirements(Category.power, with(Items.copper, 10));
            health = 100;
            solid = false;
            config.nodeConfig.put(HeatGraph.class, b -> new HeatGraphNode(b, 0.005f, 0.4f, 1,2500 + HeatGraphNode.celsiusZero));
            config.fixedConnection(HeatGraph.class, 1, 1, 1, 1);

            underBullets = true;
        }};
        combustionHeater = new CombustionHeater("combustion-heater"){{
            requirements(Category.power, with(Items.lead, 70, Items.copper, 70));
            size = 2;
            rotate = true;
            health = 700;
            solid = true;
            config.nodeConfig.put(HeatGraph.class, b -> new HeatGraphNode(b, 0.01f, 0.1f, 4, 2100 + HeatGraphNode.celsiusZero, 1000 + HeatGraphNode.celsiusZero,0.015f));
            config.fixedConnection(HeatGraph.class, 1, 1,  0, 0,  0, 0,  0, 0);

        }};

        heatSource = new HeatSource("heat-source"){{
            solid = true;
            requirements(Category.power,BuildVisibility.sandboxOnly,with());
            config.nodeConfig.put(HeatGraph.class, b -> new HeatGraphNode(b,0,1,900,5000));
            config.fixedConnection(HeatGraph.class, 1,1,1,1);
        }};

        seebeckGenerator = new SeebeckGenerator("seebeck-generator"){{
            size = 3;
            rotate = true;
            health = 2200;
            solid = true;
            hasPower = true;
            config.nodeConfig.put(HeatGraph.class, b -> new HeatGraphNode(b, 0.01f, 0.01f,9, 1800 + HeatGraphNode.celsiusZero));
            config.fixedConnection(HeatGraph.class, 0,1,0,  0,0,0,  0,1,0  ,0,0,0);
            requirements(Category.power, with(Items.graphite, 30, Items.copper, 120,Items.titanium, 100));
        }};
        smallRadiator = new HeatRadiator("small-radiator"){{

            size = 2;
            rotate = true;
            health = 1100;
            solid = true;
            config.nodeConfig.put(HeatGraph.class, b -> new HeatGraphNode(b, 0.4f, 0.15f, 4,2400 + HeatGraphNode.celsiusZero));
            config.fixedConnection(HeatGraph.class, 0, 0,  1, 1,  0, 0,  1, 1);
            requirements(Category.power, with(Items.graphite, 30, Items.copper, 100));
        }};


    }
}
