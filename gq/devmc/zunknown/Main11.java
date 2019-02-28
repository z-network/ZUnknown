package gq.devmc.zunknown;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.plugin.java.JavaPlugin;

public class Main11
  extends JavaPlugin
  implements Listener
{
  FileConfiguration config;
  File cfile;
  private String Message;
  
  public void onEnable()
  {
    this.config = getConfig();
    this.config.options().copyDefaults(true);
    saveConfig();
    this.cfile = new File(getDataFolder(), "config.yml");
    this.Message = ChatColor.translateAlternateColorCodes("&".charAt(0), this.config.getString("ZMessage"));
    Bukkit.getServer().getPluginManager().registerEvents(this, this);
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)
  {
    Player p = event.getPlayer();
    if ((!event.isCancelled()) && (p.hasPermission("znetwork.default")))
    {
      String command = event.getMessage().split(" ")[0];
      HelpTopic htopic = Bukkit.getServer().getHelpMap().getHelpTopic(command);
      if (htopic == null)
      {
        p.sendMessage(this.Message);
        event.setCancelled(true);
      }
    }
  }
}
