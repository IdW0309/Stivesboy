package me.idw0309.custom.events;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.idw0309.custom.Custom;
import me.idw0309.custom.data.PlayerData;
import me.idw0309.custom.data.PlayerManager;
import me.idw0309.custom.message.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class PlayerJoinQuitListener implements Listener {

    //TODO kijk in de config watvoor stats de speler had en geef deze aan de speler.

    final Custom plugin = Custom.getInstance();

    private final PlayerManager playerManager;


    @SneakyThrows
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        // When the player joins, load their data
        playerManager.loadPlayerData(e.getPlayer());

        playerJoined(e.getPlayer());
    }

    private void playerJoined(Player player) throws InterruptedException {

        Thread.sleep(1000);

        //Loading all player data
        PlayerData playerData = playerManager.getPlayerData(player);

        //Gives the player the effects he need.
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, playerData.getStrength()));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, playerData.getResistance()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        //Removes the effects that the player has from the plugin.
        e.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        e.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);

        // Save the player data
        playerManager.savePlayerData(e.getPlayer());

        // Unload the player
        playerManager.unloadPlayer(e.getPlayer());

    }

}
