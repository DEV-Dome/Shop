package de.dome.shopy.utils.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.items.Item;
import de.dome.shopy.utils.items.ItemKategorie;
import de.dome.shopy.utils.items.ItemSeltenheit;
import org.bukkit.Material;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class ShopItem {

    int id;
    ItemKategorie itemKategorie;
    String name;
    String beschreibung;
    Material icon;
    ItemSeltenheit itemSeltenheit;
    double schaden = 0;
    double angriffsgeschwindigkeit = 0;

    int haltbarkeit = 0;

    public ShopItem(int id, ItemKategorie itemKategorie, String name, String beschreibung, Material icon, ItemSeltenheit itemSeltenheit, double schaden, double angriffsgeschwindigkeit, int haltbarkeit) {
        this.id = id;
        this.itemKategorie = itemKategorie;
        this.name = name;
        this.beschreibung = beschreibung;
        this.icon = icon;
        this.itemSeltenheit = itemSeltenheit;
        this.schaden = roundToTwoDecimalPlaces(schaden);
        this.angriffsgeschwindigkeit = roundToTwoDecimalPlaces(angriffsgeschwindigkeit);
        this.haltbarkeit = haltbarkeit;
    }

    public int getId() {
        return id;
    }

    public ItemKategorie getItemKategorie() {
        return itemKategorie;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public Material getIcon() {
        return icon;
    }
    public ItemSeltenheit getItemSeltenheit() {
        return itemSeltenheit;
    }

    public double getSchaden() {
        return schaden;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAngriffsgeschwindigkeit() {
        return angriffsgeschwindigkeit;
    }

    public int getHaltbarkeit() {
        return haltbarkeit;
    }
    public boolean HaltbarkeitVerringern(){
        boolean zerstört = false;

        this.haltbarkeit--;

        if(this.haltbarkeit > 0){
            CompletableFuture.runAsync(() -> {
                Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_werte SET inhalt = '"+ this.haltbarkeit +"' WHERE item  = '" + id +"' AND schlussel = 'haltbarkeit'");
            });
        }else {
            /* Item Löschen, da es aufgebraucht wurde */
            delteItem();
            zerstört = true;
        }

        return zerstört;
    }

    public void delteItem(){
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("DELETE FROM shop_item_werte WHERE item  = '" + id +"'");
            Shopy.getInstance().getMySQLConntion().query("DELETE FROM shop_item WHERE id = '" + id +"'");
        });
    }

    private double roundToTwoDecimalPlaces(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
