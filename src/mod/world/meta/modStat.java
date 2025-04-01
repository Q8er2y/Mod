package mod.world.meta;

import mindustry.world.meta.*;

public class modStat{
    public static final Stat

    friction = new Stat("mod-friction", modStatCat.torque),
    inertia = new Stat("mod-inertia", modStatCat.torque),
    maxSpeed = new Stat("mod-maxspeed", modStatCat.torque),
    maxTorque = new Stat("mod-maxtorque", modStatCat.torque),

    emissivity = new Stat("unity-emissiveness", modStatCat.heat),
    heatCapacity = new Stat("unity-heatcapacity", modStatCat.heat),
    heatConductivity = new Stat("unity-heatconductivity", modStatCat.heat),
    maxTemperature = new Stat("unity-maxtemp", modStatCat.heat);

}
