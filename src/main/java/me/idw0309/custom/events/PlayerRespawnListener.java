package me.idw0309.custom.events;

import lombok.RequiredArgsConstructor;
import me.idw0309.custom.Custom;
import me.idw0309.custom.data.PlayerData;
import me.idw0309.custom.data.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class PlayerRespawnListener implements Listener  {

    final Custom plugin = Custom.getInstance();

    private final PlayerManager playerManager;

    //TODO kijk in de config watvoor stats de speler had en geef deze aan de speler.

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {

        //delay, becaus if the player isn't respawned yet, he wont get effects.
        new BukkitRunnable() {
            public void run() {
                Player player = e.getPlayer();

                //Getting playerdata
                PlayerData playerData = playerManager.getPlayerData(player);

                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, playerData.getStrength()));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, playerData.getResistance()));

            }
        }.runTaskLater(plugin, 1);
    }


}
