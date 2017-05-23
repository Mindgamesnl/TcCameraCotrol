package nl.parkcircle.objects;

import nl.parkcircle.ScreenRotationAPI;
import nl.parkcircle.helpers.PacketHelper;
import nl.parkcircle.screenManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

/**
 * Created by Mindgamesnl-laptop on 10-5-2017.
 */

public class playerScreen {

    public Player p;
    public ArmorStand as;
    public Boolean allowExit = false;
    public GameMode gameMode = GameMode.ADVENTURE;
    public Float pitch = 0f;
    public Float yaw = 0f;

    public playerScreen(Player p, Float yaw, Float pitch, Boolean allowExit) {
        this.as = (ArmorStand)p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
        this.as.setVisible(false);
        this.gameMode = p.getGameMode();
        this.p = p;
        this.allowExit = allowExit;
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamemode 3 " + this.p.getName());
        this.p.setSpectatorTarget(this.as);




        Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(ScreenRotationAPI.getPl(), new Runnable() {
            public void run() {
                setPitch(pitch);
                setYaw(yaw);
            }
        }, 10);
    }

    public void teleport(Location loc) {
        as.teleport(loc);
    }

    public void setYaw(Float yaw) {
        this.yaw = yaw;
        update();
    }

    public void setPitch(Float pitch) {
        this.pitch = pitch;
        update();
    }

    public void exit() {
        Bukkit.broadcastMessage("PIZZA IS GOED!");
        this.p.setGameMode(this.gameMode);
        this.p.teleport(this.p.getLocation());
        this.as.setHealth(0);
        this.as.remove();
        screenManager.list.remove(this.p);

    }

    public void update() {
        PacketHelper.setLook(this.yaw.floatValue(), this.pitch.floatValue(), this.as, this.p);
    }
}
