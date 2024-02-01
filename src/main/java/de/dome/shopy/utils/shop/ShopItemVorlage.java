package de.dome.shopy.utils.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.items.Item;

import java.util.concurrent.CompletableFuture;

public class ShopItemVorlage {

    int id;
    Shop shop;
    Item item;
    int hergestellt;
    boolean freigeschaltet;

    public ShopItemVorlage(int id, Shop shop, Item item, int hergestellt, boolean freigeschaltet) {
        this.id = id;
        this.shop = shop;
        this.item = item;
        this.hergestellt = hergestellt;
        this.freigeschaltet = freigeschaltet;
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

    public boolean addHerstellung(){
        hergestellt++;
        boolean ret = false;

        //Check ob, ob die Kategorie ein Level Aufgestiegen ist.
        if(hergestellt >= item.getFreischaltMenge()){
            Item freischaltItem = Item.getItemById(item.getFreischaltItemID());
            if(!shop.getShopItemVorlageByItem(freischaltItem.getId()).isfreigeschaltet()){
                shop.getShopItemVorlageByItem(freischaltItem.getId()).setFreigeschaltet(true);

                freischaltItem.freischalten(shop);
                ret = true;
            }
        }

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_vorlage SET hergestellt='"+ hergestellt +"' WHERE item ='" + item.getId() +"' AND shop = '" + shop.getShopId() +"'");
        });

        return ret;
    }

    public boolean isfreigeschaltet() {
        return freigeschaltet;
    }

    public void setFreigeschaltet(boolean freigeschaltet) {
        this.freigeschaltet = freigeschaltet;
    }
}
