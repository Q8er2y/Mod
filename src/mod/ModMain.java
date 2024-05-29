package mod;

import mindustry.mod.Mod;
import mod.content.modLiquids;
import mod.content.modBlocks;

import arc.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.ui.dialogs.*;

public class ModMain extends Mod{

    public ModMain(){
        Log.info("Loaded JavaMod constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.image(Core.atlas.find("example-java-mod-frog")).pad(20f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

        @Override
        public void loadContent() {
            modLiquids.load();
            modBlocks.load();
            System.out.println("modLiquids and modBlocks loaded");
        }
    }