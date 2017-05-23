package nl.parkcircle.helpers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Mindgamesnl-laptop on 10-5-2017.
 */
public class playerHelper {

    public static Player getPlayerByName(String name) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

}
