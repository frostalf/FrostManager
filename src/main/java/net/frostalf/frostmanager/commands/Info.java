
package net.frostalf.frostmanager.commands;

import net.frostalf.frostmanager.FrostManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Frostalf
 */
public class Info implements CommandExecutor {

    FrostManager plugin;

    public Info(FrostManager plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        String pluginName = args[1];
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + pluginName + " info: &4" + plugin.getPlugin(pluginName).getDescription()));
        return true;
    }
}
