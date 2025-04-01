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
import mod.world.blocks.distribution.DriveBelt;
import mod.world.blocks.distribution.DriveShaft;
import mod.world.blocks.distribution.SimpleTransmission;
import mod.world.blocks.power.*;
import mod.world.blocks.production.TorqueDrill;
import mod.world.graph.*;
import mod.world.meta.*;

import mindustry.world.blocks.power.PowerGenerator;

import static mindustry.content.Items.*;
import static mindustry.ctype.ContentType.item;
import static mindustry.logic.LAccess.config;
import static mindustry.type.ItemStack.with;


public class modBlocks {
    public static Block
    //power - kinetic
    crankShaft, windTurbine, rotaryWaterExtractor, flywheel, torqueSource,
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
            config.nodeConfig.put(TorqueGraph.class, b -> new TorqueGraphNode(0.02f, 8f, 6,60,b));
            config.fixedConnection(TorqueGraph.class, 0, 0, 1, 0);
        }};

        kineticDrill = new TorqueDrill("auger-drill"){{
            health = 2600;
            size = 3;
            tier = 3;
            drillTime = 400;
            requirements(Category.production, with(Items.lead, 60, Items.copper, 150));
            consumeLiquid(Liquids.water, 0.08f).boost();

            config.nodeConfig.put(TorqueGraph.class, b -> new TorqueGraphNode(0.13f, 50f, 40,b));
            config.fixedConnection(TorqueGraph.class, 0, 1, 0,  0, 0, 0,  0, 1, 0,  0, 0, 0);
        }};
    }
}
