package net.minevibe;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.minevibe.listener.Particles;

public class Core extends JavaPlugin 
{

    public Core instance;

	public void onEnable() 
    {
		instance = this;
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Particles(this), this);
    }
}