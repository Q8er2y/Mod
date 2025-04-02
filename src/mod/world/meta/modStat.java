package mod.world.meta;

import mindustry.world.meta.*;

public class modStat{
    public static final Stat

    friction = new Stat("mod-friction", modStatCat.torque),
    inertia = new Stat("mod-inertia", modStatCat.torque),
    maxSpeed = new Stat("mod-maxspeed", modStatCat.torque),
    maxTorque = new Stat("mod-maxtorque", modStatCat.torque),

    emissivity = new Stat("mod-emissiveness", modStatCat.heat),
    heatCapacity = new Stat("mod-heatcapacity", modStatCat.heat),
    heatConductivity = new Stat("mod-heatconductivity", modStatCat.heat),
    maxTemperature = new Stat("mod-maxtemp", modStatCat.heat);

}
