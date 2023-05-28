package textplugin.untitled3;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import textplugin.untitled3.events.TutorialEvents;

import java.security.Permission;
import java.security.Permissions;
import java.util.ArrayList;

public final class Untitled3 extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new TutorialEvents(), this);
        getLogger().info("Starting");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // die /
        if (command.getName().equals("sethealth")) {
            int vida = Integer.parseInt(args[0]);
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.setHealth(vida);
                player.sendMessage(ChatColor.GREEN + "life setted to: " + vida);
            }
        }
        if(command.getName().equals("spawnentity")){
            String entity = args[0];
            EntityType animalInformed = null;
            animalInformed = EntityType.valueOf(entity.toUpperCase());
            int quantity = Integer.parseInt(args[1]);
            if(sender instanceof Player){
                Player player = (Player) sender;
                World w = player.getWorld();
                for(int i=0;i<quantity;i++){
                    w.spawnEntity(player.getLocation(), animalInformed);
                }
                player.sendMessage(ChatColor.LIGHT_PURPLE + entity + " was spawned: " + quantity + " times!");
            }
        }
        if(command.getName().equals("flyspeed")){
            float speed = Float.parseFloat(args[0]);
            Player player = (Player) sender;
            player.setFlySpeed(speed);
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Flying speed updated to: " + speed);
        }
        if(command.getName().equals("getentities")){
            Player player = (Player) sender;
            World w = player.getWorld();
            w.setMonsterSpawnLimit(0);
            for(Entity entity: w.getEntities()){
                getLogger().info("entity:" + entity);
                player.sendMessage(ChatColor.DARK_GREEN + "" +entity);
            }
        }
        if(command.getName().equals("cleanentities")){
            Player player = (Player) sender;
            World w = player.getWorld();
            for(Entity entity : w.getEntities()){
                if(!(entity instanceof Player)){
                    entity.remove();
                    player.sendMessage(ChatColor.DARK_GREEN + "" + entity + " was cleaned");
                }
            }
        }
        if(command.getName().equals("gravity")){
            Player player = (Player) sender;
            if(player.hasGravity()){
                player.setGravity(false);
                player.sendMessage(ChatColor.GREEN + "Gravity turned off");
            }else{
                player.setGravity(true);
                player.sendMessage(ChatColor.GREEN + "Gravity turned on");
            }
        }
        if(command.getName().equals("fly")){
            Player player = (Player) sender;
            World w = player.getWorld();
            if(player.getGameMode().equals(GameMode.SURVIVAL)){
                if(player.getAllowFlight() == false){
                    player.setAllowFlight(true);
                    player.sendMessage(ChatColor.GREEN + "Fly enabled");
                }else{
                    player.setAllowFlight(false);
                    player.sendMessage(ChatColor.GREEN + "Fly disabled");
                }
            }else{
                player.sendMessage(ChatColor.RED + "you must be at survival mode to use this command");
            }
        }
        if(command.getName().equals("setarrowsbody")){
            int arrows = Integer.parseInt(args[0]);
            Player player = (Player) sender;
            player.setArrowsInBody(arrows);
            if(arrows == 0){
                player.sendMessage(ChatColor.GREEN + "Arrows removed from your body");
            }else{
                player.sendMessage(ChatColor.GREEN + "" + arrows + " Arrows in your body");
            }
        }
        if(command.getName().equals("setarrowscooldown")){
            int arrows = Integer.parseInt(args[0]);
            Player player = (Player) sender;
            player.setArrowCooldown(arrows);
            player.sendMessage(ChatColor.GREEN + "Arrows would be removed from your body in: " + arrows + " seconds...");
        }
        if(command.getName().equals("setwalkspeed")){
            float speed = Float.parseFloat(args[0]);
            Player player = (Player) sender;
            player.setWalkSpeed(speed);
            player.sendMessage(ChatColor.GREEN + "Walkspeed set to: " + speed);
        }
        if(command.getName().equals("playerlistnamechanger")){
            Player player = (Player) sender;
            try {
                player.setPlayerListName(args[0]);
                player.sendMessage(ChatColor.GREEN + "Sucess!");
            }catch(Exception e){
                player.sendMessage(ChatColor.RED + "Error! just one parameter is required");
            }
        }
//        if(command.getName().equals("playerspickupitems")){
//            boolean canGrabItem;
//            Player player = (Player) sender;
//            player.setCanPickupItems(false);
//            World w = player.getWorld();
//            for (Entity playerr : w.getEntities()){
//                if(playerr instanceof Player){
//                    if(((Player) playerr).getCanPickupItems()){
//                        ((Player) playerr).setCanPickupItems(false);
//                        player.sendMessage("Players cannot pickup items now");
//                    }else{
//                        ((Player) playerr).setCanPickupItems(true);
//                        player.sendMessage("players can pickup items now");
//                    }
//                }
//            }
//        }
        if(command.getName().equals("debugtest")){
            Player player = (Player) sender;
            World w = player.getWorld();
            player.setGravity(false);
        }
        return true;
    }
}
