package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManger {

    public void setScoreBoard(Player p) {
        if(!Shopy.getInstance().getSpielerShops().containsKey(p.getUniqueId())) return;

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("shopy", "shopy", "§9Shopy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score text = obj.getScore("§1");
        text.setScore(15);

        text = obj.getScore(" §7Rang    §a▶ §e" + "§cComing Soon");
        text.setScore(13);

        text = obj.getScore(" §7Geld    §a▶ §e" + Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger().getRessourceValue(Ressource.getRessoureByName("Geld")) + " §7€");
        text.setScore(14);

        text = obj.getScore(" §7Kunden §a▶ §e" + "§cComing Soon" + " §7");
        text.setScore(12);

        if(Shopy.getInstance().getSpielerDungeon().containsKey(p.getUniqueId())){
            text = obj.getScore("§2");
            text.setScore(11);

            text = obj.getScore(" §7Monster am Leben §a▶ §e" + Shopy.getInstance().getSpielerDungeon().get(p.getUniqueId()).getLebendeGegner());
            text.setScore(10);

            text = obj.getScore(" §7Erhaltender Loot §a▶ §e" + Shopy.getInstance().getSpielerDungeon().get(p.getUniqueId()).getErhaltendenLoot());
            text.setScore(9);

            text = obj.getScore("§3");
            text.setScore(8);
        }else {
            text = obj.getScore("§2");
            text.setScore(11);
        }

        p.setScoreboard(board);
    }
}
