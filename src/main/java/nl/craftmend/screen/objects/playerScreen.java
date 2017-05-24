package nl.craftmend.screen.objects;

import nl.craftmend.TcCamControl;
import nl.craftmend.screen.helpers.PacketHelper;
import nl.craftmend.screenManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.EulerAngle;

import java.util.UUID;

/**
 * Created by Mindgamesnl-laptop on 10-5-2017.
 */

public class playerScreen {

    private Player p;
    private ArmorStand as;
    private Boolean allowExit = false;
    private GameMode gameMode = GameMode.ADVENTURE;
    private Float pitch = 0f;
    private Float yaw = 0f;
    private Entity minecart;
    private UUID cartuuid;

    public playerScreen(Player p, Float yaw, Float pitch, UUID id) {
        this.minecart = p.getVehicle();
        this.as = (ArmorStand)p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
        this.as.setVisible(false);
        this.gameMode = p.getGameMode();
        this.p = p;
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamemode 3 " + this.p.getName());
        this.minecart.setPassenger(this.as);
        enter();

        this.cartuuid = id;
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwner(p.getName());
        skull.setItemMeta(skullMeta);
        this.as.setHelmet(skull);
        this.as.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
        this.as.setBoots(new ItemStack(Material.LEATHER_BOOTS, 1));
        this.as.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS, 1));

        Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(TcCamControl.getPl(), new Runnable() {
            public void run() {
                setPitch(pitch, yaw);

            }
        }, 8);
    }

    public void setYaw(Float yaw) {
        this.yaw = yaw;
        update();
    }

    public void setPitch(Float pitch, Float yaw) {
        this.pitch = pitch;
        this.yaw = yaw;
        update();

    }

    public UUID getId() {
        return this.cartuuid;
    }

    public Player getPlayer() {
        return this.p;
    }

    public void exit() {
        this.p.setGameMode(this.gameMode);
        this.minecart.setPassenger(this.p);
        this.as.setHealth(0);
        this.as.remove();
    }

    public void enter() {
        if (this.p.getVehicle() == null) {
            this.p.setSpectatorTarget(this.as);
        }
    }



    public void update() {
        PacketHelper.setLook(this.yaw.floatValue(), this.pitch.floatValue(), this.as, this.p);
        enter();

    }
}
