package de.dome.shopy.utils.shop;

import de.dome.shopy.utils.items.Item;

public class ShopItemVorlage {

    int id;
    Shop shop;
    Item item;
    int hergestellt;
    boolean freigeschlatet;

    public ShopItemVorlage(int id, Shop shop, Item item, int hergestellt, boolean freigeschlatet) {
        this.id = id;
        this.shop = shop;
        this.item = item;
        this.hergestellt = hergestellt;
        this.freigeschlatet = freigeschlatet;
    }

    public int getId() {
        return id;
    }

    public Shop getShop() {
        return shop;
    }

    public Item getItem() {
        return item;
    }

    public int getHergestellt() {
        return hergestellt;
    }

    public boolean isFreigeschlatet() {
        return freigeschlatet;
    }
}
