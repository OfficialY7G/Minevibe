package net.minevibe.listener;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_8_R1.EnumParticle;
import net.minecraft.server.v1_8_R1.Packet;
import net.minecraft.server.v1_8_R1.PacketPlayOutWorldParticles;
import net.minevibe.Core;

public class Particles implements Listener 
{
	  private final Core core;
	  private ArrayList<String> alreadySpawned = new ArrayList<>();
	  public Particles(Core plugin) 
	  {
	   this.core = plugin;
	  }
	
    public void spawnParticle(Player p, Location loc) 
    {
    	particleSummon(p, loc);
        p.sendMessage(ChatColor.BLUE + "Minevibe> " + ChatColor.GRAY + "This will last for five seconds, using a runnable. Lovely stuff, isn't it?");
    }
    
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) 
    {

    	Player player = (Player) event.getPlayer();
    	Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
    	if(block.getType() == Material.WOOL && block.getData() == (byte) 14) 
    	{
    		if (!alreadySpawned.contains("Already Done"))
    		{
    		spawnParticle(player, player.getLocation());
    		alreadySpawned.add("Already Done");
    		}
    	}
    }
    private int particletime = 100;
    
    public void particleSummon(Player p, Location loc)
    {
        	new BukkitRunnable() 
        	{
        	    @Override
        	    public void run() 
        	    {
        	    	if (particletime == 0)
        	    	{
        	    		p.sendMessage(ChatColor.BLUE + "Minevibe> " + ChatColor.GRAY + "Done!");
        	    		this.cancel();
        	    	}
        	    	
        	    	else if (particletime > 0 && !(particletime == 100))
        	    	{
        	    		particletime--;
                	    Packet particle = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), (float) 255, (float) 0, (float) 0, (float) 0, 0, 0);
                    	((CraftPlayer)p).getHandle().playerConnection.sendPacket(particle);
        	    	}
        	    	else if (particletime == 100)
        	    	{
        	    	particletime = 5;
        	    	}
        	    }
        	}.runTaskTimer(core.instance, 0, 20);
    }
}
