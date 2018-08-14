package net.minevibe;

import org.bukkit.plugin.java.JavaPlugin;

import net.minevibe.commands.CroucherCMD;

public class Core extends JavaPlugin 
{
    public Core instance;

	public void onEnable() 
    {
		instance = this;
        this.getCommand("crouch").setExecutor(new CroucherCMD());
    }
}