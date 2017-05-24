package nl.craftmend;

import com.bergerkiller.bukkit.tc.signactions.SignAction;
import nl.craftmend.screen.events.listener;
import nl.craftmend.screen.objects.playerScreen;
import nl.craftmend.traincarts.cameraSign;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Mindgamesnl-laptop on 10-5-2017.
 */

public final class TcCamControl extends JavaPlugin {

    public static TcCamControl TcCamControl;

    public static TcCamControl getPl() {
        return TcCamControl;
    }

    public void onEnable() {
        //Bukkit.getServer().getPluginManager().registerEvents(new listener(), this);
        TcCamControl = this;
        SignAction.register(new cameraSign());
    }

    public void onDisable() {
        for (playerScreen ps : screenManager.list.values()) {
            ps.exit();
        }
    }
}
