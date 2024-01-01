package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class RessourenShopManger {

    Shop shop;
    private HashMap<Ressoure, Integer> shopRessoure;

    public RessourenShopManger(Shop shop){
        this.shop = shop;
        this.shopRessoure = new HashMap<>();

        CompletableFuture.runAsync(() -> {
            try {
                String queryRessourecs = "SELECT * FROM ressource";

                ResultSet resultRessourecs = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecs);
                while(resultRessourecs.next()){
                    Ressoure ressoure = Ressoure.getRessoureByName(resultRessourecs.getString("name"));

                    String queryRessourecsShop = "SELECT * FROM shop_ressource WHERE shop = " + shop.getShopId() + " AND ressource = " + resultRessourecs.getInt("id") + " LIMIT 1";
                    ResultSet resultRessourecsShop = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecsShop);
                    resultRessourecsShop.next();

                    if(resultRessourecsShop.getRow() == 0) {
                        Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_ressource (shop, ressource, menge) VALUES ('" + shop.getShopId() +"','"+ resultRessourecs.getInt("id") +"', 0)");

                        if(ressoure != null){
                            shopRessoure.put(ressoure, 0);
                        }
                    }else {
                        if(ressoure != null){
                            shopRessoure.put(ressoure, resultRessourecsShop.getInt("menge"));
                        }
                    }
                }
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
            }
        });
    }

    public Shop getShop() {
        return shop;
    }

    public HashMap<Ressoure, Integer> getShopRessoure() {
        return shopRessoure;
    }
}
