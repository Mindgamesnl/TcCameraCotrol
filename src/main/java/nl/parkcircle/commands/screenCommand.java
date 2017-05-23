package nl.parkcircle.commands;

import nl.parkcircle.helpers.playerHelper;
import nl.parkcircle.objects.playerScreen;
import nl.parkcircle.screenManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mindgamesnl-laptop on 10-5-2017.
 */

public class screenCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("screenAPI.set")) {
            if (args[0].equalsIgnoreCase("set")) {
                //screen set yaw pitch name
                screenManager.set(playerHelper.getPlayerByName(args[3]), Float.valueOf(args[1]), Float.valueOf(args[2]), false);
            } else if (args[0].equalsIgnoreCase("tp")) {
                //screen tp playerHelper.getPlayerByName(args[1]) x y z
                Player p = (Player) sender;
                Location loc = new Location(p.getWorld(), 190f, 190f, Double.valueOf(args[4]));
                screenManager.get(p).teleport(loc);
            } else if (args[0].equalsIgnoreCase("reset")) {
                //screen reset name
                Player p = playerHelper.getPlayerByName(args[2]);
                screenManager.reset(p);
            }
        } else {
            sender.sendMessage("how about nah");
        }
        return true;
    }
}
