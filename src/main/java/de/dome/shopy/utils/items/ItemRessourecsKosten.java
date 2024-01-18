package de.dome.shopy.utils.items;

import de.dome.shopy.utils.Ressoure;

public class ItemRessourecsKosten {

    int id;
    Item item;
    Ressoure ressoure;
    int menge;

    public ItemRessourecsKosten(int id, Item item, Ressoure ressoure, int menge) {
        this.id = id;
        this.item = item;
        this.ressoure = ressoure;
        this.menge = menge;
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public Ressoure getRessoure() {
        return ressoure;
    }

    public int getMenge() {
        return menge;
    }
}
