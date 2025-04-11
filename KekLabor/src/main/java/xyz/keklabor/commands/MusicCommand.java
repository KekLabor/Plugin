package xyz.keklabor.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import xyz.keklabor.music.CatMusicPlayer;

import java.util.Collections;

public class MusicCommand implements CommandExecutor, Listener {

    private final CatMusicPlayer musicPlayer;

    public MusicCommand(CatMusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        boolean isPlaying = musicPlayer.isPlaying(player);
        Inventory gui = Bukkit.createInventory(null, 9, "🎵 Music Settings");

        ItemStack item = new ItemStack(isPlaying ? Material.LIME_DYE : Material.RED_DYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(isPlaying ? "§aMusic is Enabled" : "§cMusic is Disabled");
        meta.setLore(Collections.singletonList("§7Click to toggle"));
        item.setItemMeta(meta);

        gui.setItem(4, item);
        player.openInventory(gui);
        return true;
    }
}
