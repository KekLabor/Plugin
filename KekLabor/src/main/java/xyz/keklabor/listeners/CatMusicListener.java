package xyz.keklabor.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.keklabor.music.CatMusicPlayer;

public class CatMusicListener implements Listener {

    private final CatMusicPlayer catMusicPlayer;
    private final JavaPlugin plugin;

    public CatMusicListener(CatMusicPlayer catMusicPlayer, JavaPlugin plugin) {
        this.catMusicPlayer = catMusicPlayer;
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!catMusicPlayer.isPlaying(player)) {
            catMusicPlayer.start(player);
            plugin.getLogger().info("[KekLabor Music-Loop] Started Music Loop for " + player.getName());
        } else {
            plugin.getLogger().info("[KekLabor Music-Loop] Music is already enabled for " + player.getName());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        catMusicPlayer.stop(event.getPlayer());
        plugin.getLogger().info("[KekLabor Music-Loop] Music Loop Stopped for " + event.getPlayer().getName() + " (Player left)");
    }
}
