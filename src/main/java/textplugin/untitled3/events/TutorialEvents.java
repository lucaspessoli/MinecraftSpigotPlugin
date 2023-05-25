package textplugin.untitled3.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class TutorialEvents implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event){
        String a = event.getPlayer().getName();
        Player player = event.getPlayer();
        player.sendMessage("Welcome! " + player.getName());
    }

    @EventHandler
    public static void onPlayerWalk(PlayerMoveEvent event){
        Player player = event.getPlayer();
        World w = player.getWorld();
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();

        Material block = player.getWorld().getBlockAt(x,y-1,z).getType();
        if(block.equals(Material.STONE)){
            w.createExplosion(player.getLocation(),5);
        }
    }

    @EventHandler
    public static void onPlayerChat(PlayerChatEvent event){
        Player player = event.getPlayer();
        World w = player.getWorld();
        if(event.getMessage().equals("tais carla")){
            w.createExplosion(player.getLocation(),50);
        }
        if(event.getMessage().equals("bosta")){
            player.kickPlayer(player.getName());
        }
        if(event.getMessage().equals("porco")){
            w.spawnEntity(player.getLocation(),EntityType.PIG);
        }
    }
    @EventHandler
    public static void onPlayerThrowEgg(PlayerEggThrowEvent event){
        Player player = event.getPlayer();
        event.setHatching(true);
        event.setHatchingType(EntityType.CREEPER);
        event.setNumHatches((byte) 5);
        Location e = event.getEgg().getLocation();
        player.teleport(e);
    }


}
