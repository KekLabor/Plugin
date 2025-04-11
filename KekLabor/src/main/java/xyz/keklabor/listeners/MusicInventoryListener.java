package xyz.keklabor.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import xyz.keklabor.music.CatMusicPlayer;

public class MusicInventoryListener implements Listener {

    private final CatMusicPlayer musicPlayer;

    public MusicInventoryListener(CatMusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryView view = event.getView();
        Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory == null) return;

        if (!view.getTitle().equals("🎵 Music Settings")) return;

        event.setCancelled(true); // Kein Item rausnehmen etc.

        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();

        if (event.getSlot() == 4) {
            musicPlayer.toggle(player);
            player.closeInventory();
            //Bukkit.dispatchCommand(player, "music"); // GUI neu öffnen
        }
    }
}
