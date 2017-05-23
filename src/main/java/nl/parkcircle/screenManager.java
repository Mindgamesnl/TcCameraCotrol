package nl.parkcircle;

import nl.parkcircle.objects.playerScreen;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Mindgamesnl-laptop on 10-5-2017.
 */

public class screenManager {
    public static HashMap<String, playerScreen> list = new HashMap();

    public static void set(Player p, Float yaw, Float pitch, Boolean allowexit) {
        if (list.containsKey(p)) {
            playerScreen screen = list.get(p);
            screen.setPitch(pitch);
            screen.setYaw(yaw);
            screen.allowExit = allowexit;
        } else {
            list.put(p.getName(), new playerScreen(p, yaw, pitch, allowexit));
        }

    }

    public static void reset(Player p) {
        list.get(p).exit();
    }

    public static playerScreen get(Player p) {
        return (playerScreen)list.get(p);
    }
}
