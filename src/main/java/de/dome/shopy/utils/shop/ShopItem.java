package de.dome.shopy.utils.shop;

import de.dome.shopy.utils.items.Item;
import de.dome.shopy.utils.items.ItemKategorie;
import de.dome.shopy.utils.items.ItemSeltenheit;
import org.bukkit.Material;

import java.util.ArrayList;

public class ShopItem {

    int id;
    ItemKategorie itemKategorie;
    String name;
    String beschreibung;
    Material icon;
    ItemSeltenheit itemSeltenheit;

    public ShopItem(int id, ItemKategorie itemKategorie, String name, String beschreibung, Material icon, ItemSeltenheit itemSeltenheit) {
        this.id = id;
        this.itemKategorie = itemKategorie;
        this.name = name;
        this.beschreibung = beschreibung;
        this.icon = icon;
        this.itemSeltenheit = itemSeltenheit;
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
}
