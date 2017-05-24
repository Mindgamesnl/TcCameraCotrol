package nl.craftmend.traincarts;

import com.bergerkiller.bukkit.common.entity.type.CommonMinecart;
import com.bergerkiller.bukkit.tc.Permission;
import com.bergerkiller.bukkit.tc.controller.MinecartMember;
import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import com.bergerkiller.bukkit.tc.events.SignChangeActionEvent;
import com.bergerkiller.bukkit.tc.signactions.SignAction;
import com.bergerkiller.bukkit.tc.signactions.SignActionType;
import nl.craftmend.screen.objects.playerScreen;
import nl.craftmend.screenManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
                            if (((CommonMinecart) member.getEntity()).getPassenger() instanceof Player) {
                                if (info.getLine(2).equalsIgnoreCase("reset")) {

                                } else {
                                    screenManager.set(((CommonMinecart) member.getEntity()).getPlayerPassenger(), Float.parseFloat(info.getLine(2)), Float.parseFloat(info.getLine(3)), ((CommonMinecart) member.getEntity()).getUniqueId());
                                }
                            } else {
                                if (info.getLine(2).equalsIgnoreCase("reset")) {
                                    for (playerScreen screen : screenManager.list.values()) {
                                        if (screen.getId() == ((CommonMinecart) member.getEntity()).getUniqueId()) {
                                            screenManager.reset(screen.getPlayer());

                                        }
                                    }
                                }
                                for (playerScreen screen : screenManager.list.values()) {
                                    if (screen.getId() == ((CommonMinecart) member.getEntity()).getUniqueId()) {
                                        screenManager.set(screen.getPlayer(), Float.parseFloat(info.getLine(2)), Float.parseFloat(info.getLine(3)), screen.getId());
                                    }
                                }
                            }


                        }
                    }
                }
            } else if (info.isCartSign()) {
                if ((info.isAction(new SignActionType[]{SignActionType.REDSTONE_ON, SignActionType.MEMBER_ENTER})) && (info.hasMember())) {
                    if (!((CommonMinecart) info.getMember().getEntity()).isEmpty()) {
                        if (((CommonMinecart) info.getMember().getEntity()).getPassenger() instanceof Player) {
                            if (info.getLine(2).equalsIgnoreCase("reset")) {

                            } else {
                                screenManager.set(((CommonMinecart) info.getMember().getEntity()).getPlayerPassenger(), Float.parseFloat(info.getLine(2)), Float.parseFloat(info.getLine(3)), ((CommonMinecart) info.getMember().getEntity()).getUniqueId());
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
                                if (screen.getId() == ((CommonMinecart) info.getMember().getEntity()).getUniqueId()) {
                                    screenManager.set(screen.getPlayer(), Float.parseFloat(info.getLine(2)), Float.parseFloat(info.getLine(3)), screen.getId());
                                }
                            }
                        }
                    }
                }
            } else if (info.isRCSign()) {
                if (info.isAction(new SignActionType[]{SignActionType.REDSTONE_ON})) {
                    for (MinecartMember<?> member : info.getGroup()) {
                        if (!((CommonMinecart) member.getEntity()).isEmpty()) {
                            if (((CommonMinecart) member.getEntity()).getPassenger() instanceof Player) {
                                if (info.getLine(2).equalsIgnoreCase("reset")) {

                                } else {
                                    screenManager.set(((CommonMinecart) member.getEntity()).getPlayerPassenger(), Float.parseFloat(info.getLine(2)), Float.parseFloat(info.getLine(3)), ((CommonMinecart) member.getEntity()).getUniqueId());
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
                                        screenManager.set(screen.getPlayer(), Float.parseFloat(info.getLine(2)), Float.parseFloat(info.getLine(3)), screen.getId());
                                    }
                                }
                            }
                        }
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
