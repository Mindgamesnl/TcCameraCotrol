package nl.parkcircle;

import nl.parkcircle.commands.screenCommand;
import nl.parkcircle.events.listener;
import nl.parkcircle.objects.playerScreen;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Mindgamesnl-laptop on 10-5-2017.
 */

public final class ScreenRotationAPI extends JavaPlugin {

    public static ScreenRotationAPI ScreenRotationAPI;

    public static ScreenRotationAPI getPl() {
        return ScreenRotationAPI;
    }

    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new listener(), this);
        ScreenRotationAPI = this;
        getCommand("screenrotationapi").setExecutor(new screenCommand());
    }

    public void onDisable() {
        for (playerScreen ps : screenManager.list.values()) {
            ps.exit();
        }
    }
}
