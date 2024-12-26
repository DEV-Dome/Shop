package de.dome.shopy.utils.shop.shophandwerksaufgabe;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.items.Item;

import java.util.concurrent.CompletableFuture;

public class ShopHandwerksAufgabeItem {

    int id;
    Item item;

    int menge;
    String belohnung;
    int belohnungMenge;
    int fortschritt;

    public ShopHandwerksAufgabeItem(Item item, int id,int menge, String belohnung, int belohnungMenge, int fortschritt) {
        this.id = id;
        this.item = item;
        this.menge = menge;
        this.belohnung = belohnung;
        this.belohnungMenge = belohnungMenge;
        this.fortschritt = fortschritt;
    }

    public Item getItem() {
        return item;
    }

    public int getMenge() {
        return menge;
    }

    public int getBelohnungMenge() {
        return belohnungMenge;
    }

    public String getBelohnung() {
        return belohnung;
    }

    public int getFortschritt() {
        return fortschritt;
    }

    public void setFortschritt(int fortschritt) {
        this.fortschritt = fortschritt;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_handwerks_aufgaben_item SET fortschritt = '"+ fortschritt +"' WHERE id = " + id);
        });
    }
}
