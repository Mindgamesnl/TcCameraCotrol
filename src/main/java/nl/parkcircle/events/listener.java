package nl.parkcircle.events;

import nl.parkcircle.screenManager;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * Created by Mindgamesnl-laptop on 10-5-2017.
 */

public class listener implements Listener {

    @EventHandler
    public void OnPlayerGameModeChange(PlayerGameModeChangeEvent e) {
        try {
            screenManager.reset(e.getPlayer());
        } catch (NullPointerException localNullPointerException) {}
    }



    public void OnPlayerQuit(PlayerQuitEvent e) {
        try {
            screenManager.reset(e.getPlayer());
        } catch (NullPointerException localNullPointerException) {}
    }
}