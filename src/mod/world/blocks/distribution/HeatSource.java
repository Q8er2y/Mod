package mod.world.blocks.distribution;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import arc.util.io.*;
import mod.graphics.*;
import mod.world.blocks.*;
import mod.world.blocks.power.TorqueSource.*;
import mod.world.graph.*;

import static mod.world.graph.HeatGraphNode.celsiusZero;

public class HeatSource extends GenericGraphBlock{
    TextureRegion heatRegion;
    TextureRegion base;
    public HeatSource(String name){
        super(name);
        configurable = true;
        config(Float.class,(HeatSourceBuild build, Float val)->{build.targettemp = val;});
    }

    @Override
    public void load(){
        super.load();
        base = loadTex("base");
        heatRegion = loadTex("heat");
    }

    public class HeatSourceBuild extends GenericGraphBuild{
        float targettemp= celsiusZero;

        @Override
        public void updateTile(){
            super.updateTile();
            heatNode().setTemp(targettemp);
        }

        @Override
        public void buildConfiguration(Table table){
            super.buildConfiguration(table);
            //placeholder.
            table.label(()-> Core.bundle.format("bar.unity-temp",
                        Strings.fixed(targettemp - celsiusZero, 1)));
            table.row();
            table.slider(0, celsiusZero+3000,10f,targettemp, true,this::configure);
        }
        Color col = new Color();
        @Override
        public void draw(){
            Draw.rect(base, this.x, this.y, 0);
            modDrawf.drawHeat(heatRegion, x, y, rotdeg(), heatNode().getTemp());
            drawTeamTop();
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(targettemp);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            targettemp = read.f();
        }

        @Override
        public Object config(){
            return targettemp;
        }
    }
}
