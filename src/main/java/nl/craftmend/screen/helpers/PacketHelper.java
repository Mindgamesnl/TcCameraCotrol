package nl.craftmend.screen.helpers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

/**
 * Created by Mindgamesnl-laptop on 10-5-2017.
 */

public class PacketHelper
{
    public static void setLook(float yaw, float pitch, ArmorStand as, Player player)
    {
        PacketContainer pc = new PacketContainer(PacketType.Play.Server.ENTITY_LOOK);
        pc.getModifier().writeDefaults();
        pc.getIntegers().write(0, Integer.valueOf(as.getEntityId()));
        pc.getBytes()
                .write(0, Byte.valueOf((byte)getCompressedAngle(yaw)))
                .write(1, Byte.valueOf((byte)getCompressedAngle(pitch)));
        pc.getBooleans().write(0, Boolean.valueOf(true));
        try
        {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, pc);
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }

    public static int getCompressedAngle(float value)
    {
        return (int)(value * 256.0F / 360.0F);
    }
}
