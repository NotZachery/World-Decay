package me.zacherycoleman.worlddecay;

import org.bukkit.plugin.java.JavaPlugin;

import me.zacherycoleman.worlddecay.Runnables.DecayRunnable;

public final class WorldDecay extends JavaPlugin
{

    @Override
    public void onEnable()
    {
        // Plugin startup logic
        new DecayRunnable().runTaskTimer(this, 0L, 20L);
        getLogger().info("World Decay enabled successfully! :D");
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
        getLogger().info("World Decay disabled successfully! :D");
    }
}
