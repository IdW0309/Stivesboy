package me.idw0309.custom.events;

import lombok.RequiredArgsConstructor;
import me.idw0309.custom.Custom;
import me.idw0309.custom.data.PlayerData;
import me.idw0309.custom.data.PlayerManager;
import me.idw0309.custom.message.Message;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

@RequiredArgsConstructor
public class PlayerDeathListener implements Listener {


    private final PlayerManager playerManager;

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();

        if (player == null) return;

        if (killer == null) return;

        executeKill(player, killer);
    }

    private void executeKill(Player target, Player killer) {

        //Creates a 50/50 change to get strenght or resistance
        int random = getRandomInt(2); //will return a number between 0-1

        //Loading playerdata of killer and killed player
        PlayerData targetData = playerManager.getPlayerData(target);
        PlayerData killerData = playerManager.getPlayerData(killer);

        int resistance = targetData.getResistance();
        int strength = targetData.getStrength();

        if (random == 0) {
            //Checks if player has 0 as score, this prevents the player from getting into the -1
            if (resistance > 0) {
                targetData.removeResistance();
            }
            killerData.addResistance();
        } else if (random == 1) {
            //Checks if player has 0 as score, this prevents the player from getting into the -1
            if (strength > 0) {
                targetData.removeStrength();
            }
            killerData.addStrength();
        }

        //Sends message to player who got killed and to the player who killed him with his score
        killer.sendMessage(ChatColor.YELLOW + "Score | Strenght: " + (killerData.getStrength()+1) + ", Resistance: " + (killerData.getResistance()+1));
        target.sendMessage(ChatColor.YELLOW + "Score | Strenght: " + (targetData.getStrength()+1) + ", Resistance: " + (targetData.getResistance()+1));

        //Adds the new gaind effects to the killer
        killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, killerData.getStrength()));
        killer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, killerData.getResistance()));

    }

    public static Integer getRandomInt(Integer max) {
        Random ran = new Random();
        return ran.nextInt(max);
    }

}
