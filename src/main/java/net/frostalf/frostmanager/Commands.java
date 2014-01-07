
package net.frostalf.frostmanager;

import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 *
 * @author Frostalf
 */
public class Commands implements CommandExecutor {
    
    private FrostManager plugin;
    public Commands(FrostManager plugin){
        this.plugin = plugin;
    }
    
    private PluginManager pm = plugin.getServer().getPluginManager();
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        
        if(cmd.getName().equalsIgnoreCase("disable")){
            if(sender.hasPermission("frostmanager.*") || sender.hasPermission("frostmanager.disable")){
                if(args.length > 0 && args.length < 2){
                    pm.disablePlugin(pm.getPlugin(args[0]));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2Disabling Plugin!"));
                    if(pm.getPlugin(args[0]).isEnabled() == false){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bPlugin Disabled!"));
                        plugin.getLogger().log(Level.INFO, "Plugin: {0} Disabled!", pm.getPlugin(args[0]).getName());
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Plugin Not Disabled!"));
                        plugin.getLogger().log(Level.WARNING, "Plugin: {0} Could not be Disabled!", pm.getPlugin(args[0]).getName());
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Too Many Arguments!"));
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You Don't have Permissions!"));
                return true;
            }
            return true;
        }
        
        if(cmd.getName().equalsIgnoreCase("enable")){
            if(sender.hasPermission("frostmanager.*") || sender.hasPermission("frostmanager.enable")){
              if(args.length > 0 && args.length < 2){
                    pm.enablePlugin(pm.getPlugin(args[0]));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2Enabling Plugin!"));
                    if(pm.getPlugin(args[0]).isEnabled() == true){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bPlugin Enabled!"));
                        plugin.getLogger().log(Level.INFO, "Plugin: {0} Enabled!", pm.getPlugin(args[0]).getName());
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Plugin Not Enabled!"));
                        plugin.getLogger().log(Level.WARNING, "Plugin: {0} Could not be Enabled!", pm.getPlugin(args[0]).getName());
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Too Many Arguments!"));
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You Don't have Permissions!"));
            }
            return true;
        }
        
        if(cmd.getName().equalsIgnoreCase("version")){
            if(sender.hasPermission("frostmanager.*") || sender.hasPermission("frostmanager.version")){
                if(args.length == 0){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bFrostManager version: &4" + plugin.getDescription().getVersion()));
                }
                if(args.length > 0 && args.length < 2){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + args[0] +" version: &4" + pm.getPlugin(args[0]).getDescription().getVersion()));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Too Many Arguments!"));
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have Permissions!"));
            }
            return true;
        }
        
        return false;
    }

}
