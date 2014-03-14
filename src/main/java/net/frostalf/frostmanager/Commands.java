
package net.frostalf.frostmanager;

import java.util.logging.Level;
import net.frostalf.frostmanager.util.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Frostalf
 */
public class Commands implements CommandExecutor {
    
    private final FrostManager plugin;
    public Commands(FrostManager plugin){
        this.plugin = plugin;
    }    
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(cmd.getName().equalsIgnoreCase("fm")){ 
            if(args.length == 2){
                String action = args[0];
                String pluginName = args[1];
                if(action.equalsIgnoreCase("disable")){
                    if(Permissions.DISABLE.hasPerm(sender)){
                        plugin.disablePlugin(pluginName);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2Disabling Plugin!"));
                        if(plugin.getPlugin(pluginName).isEnabled() == false){
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bPlugin Disabled: " + pluginName));
                            plugin.getLogger().log(Level.INFO, "Plugin: {0} Disabled!", plugin.getPlugin(pluginName).getName());
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Plugin Not Disabled!"));
                            plugin.getLogger().log(Level.WARNING, "Plugin: {0} Could not be Disabled!", plugin.getPlugin(pluginName).getName());
                        }
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You Don't have Permissions!"));
                    }
                    return true;
                }
                
                if(action.equalsIgnoreCase("enable")){
                    if(Permissions.ENABLE.hasPerm(sender)){
                        plugin.enablePlugin(pluginName);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2Enabling Plugin!"));
                        if(plugin.getPlugin(pluginName).isEnabled() == true){
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bPlugin Enabled: " + pluginName));
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Plugin Not Enabled!"));
                            plugin.getLogger().log(Level.WARNING, "Plugin: {0} Could not be Enabled!", plugin.getPlugin(pluginName).getName());
                        }
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You Don't have Permissions!"));
                    }
                    return true;
                }
                
                if(action.equalsIgnoreCase("load")){
                    if(Permissions.LOAD.hasPerm(sender)){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bLoading Plugin: &4" + plugin.getPluginFile(pluginName).getName()));
                        plugin.loadPlugin(plugin.getPluginFile(pluginName).getName(), sender);
                        if(plugin.getPlugin(pluginName).isEnabled()){
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bPlugin Loaded successfully: " + pluginName));
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bPlugin Not Loaded: " + pluginName));
                        }
                    }
                    return true;
                }
                
                if(action.equalsIgnoreCase("version")){
                    if(Permissions.VERSION.hasPerm(sender)){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + pluginName + " version: &4" + plugin.getPlugin(pluginName).getVersion()));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have Permissions!"));
                    }
                    return true;
                }
                
                if(action.equalsIgnoreCase("info")){
                    if(Permissions.INFO.hasPerm(sender)){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + pluginName + " info: &4" + plugin.getPlugin(pluginName).getDescription()));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have Permissions!"));
                    }
                    return true;
                }
            }
            
            if(args.length == 1){
                String action = args[0];
                if(action.equalsIgnoreCase("version")){
                    if(Permissions.VERSION.hasPerm(sender)){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bFrostManager version: &4" + plugin.getDescription().getVersion()));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have Permissions!"));
                    }
                    return true;
                }
                
                if(action.equalsIgnoreCase("info")){
                    if(Permissions.INFO.hasPerm(sender)){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bFrostManager info: &4" + plugin.getDescription().getDescription()));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have Permissions!"));
                    }
                    return true;
                }
                
                if(action.equalsIgnoreCase("reload")){
                    if(Permissions.RELOAD.hasPerm(sender)){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2Reloading Plugin"));
                        plugin.recacheData();
                        plugin.recacheFileList();
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have Permissions!"));
                    }
                    return true;
                }
                return true;
            }
            
            if(args.length == 0){
                if(Permissions.MANAGE.hasPerm(sender)){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Missing Arguments!"));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have Permissions!"));
                }
                return true;
            }
            
            if(args.length < 2){
                if(Permissions.MANAGE.hasPerm(sender)){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Too Many Arguments!"));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have Permissions!"));
                }
                return true;
            }
            return true;
        }
        return false;
    }
}
