package de.dome.shopy.utils.shop.shophandwerksaufgabe;

import de.dome.shopy.utils.items.Item;

public class ShopHandwerksAufgabeItem {

    Item item;

    int menge;
    String belohnung;
    int belohnungMenge;

    public ShopHandwerksAufgabeItem(Item item, int menge, String belohnung, int belohnungMenge) {
        this.item = item;
        this.menge = menge;
        this.belohnung = belohnung;
        this.belohnungMenge = belohnungMenge;
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
}
