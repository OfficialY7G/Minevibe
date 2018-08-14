package net.minevibe.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R1.PacketPlayInEntityAction;

public class CroucherCMD implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{

		if (!(sender instanceof Player))
		{
			return false;
		}

		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("crouch"))
		{
			for(Player player : Bukkit.getServer().getOnlinePlayers()) 
			{
				if(!player.equals(p))
				{
			        setServerwideCrouch(player);
				}
			}
		}
		return true;
	}
	
	public void setServerwideCrouch(Player player) 
	{
        PacketPlayInEntityAction packet = new PacketPlayInEntityAction();
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}