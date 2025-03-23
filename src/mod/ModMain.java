package mod;

import arc.util.Time;
import mindustry.mod.Mod;
import mindustry.ui.dialogs.BaseDialog;
import mod.content.modLiquids;
import mod.content.modBlocks;
import arc.*;
import arc.Events;
import arc.util.Log;
import mindustry.game.EventType.ClientLoadEvent;
import mod.ui.dialogs.ConsoleSettings;


public class ModMain extends Mod {
    public ModMain(){
    if (Core.settings.getBool("mod.startup-dialog"))
        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("Welcome!");
                dialog.cont.add("Mod was loaded successfully!").row();
                dialog.cont.image(Core.atlas.find("mod-minos")).pad(20f).row();
                dialog.cont.add("This dialog can be turned off in Settings").row();
                dialog.cont.button("Okay", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent() {
        // Load liquids and blocks
        modLiquids.load();
        modBlocks.load();

        Log.info("All liquids and blocks loaded");

        // Listen for the client load event to initialize the console settings
        Events.on(ClientLoadEvent.class, event -> {
            ConsoleSettings.init();
            Log.info("Console settings initialized after client load.");
        });
    }
}
