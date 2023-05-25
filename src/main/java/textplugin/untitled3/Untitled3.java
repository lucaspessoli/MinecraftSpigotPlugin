package textplugin.untitled3;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import textplugin.untitled3.events.TutorialEvents;

public final class Untitled3 extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new TutorialEvents(), this);
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
        if(command.getName().equals("spawnanimal")){
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
                player.sendMessage(ChatColor.LIGHT_PURPLE + entity + " was spawned" + quantity + " times!");
            }
        }
        return true;
    }
}
