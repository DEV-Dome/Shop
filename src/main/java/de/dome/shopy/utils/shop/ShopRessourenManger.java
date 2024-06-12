package de.dome.shopy.utils.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ShopRessourenManger {

    Shop shop;
    private Map<Ressource, Integer> shopRessoure;

    public ShopRessourenManger(Shop shop){
        this.shop = shop;
        this.shopRessoure = new LinkedHashMap<>();

        ladeResscuren();
    }

    public Shop getShop() {
        return shop;
    }

    public Map<Ressource, Integer> getShopRessoure() {
        return shopRessoure;
    }

    public int getRessourceValue(Ressource ressource){
        int ret = -1;

        for (Map.Entry<Ressource, Integer> shopRessoure : shopRessoure.entrySet()) {
            if(shopRessoure.getKey().getName().equalsIgnoreCase(ressource.getName())){
                ret = shopRessoure.getValue();
            }
        }

        return ret;
    }

    public void setRessourcenValue(Ressource ressource, int newValue){
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_ressource SET menge = '"+ newValue+"' WHERE shop = " + shop.shopId + " AND ressource = " + ressource.getId());
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
                    Ressource ressource = Ressource.getRessoureByName(resultRessourecs.getString("name"));

                    String queryRessourecsShop = "SELECT * FROM shop_ressource WHERE shop = " + shop.getShopId() + " AND ressource = " + resultRessourecs.getInt("id") + " LIMIT 1";
                    ResultSet resultRessourecsShop = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecsShop);
                    resultRessourecsShop.next();

                    if(resultRessourecsShop.getRow() == 0) {
                        Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_ressource (shop, ressource, menge) VALUES ('" + shop.getShopId() +"','"+ resultRessourecs.getInt("id") +"', 0)");

                        if(ressource != null){
                            shopRessoure.put(ressource, 0);
                        }
                    }else {
                        if(ressource != null){
                            shopRessoure.put(ressource, resultRessourecsShop.getInt("menge"));
                        }
                    }
                }
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
            }
        });
    }
}
