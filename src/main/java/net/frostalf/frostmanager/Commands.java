
package net.frostalf.frostmanager;

import java.util.logging.Level;
import net.frostalf.frostmanager.util.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
    
    private PluginManager pm;
    
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        pm = plugin.getServer().getPluginManager();
        if(cmd.getName().equalsIgnoreCase("fmdisable")){
            if(Permissions.DISABLE.hasPerm(sender)){
                if(args.length == 1){
                    pm.disablePlugin(plugin.getPlugin(args[0]).getPlugin());
                    plugin.recacheData();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2Disabling Plugin!"));
                    if(plugin.getPlugin(args[0]).isEnabled() == false){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bPlugin Disabled!"));
                        plugin.getLogger().log(Level.INFO, "Plugin: {0} Disabled!", pm.getPlugin(args[0]).getName());
                        return true;
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Plugin Not Disabled!"));
                        plugin.getLogger().log(Level.WARNING, "Plugin: {0} Could not be Disabled!", pm.getPlugin(args[0]).getName());
                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Too Many Arguments!"));
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You Don't have Permissions!"));
                return true;
            }
        }
        
        if(cmd.getName().equalsIgnoreCase("fmenable")){
            if(Permissions.ENABLE.hasPerm(sender)){
              if(args.length == 1){
                    pm.enablePlugin(plugin.getPlugin(args[0]).getPlugin());
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2Enabling Plugin!"));
                    plugin.recacheData();
                    if(plugin.getPlugin(args[0]).isEnabled() == true){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bPlugin Enabled!"));
                        plugin.getLogger().log(Level.INFO, "Plugin: {0} Enabled!", pm.getPlugin(args[0]).getName());
                        return true;
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Plugin Not Enabled!"));
                        plugin.getLogger().log(Level.WARNING, "Plugin: {0} Could not be Enabled!", pm.getPlugin(args[0]).getName());
                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Too Many Arguments!"));
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You Don't have Permissions!"));
                return true;
            }
        }
        
        if(cmd.getName().equalsIgnoreCase("fmversion")){
            if(Permissions.VERSION.hasPerm(sender)){
                if(args.length == 0){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bFrostManager version: &4" + plugin.getDescription().getVersion()));
                    return true;
                }
                if(args.length == 1){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + args[0] + " version: &4" + plugin.getPlugin(args[0]).getVersion()));
                    return true;
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Too Many Arguments!"));
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have Permissions!"));
                return true;
            }
        }
        
        if(cmd.getName().equalsIgnoreCase("fminfo")){
            if(Permissions.INFO.hasPerm(sender)){
                if(args.length == 0){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bFrostManager version: &4" + plugin.getDescription().getDescription()));
                    return true;
                }
                if(args.length == 1){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + args[0] + " version: &4" + plugin.getPlugin(args[0]).getName()));
                    return true;
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Too Many Arguments!"));
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have Permissions!"));
                return true;
            }
        }
        
        return false;
    }
}
