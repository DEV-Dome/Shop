package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class RessourenShopManger {

    Shop shop;
    private Map<Ressoure, Integer> shopRessoure;

    public RessourenShopManger(Shop shop){
        this.shop = shop;
        this.shopRessoure = new LinkedHashMap<>();

        ladeResscuren();
    }

    public Shop getShop() {
        return shop;
    }

    public Map<Ressoure, Integer> getShopRessoure() {
        return shopRessoure;
    }

    public int getRessourceValue(Ressoure ressoure){
        int ret = -1;

        for (Map.Entry<Ressoure, Integer> shopRessoure : shopRessoure.entrySet()) {
            if(shopRessoure.getKey().getName().equalsIgnoreCase(ressoure.getName())){
                ret = shopRessoure.getValue();
            }
        }

        return ret;
    }

    public void setRessourcenValue(Ressoure ressoure, int newValue){
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_ressource SET menge = '"+ newValue+"' WHERE shop = " + shop.shopId + " AND ressource = " + ressoure.getId());
            ladeResscuren();
        });

//        shopRessoure.remove(ressoure);
//        shopRessoure.put(ressoure, newValue);
    }

    private void ladeResscuren(){
        CompletableFuture.runAsync(() -> {
            try {
                String queryRessourecs = "SELECT * FROM ressource ORDER BY reinfolge";

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
}
