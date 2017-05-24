package nl.craftmend;

import nl.craftmend.screen.objects.playerScreen;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Mindgamesnl-laptop on 10-5-2017.
 */

public class screenManager {
    public static HashMap<String, playerScreen> list = new HashMap();

    public static void set(Player p, Float yaw, Float pitch, UUID id) {
        if (list.containsKey(p.getName())) {
            playerScreen screen = list.get(p.getName());
            screen.setPitch(pitch, yaw);
        } else {
            list.put(p.getName(), new playerScreen(p, yaw, pitch, id));
        }

    }

    public static void reset(Player p) {
        list.get(p.getName()).exit();
        list.remove(p.getName());
    }

    public static playerScreen get(Player p) {
        return (playerScreen)list.get(p.getName());
    }
}
