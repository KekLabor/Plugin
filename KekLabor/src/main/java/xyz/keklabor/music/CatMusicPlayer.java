package xyz.keklabor.music;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class CatMusicPlayer {

    private final JavaPlugin plugin;
    private final Set<UUID> playingPlayers = new HashSet<>();
    private final Map<UUID, BukkitRunnable> tasks = new HashMap<>();

    public CatMusicPlayer(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void start(Player player) {
        UUID uuid = player.getUniqueId();
        if (playingPlayers.contains(uuid)) return;

        playingPlayers.add(uuid);
        BukkitRunnable task = createLoop(uuid);
        tasks.put(uuid, task);
        task.runTaskTimer(plugin, 0L, 20L * 60); // alle 60 Sekunden
    }

    public void stop(Player player) {
        UUID uuid = player.getUniqueId();
        playingPlayers.remove(uuid);

        BukkitRunnable task = tasks.remove(uuid);
        if (task != null) {
            task.cancel();
        }
        player.stopSound(Sound.MUSIC_DISC_CAT, SoundCategory.RECORDS);
    }

    public boolean isPlaying(Player player) {
        return playingPlayers.contains(player.getUniqueId());
    }

    public void toggle(Player player) {
        if (isPlaying(player)) {
            stop(player);
            player.sendMessage("§cMusic disabled");
        } else {
            start(player);
            player.sendMessage("§aMusic enabled");
        }
    }

    private BukkitRunnable createLoop(UUID uuid) {
        return new BukkitRunnable() {
            @Override
            public void run() {
                if (!playingPlayers.contains(uuid)) {
                    cancel();
                    return;
                }

                Player player = Bukkit.getPlayer(uuid);
                if (player == null || !player.isOnline()) {
                    cancel();
                    return;
                }

                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.playSound(p.getLocation(), Sound.MUSIC_DISC_CAT, SoundCategory.RECORDS, 100.0f, 1.0f);
                }
            }
        };
    }
}
