package me.zacherycoleman.worlddecay.Runnables;

import org.bukkit.scheduler.BukkitRunnable;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class DecayRunnable extends BukkitRunnable 
{

    // ArrayList for the blocks
    private static final List<Block> BlockList = new ArrayList<Block>();

    // Return a random block
    private int RandomBlock() { return new SecureRandom().nextInt(this.BlockList.size()); }

    // The runnable
    public void run()
    {
        // Getting the players
        for (Player P : Bukkit.getOnlinePlayers())
        {
            // Location of the player   
            Location Loc = P.getLocation();
            // Get the nearby blocks of the player 
            List<Block> blocks = GetNearbyBlocks(Loc, 50); 
            // Add them to the list
            BlockList.addAll(blocks); 

            /*getHighestBlockAt(Location) doesn't actually return the highest block at that location, because
            /*shitfuck API returns the block RIGHT FUCKING ABOVE IT (even though it says in Javadocs, that it
            /*returns a "non-empty" block)! Unless, of course, it's tall grass or leaves. so instead we have
            /*to use getHighestBlockYAt(Location) but because it returns an int, we have to convert it to a 
            /*location using setY() and then take away 1, because it still fucking returns air. */
            // Location of the random block the randomizer chose
            Location Loc2 = this.BlockList.get(this.RandomBlock()).getLocation();
            // get the "highest" block
            int Loc3 = Loc2.getWorld().getHighestBlockYAt(Loc2);
            // Oops, it doesn't return the highest, minus 1.
            Loc2.setY(Loc3 - 1);

            // Make sure, if the block is somehow air, to not try to replace it with more air
            if (Loc2.getBlock().getType() != Material.AIR)
            {
                // Set the block to air
                Loc2.getBlock().setType(Material.AIR);
                // Clear the list, we don't want to keep replacing those blocks, we arn't there anymore
                BlockList.clear();
            }       
        }
    }
    
    // Some BMF shit
    public List<Block> GetNearbyBlocks(Location location, int radius)
    {
        // The list for the blocks
        List<Block> blocks = new ArrayList<Block>();

        // idk
        for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) 
        {
            for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) 
            {
                for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) 
                {
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }
}