package de.dome.shopy.utils.shop;

import de.dome.shopy.utils.items.Item;
import de.dome.shopy.utils.items.ItemKategorie;
import de.dome.shopy.utils.items.ItemSeltenheit;
import org.bukkit.Material;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ShopItem {

    int id;
    ItemKategorie itemKategorie;
    String name;
    String beschreibung;
    Material icon;
    ItemSeltenheit itemSeltenheit;
    double schaden = 0;
    double angriffsgeschwindigkeit = 0;

    public ShopItem(int id, ItemKategorie itemKategorie, String name, String beschreibung, Material icon, ItemSeltenheit itemSeltenheit, double schaden, double angriffsgeschwindigkeit) {
        this.id = id;
        this.itemKategorie = itemKategorie;
        this.name = name;
        this.beschreibung = beschreibung;
        this.icon = icon;
        this.itemSeltenheit = itemSeltenheit;
        this.schaden = roundToTwoDecimalPlaces(schaden);
        this.angriffsgeschwindigkeit = roundToTwoDecimalPlaces(angriffsgeschwindigkeit);
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

    private double roundToTwoDecimalPlaces(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
