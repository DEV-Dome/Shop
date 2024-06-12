package de.dome.shopy.utils.items;

import de.dome.shopy.utils.Ressource;

public class ItemRessourecenKosten {

    int id;
    Item item;
    Ressource ressource;
    int menge;

    public ItemRessourecenKosten(int id, Item item, Ressource ressource, int menge) {
        this.id = id;
        this.item = item;
        this.ressource = ressource;
        this.menge = menge;
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public Ressource getRessoure() {
        return ressource;
    }

    public int getMenge() {
        return menge;
    }
}
