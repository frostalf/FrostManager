
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
public class Enable implements CommandExecutor {

    FrostManager plugin;

    public Enable(FrostManager plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        String pluginName = args[1];
        plugin.enablePlugin(pluginName);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2Enabling Plugin!"));
        if(plugin.getPlugin(pluginName).isEnabled() == true){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bPlugin Enabled: " + pluginName));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Plugin Not Enabled!"));
            plugin.getLogger().log(Level.WARNING, "Plugin: {0} Could not be Enabled!", plugin.getPlugin(pluginName).getName());
        }
        return true;
    }
}
