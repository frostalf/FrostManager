
package net.frostalf.frostmanager.commands;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.logging.Level;
import net.frostalf.frostmanager.FrostManager;
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
    private Map<String, CommandExecutor> subCommandMap = Maps.newHashMap();
    public Commands(FrostManager plugin){
        this.plugin = plugin;
        subCommandMap.put("enable", new Enable(plugin));
        subCommandMap.put("disable", new Disable(plugin));
        subCommandMap.put("info", new Info(plugin));
        subCommandMap.put("version", new Version(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(cmd.getName().equalsIgnoreCase("fm")){ 
            if(args.length == 2){
                String action = args[0];
                String pluginName = args[1];
                if(action.equalsIgnoreCase("disable")){
                    if(Permissions.DISABLE.hasPerm(sender)){
                        return subCommandMap.get("disable").onCommand(sender, cmd, action, args);
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You Don't have Permissions!"));
                    }
                    return false;
                }

                if(action.equalsIgnoreCase("enable")){
                    if(Permissions.ENABLE.hasPerm(sender)){
                        return subCommandMap.get("enable").onCommand(sender, cmd, action, args);
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You Don't have Permissions!"));
                    }
                    return false;
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
