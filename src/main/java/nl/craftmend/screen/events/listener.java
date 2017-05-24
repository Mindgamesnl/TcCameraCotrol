package nl.craftmend.screen.events;

import nl.craftmend.screenManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

/**
 * Created by Mindgamesnl-laptop on 10-5-2017.
 */

public class listener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        screenManager.reset(e.getPlayer());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (screenManager.get(e.getPlayer()) != null) {
            e.setCancelled(true);
            screenManager.get(e.getPlayer()).enter();
        }
    }

}