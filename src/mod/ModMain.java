package mod;

import mindustry.mod.Mod;
import mod.content.modLiquids;
import mod.content.modBlocks;

import arc.Core;
import arc.Events;
import arc.util.Log;
import arc.scene.ui.CheckBox;
import arc.scene.ui.TextButton;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.ui.dialogs.SettingsMenuDialog;
import mindustry.ui.dialogs.BaseDialog;
import arc.scene.ui.Image;

public class ModMain extends Mod {

    public ModMain(){
        Log.info("Loaded JavaMod constructor.");
    }

    @Override
    public void init() {
        // Register the custom setting "dialog-enabled" with a default value of true.
        Core.settings.defaults("dialog-enabled", true);

        // When the client is fully loaded, add the custom settings category.
        Events.on(ClientLoadEvent.class, e -> {
            Core.app.post(() -> {
                // Create a new SettingsMenuDialog.
                SettingsMenuDialog settingsDialog = new SettingsMenuDialog();
                // Add a custom category for your mod's settings.
                settingsDialog.addCategory("My Mod Settings", table -> {
                    // Create a checkbox manually since checkPref doesn't return one.
                    CheckBox dialogCheckbox = new CheckBox("Dialog Enabled");
                    dialogCheckbox.setChecked(Core.settings.getBool("dialog-enabled"));
                    // Save the setting when the checkbox changes.
                    dialogCheckbox.changed(() -> {
                        Core.settings.put("dialog-enabled", dialogCheckbox.isChecked());
                    });
                    table.add(dialogCheckbox);
                    table.row();

                    // Create the "Show Image Dialog" button.
                    TextButton dialogButton = table.button("Show Image Dialog", () -> {
                        new MyImageDialog().show();
                    }).get();
                    // Set the initial enabled/disabled state of the button.
                    dialogButton.setDisabled(!Core.settings.getBool("dialog-enabled"));

                    // Listen for changes on the checkbox to update the button's state.
                    dialogCheckbox.changed(() -> {
                        Core.settings.put("dialog-enabled", dialogCheckbox.isChecked());
                        dialogButton.setDisabled(!dialogCheckbox.isChecked());
                    });
                });
            });
        });
    }

    @Override
    public void loadContent() {
        modLiquids.load();
        modBlocks.load();

        Log.info("All content loaded");
    }

    // Inner class for the image dialog.
    public static class MyImageDialog extends BaseDialog {
        public MyImageDialog(){
            super("Image Dialog");
            // Load the image from the atlas. Ensure "thyendisnow" is added to your atlas.
            Image image = new Image(Core.atlas.find("thyendisnow"));
            // Add the image and text to the dialog.
            cont.add(image).size(200f, 200f).pad(10f);
            cont.row();
            cont.add("This is thyendisnow.png").pad(10f);
            cont.row();
            cont.button("Close", this::hide).size(150f, 50f);
        }
    }
}
