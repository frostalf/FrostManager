
package net.frostalf.frostmanager.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

/**
 *
 * @author Frostalf
 */
public class FrostManagerListener implements Listener {

    
    
    @EventHandler
    public void onPluginEnable(PluginEnableEvent event){
        String name = event.getPlugin().getName().toLowerCase();
    }
    
    @EventHandler
    public void onPluginDisable(PluginDisableEvent event){
        String name = event.getPlugin().getName().toLowerCase();
    }
    
    
}
