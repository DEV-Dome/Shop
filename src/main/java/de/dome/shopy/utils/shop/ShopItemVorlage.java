package de.dome.shopy.utils.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.items.Item;

import java.util.concurrent.CompletableFuture;

public class ShopItemVorlage {

    int id;
    Shop shop;
    Item item;
    int hergestellt;
    boolean meisterung;
    boolean freigeschaltet;


    public ShopItemVorlage(int id, Shop shop, Item item, int hergestellt, boolean meisterung, boolean freigeschaltet) {
        this.id = id;
        this.shop = shop;
        this.item = item;
        this.hergestellt = hergestellt;
        this.meisterung = meisterung;
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

    public boolean isMeisterung() {
        return meisterung;
    }

    public boolean addHerstellung(){
        hergestellt++;
        boolean ret = false;

        //Check ob, ob die Kategorie ein Level Aufgestiegen ist.
        if(hergestellt >= item.getFreischaltMenge() && item.getFreischaltItemID() != 0){
            Item freischaltItem = Item.getItemById(item.getFreischaltItemID());
            if(!shop.getShopItemVorlageByItem(freischaltItem.getId()).isfreigeschaltet()){
                shop.getShopItemVorlageByItem(freischaltItem.getId()).setFreigeschaltet(true);

                freischaltItem.freischalten(shop);
                ret = true;
            }
        }

        if(hergestellt >= item.getMeisterMenge()){
            Item meisterungsItem = Item.getItemById(item.getFreischaltItemID());
            if(!isMeisterung()){
               meisterung = true;

               meisterung(shop);
               ret = true;
            }
        }

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_vorlage SET hergestellt='"+ hergestellt +"' WHERE item ='" + item.getId() +"' AND shop = '" + shop.getShopId() +"'");
        });

        return ret;
    }
    private void meisterung(Shop shop){
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_vorlage SET meisterung='1' WHERE item ='" + getId() +"' AND shop = '" + shop.getShopId() +"'");
        });
    }

    public boolean isfreigeschaltet() {
        return freigeschaltet;
    }

    public void setFreigeschaltet(boolean freigeschaltet) {
        this.freigeschaltet = freigeschaltet;

        CompletableFuture.runAsync(() -> {
            String freigeschaltetString = "NEIN";
            if(freigeschaltet) freigeschaltetString = "JA";

            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_vorlage SET freigeschaltet='"+ freigeschaltetString +"' WHERE item ='" + item.getId() +"' AND shop = '" + shop.getShopId() +"'");
        });
    }
}
