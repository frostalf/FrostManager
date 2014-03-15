
package net.frostalf.frostmanager.commands;

import java.util.logging.Level;
import net.frostalf.frostmanager.FrostManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Frostalf
 */
public class Disable implements CommandExecutor {

    FrostManager plugin;

    public Disable(FrostManager plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        String pluginName = args[1];
        plugin.disablePlugin(pluginName);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2Disabling Plugin!"));
        if(plugin.getPlugin(pluginName).isEnabled() == false){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bPlugin Disabled: " + pluginName));
            plugin.getLogger().log(Level.INFO, "Plugin: {0} Disabled!", plugin.getPlugin(pluginName).getName());
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Plugin Not Disabled!"));
            plugin.getLogger().log(Level.WARNING, "Plugin: {0} Could not be Disabled!", plugin.getPlugin(pluginName).getName());
        }
        return true;
    }
}
