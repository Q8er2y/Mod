package mod.ui.dialogs;

import arc.Core;
import mindustry.Vars;


public class ConsoleSettings {

    public static void init() {
        Vars.ui.settings.addCategory("Mod Settings", root -> {


            root.checkPref("mod.startup-dialog", true);
        });
    }
    public boolean setting1() {
        return Core.settings.getBool("mod.startup-dialog", true);
    }

}