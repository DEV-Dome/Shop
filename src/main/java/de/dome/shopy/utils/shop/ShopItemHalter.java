package de.dome.shopy.utils.shop;


import de.dome.shopy.Shopy;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import java.util.concurrent.CompletableFuture;

public class ShopItemHalter {

    int id;
    Shop shop;
    String typ;
    Location location;
    ShopItem item1;
    ShopItem item2;
    ShopItem item3;
    ShopItem item4;
    ShopItem item5;
    ShopItem item6;

    public ShopItemHalter(int id, Shop shop, String typ, Location location, ShopItem item1, ShopItem item2, ShopItem item3, ShopItem item4, ShopItem item5, ShopItem item6) {
        this.id = id;
        this.shop = shop;
        this.typ = typ;
        this.location = location;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.item6 = item6;
    }

    public Shop getShop() {
        return shop;
    }

    public String getTyp() {
        return typ;
    }

    public Location getLocation() {
        return location;
    }

    public ShopItem getItem1() {
        return item1;
    }

    public ShopItem getItem2() {
        return item2;
    }

    public ShopItem getItem3() {
        return item3;
    }

    public ShopItem getItem4() {
        return item4;
    }

    public ShopItem getItem5() {
        return item5;
    }

    public ShopItem getItem6() {
        return item6;
    }

    public void setItem1(ShopItem item) {
        if(item1 != null) this.item1.setAusgestellt(false, 0);

        if(item == null){
            this.item1 = null;

            CompletableFuture.runAsync(() -> {
                Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_1 = NULL WHERE id = " + getId());
            });

            return;
        }

        this.item1 = item;

        item.setAusgestellt(true, id);
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_1 = '"+ item.getId() +"' WHERE id = " + getId());
        });
    }

    public void setItem2(ShopItem item) {
        if(item2 != null) this.item2.setAusgestellt(false, 0);

        if(item == null){
            this.item2 = null;

            CompletableFuture.runAsync(() -> {
                Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_2 = NULL WHERE id = " + getId());
            });

            return;
        }

        this.item2 = item;

        item.setAusgestellt(true, id);
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_2 = '"+ item.getId() +"' WHERE id = " + getId());
        });
    }

    public void setItem3(ShopItem item) {
        if(item3 != null) this.item3.setAusgestellt(false, 0);

        if(item == null){
            this.item3 = null;

            CompletableFuture.runAsync(() -> {
                Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_3 = NULL WHERE id = " + getId());
            });

            return;
        }

        this.item3 = item;

        item.setAusgestellt(true, id);
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_3 = '"+ item.getId() +"' WHERE id = " + getId());
        });
    }

    public void setItem4(ShopItem item) {
        if(item4 != null) this.item4.setAusgestellt(false, 0);

        /*Nur ein Item Löschen*/
        if(item == null){
            this.item4 = null;

            CompletableFuture.runAsync(() -> {
                Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_4 = NULL WHERE id = " + getId());
            });

            return;
        }

        this.item4 = item;

        item.setAusgestellt(true, id);
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_4 = '"+ item.getId() +"' WHERE id = " + getId());
        });
    }

    public void setItem5(ShopItem item) {
        if(item5 != null) this.item5.setAusgestellt(false, 0);

        /*Nur ein Item Löschen*/
        if(item == null){
            this.item5 = null;

            CompletableFuture.runAsync(() -> {
                Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_5 = NULL WHERE id = " + getId());
            });

            return;
        }

        this.item5 = item;

        item.setAusgestellt(true, id);
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_5 = '"+ item.getId() +"' WHERE id = " + getId());
        });
    }
    public void setItem6(ShopItem item) {
        if(item6 != null) this.item6.setAusgestellt(false, 0);

        /*Nur ein Item Löschen*/
        if(item == null){
            this.item6 = null;

            CompletableFuture.runAsync(() -> {
                Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_6 = NULL WHERE id = " + getId());
            });

            return;
        }

        this.item6 = item;

        item.setAusgestellt(true, id);
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_6 = '"+ item.getId() +"' WHERE id = " + getId());
        });
    }

    public int getId() {
        return id;
    }
}
