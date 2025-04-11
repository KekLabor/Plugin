package xyz.keklabor;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.keklabor.commands.ManageCommand;
import xyz.keklabor.commands.MusicCommand;
import xyz.keklabor.commands.SpawnCommand;
import xyz.keklabor.listeners.CatMusicListener;
import xyz.keklabor.listeners.ManageInventoryListener;
import xyz.keklabor.listeners.MusicInventoryListener;
import xyz.keklabor.music.CatMusicPlayer;

import java.util.Objects;

public final class KekLabor extends JavaPlugin {

    private CatMusicPlayer musicPlayer;

    @Override
    public void onEnable() {
        musicPlayer = new CatMusicPlayer(this);
        getLogger().info("[Music-Loop] Started successfully");

        registerCommands();
        registerListeners();

        getLogger().info("Started successfully");
    }

    @Override
    public void onDisable() {
        getLogger().info("Shut down successfully");
        getLogger().info("[Music-Loop] Shut down successfully");

    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand());
        Objects.requireNonNull(getCommand("music")).setExecutor(new MusicCommand(musicPlayer));
        Objects.requireNonNull(getCommand("manage")).setExecutor(new ManageCommand());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new CatMusicListener(musicPlayer, this), this);
        getServer().getPluginManager().registerEvents(new MusicInventoryListener(musicPlayer), this);
        getServer().getPluginManager().registerEvents(new ManageInventoryListener(), this);
    }
}
