
package net.frostalf.frostmanager.listeners;

import net.frostalf.frostmanager.FrostManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.PluginManager;

/**
 *
 * @author Frostalf
 */
public class FrostManagerListener implements Listener {

    private final FrostManager plugin;
    
    public FrostManagerListener(FrostManager plugin){
        this.plugin = plugin;
    }
    
    public void registerEvents(){
        
        PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(this, plugin);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPluginEnable(PluginEnableEvent event){
        String name = event.getPlugin().getName();
        //plugin.addPlugin(name);
    }    
}
