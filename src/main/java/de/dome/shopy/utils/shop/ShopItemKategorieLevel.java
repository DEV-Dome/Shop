package de.dome.shopy.utils.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.items.ItemKategorie;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class ShopItemKategorieLevel {

    int level;
    int xpZumNachstenLevel;
    int aktuelleXP;
    ItemKategorie itemKategorie;
    Shop shop;

    public ShopItemKategorieLevel(int level, int xpZumNachstenLevel, int aktuelleXP, ItemKategorie itemKategorie, Shop shop) {
        this.level = level;
        this.xpZumNachstenLevel = xpZumNachstenLevel;
        this.aktuelleXP = aktuelleXP;
        this.itemKategorie = itemKategorie;
        this.shop = shop;
    }

    /* git zurück, ob es ein LevelUp gabe */
    public boolean addXp(int xp){
        aktuelleXP += xp;

        //Check ob, ob die Kategorie ein Level Aufgestiegen ist.
        if(aktuelleXP >= xpZumNachstenLevel){
            int altesLevel = level;

            levelUp();

            if(altesLevel != level) return true;
            else return false;
        }

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET value='"+ aktuelleXP +"' WHERE wert='itemKategorie_"+ itemKategorie.getName() +"_xp' AND shop = '" + shop.getShopId() +"'");
        });
        return false;
    }

    private void levelUp(){
        if(aktuelleXP >= xpZumNachstenLevel){
            CompletableFuture.runAsync(() -> {
                //ckeck, ob es ein max level gibt
                try {
                    String query = "SELECT * FROM item_kategorie_level WHERE level = '" + (++level) + "' LIMIT 1";
                    ResultSet result = Shopy.getInstance().getMySQLConntion().resultSet(query);

                    if (result.next()){
                        aktuelleXP = aktuelleXP - xpZumNachstenLevel;
                        xpZumNachstenLevel = result.getInt("xp");

                        Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET value='"+ aktuelleXP +"' WHERE wert='itemKategorie_"+ itemKategorie.getName() +"_xp' AND shop = '" + shop.getShopId() +"'");
                        Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET value='"+ level +"' WHERE wert='itemKategorie_"+ itemKategorie.getName() +"_level' AND shop = '" + shop.getShopId() +"'");
                    }else {
                        level--;

                        aktuelleXP = xpZumNachstenLevel;
                        Shopy.getInstance().getMySQLConntion().query("UPDATE shop_werte SET value='"+ aktuelleXP +"' WHERE wert='itemKategorie_"+ itemKategorie.getName() +"_xp' AND shop = '" + shop.getShopId() +"'");
                    }
                } catch (SQLException e) { }
            });
        }
    }
    public int getLevel() {
        return level;
    }

    public int getXpZumNachstenLevel() {
        return xpZumNachstenLevel;
    }

    public int getAkeulleXP() {
        return aktuelleXP;
    }
}
