
package net.frostalf.frostmanager.commands;

import net.frostalf.frostmanager.FrostManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Frostalf
 */
public class Version implements CommandExecutor {

    FrostManager plugin;

    public Version(FrostManager plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        return true;
    }
}
