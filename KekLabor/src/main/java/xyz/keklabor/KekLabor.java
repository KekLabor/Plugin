package xyz.keklabor;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.keklabor.commands.MusicCommand;
import xyz.keklabor.commands.SpawnCommand;
import xyz.keklabor.listeners.CatMusicListener;
import xyz.keklabor.listeners.MusicInventoryListener;
import xyz.keklabor.music.CatMusicPlayer;

import java.util.Objects;

public final class KekLabor extends JavaPlugin {

    private CatMusicPlayer musicPlayer;

    @Override
    public void onEnable() {
        musicPlayer = new CatMusicPlayer(this);
        getLogger().info("[KekLabor Music-Loop] Started successfully");

        registerCommands();
        registerListeners();

        getLogger().info("[KekLabor] Started successfully");
    }

    @Override
    public void onDisable() {
        getLogger().info("[KekLabor] Shut down successfully");
        getLogger().info("[KekLabor Music-Loop] Shut down successfully");

    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand());
        Objects.requireNonNull(getCommand("music")).setExecutor(new MusicCommand(musicPlayer));
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new CatMusicListener(musicPlayer, this), this);
        getServer().getPluginManager().registerEvents(new MusicInventoryListener(musicPlayer), this);
    }
}
