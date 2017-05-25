package nl.craftmend.traincarts;

import com.bergerkiller.bukkit.common.entity.type.CommonMinecart;
import com.bergerkiller.bukkit.tc.Permission;
import com.bergerkiller.bukkit.tc.controller.MinecartMember;
import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import com.bergerkiller.bukkit.tc.events.SignChangeActionEvent;
import com.bergerkiller.bukkit.tc.signactions.SignAction;
import com.bergerkiller.bukkit.tc.signactions.SignActionType;
import nl.craftmend.TcCamControl;
import nl.craftmend.screen.objects.playerScreen;
import nl.craftmend.screenManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by mats on 24-5-2017.
 */
public class cameraSign extends SignAction {

    public boolean match(SignActionEvent e) {

        if ((e.getLine(1).contains("camera")) && (!e.getLine(2).isEmpty())) {
            return true;
        } else {
            return false;
        }
    }

    public void execute(SignActionEvent info) {
        if (info.isPowered()) {
            if (info.isTrainSign()) {
                if ((info.isAction(new SignActionType[]{SignActionType.REDSTONE_ON, SignActionType.GROUP_ENTER})) && (info.hasGroup())) {
                    for (MinecartMember<?> member : info.getGroup()) {
                        if (!((CommonMinecart) member.getEntity()).isEmpty()) {
                            prosess(member, info);
                        }
                    }
                }
            } else if (info.isCartSign()) {
                if ((info.isAction(new SignActionType[]{SignActionType.REDSTONE_ON, SignActionType.MEMBER_ENTER})) && (info.hasMember())) {
                    if (!((CommonMinecart) info.getMember().getEntity()).isEmpty()) {
                        prosess(info.getMember(), info);
                    }
                }
            } else if (info.isRCSign()) {
                if (info.isAction(new SignActionType[]{SignActionType.REDSTONE_ON})) {
                    for (MinecartMember<?> member : info.getGroup()) {
                        if (!((CommonMinecart) member.getEntity()).isEmpty()) {
                            prosess(member, info);
                        }
                    }
                }
            }
        }
    }

    public void prosess(MinecartMember<?> member, SignActionEvent info) {

        if (info.getLine(3).contains("smooth:")) {

            if (((CommonMinecart) member.getEntity()).getPassenger() instanceof Player) {

                final Integer[] stepsFin = {0};
                Integer schedule = 0;

                Float startYaw = ((CommonMinecart) member.getEntity()).getPlayerPassenger().getLocation().getYaw();
                Float startPitch = ((CommonMinecart) member.getEntity()).getPlayerPassenger().getLocation().getPitch();

                Float targetYaw = Float.parseFloat(info.getLine(2).split(":")[0]);
                Float targetPitch = Float.parseFloat(info.getLine(2).split(":")[1]);

                Float relativeYaw = (startYaw + targetYaw) / Integer.parseInt(info.getLine(3).replace("smooth:", ""));
                Float relativePitch = (startPitch + targetPitch) / Integer.parseInt(info.getLine(3).replace("smooth:", ""));

                final Float[] curY = new Float[1];
                final Float[] curP = new Float[1];

                UUID id = ((CommonMinecart) member.getEntity()).getUniqueId();
                Player p = ((CommonMinecart) member.getEntity()).getPlayerPassenger();
                Integer finalSchedule = schedule;
                Integer finalSchedule1 = schedule;
                schedule = Bukkit.getScheduler().scheduleSyncRepeatingTask(TcCamControl.getPl(), new Runnable() {
                    @Override
                    public void run() {
                        Boolean changed = false;
                        stepsFin[0]++;
                        if (!(Math.round((relativeYaw * stepsFin[0])) > targetYaw)) {
                            curY[0] = Float.valueOf(Math.round(relativeYaw * stepsFin[0]));
                            changed = true;
                        }

                        if (!(Math.round((relativePitch * stepsFin[0])) > targetPitch)) {
                            curP[0] = Float.valueOf(Math.round((relativePitch * stepsFin[0])));
                            changed = true;
                        }

                        if (!changed) {
                            Bukkit.getScheduler().cancelTask(finalSchedule1);
                        } else {
                            screenManager.set(p, curY[0], curP[0], id);
                        }
                     }
                }, 2, 2);

            }

        } else {
            if (((CommonMinecart) member.getEntity()).getPassenger() instanceof Player) {
                if (info.getLine(2).equalsIgnoreCase("reset")) {
                } else {
                    Float yaw = Float.parseFloat(info.getLine(2).split(":")[0]);
                    Float pitch = Float.parseFloat(info.getLine(2).split(":")[1]);
                    screenManager.set(((CommonMinecart) member.getEntity()).getPlayerPassenger(), yaw, pitch, ((CommonMinecart) member.getEntity()).getUniqueId());
                }
            } else {
                if (info.getLine(2).equalsIgnoreCase("reset")) {
                    for (playerScreen screen : screenManager.list.values()) {
                        if (screen.getId() == ((CommonMinecart) info.getMember().getEntity()).getUniqueId()) {
                            screenManager.reset(screen.getPlayer());

                        }
                    }
                }
                for (playerScreen screen : screenManager.list.values()) {
                    if (screen.getId() == ((CommonMinecart) member.getEntity()).getUniqueId()) {
                        Float yaw = Float.parseFloat(info.getLine(2).split(":")[0]);
                        Float pitch = Float.parseFloat(info.getLine(2).split(":")[1]);
                        screenManager.set(screen.getPlayer(), yaw, pitch, screen.getId());
                    }
                }
            }
        }


    }

    public boolean build(SignChangeActionEvent event)
    {
        if (event.isCartSign()) {
            return handleBuild(event, Permission.BUILD_EFFECT, "camera setter", "Do camera magic");
        }
        if (event.isTrainSign()) {
            return handleBuild(event, Permission.BUILD_EFFECT, "camera setter", "Do camera magic");
        }
        if (event.isRCSign()) {
            return handleBuild(event, Permission.BUILD_EFFECT, "camera setter", "Do camera magic");
        }
        return false;
    }

}
