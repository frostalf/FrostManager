
package net.frostalf.frostmanager.util;

import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Frostalf
 */
public enum Permissions {

    ENABLE("frostmanager.enable", "", "frostmanager.*"),
    DISABLE("frostmanager.disable", "", "frostmanager.*"),
    VERSION("frostmanager.version", "", "frostmanager.*"),
    INFO("frostmanager.info", "", "frostmanager.*"),
    LOAD("frostmanager.load", "", "frostmanager.*"),
    RELOAD("frostmanager.reload", "", "frostmanager.*"),
    MANAGE("frostmanager.manage", "", "frostmanager.*");

    String perm;
    String requiredPerm;
    ArrayList<String> hierarchy = new ArrayList<>();

    Permissions(String perm, String requiredPerm, String... hierarchy) {
        this.perm = perm;
        this.requiredPerm = requiredPerm;
        this.hierarchy.addAll(Arrays.asList(hierarchy));
    }

    public boolean hasPerm(CommandSender sender){
        if(sender instanceof Player){
        return hasPerm((Player) sender);
        } else {
            return true;
        }
    }

    public boolean hasPerm(Player player){
        boolean hasRequiredPerm = this.requiredPerm.equalsIgnoreCase("") ? true : player.hasPermission(this.requiredPerm);
        if(!(player.hasPermission(this.perm) && hasRequiredPerm)) {
            for(String s : this.hierarchy){
                if(player.hasPermission(s)){
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }
}
