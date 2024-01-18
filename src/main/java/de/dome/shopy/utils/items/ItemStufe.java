package de.dome.shopy.utils.items;

import de.dome.shopy.Shopy;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class ItemStufe {

    int id;
    String name;
    String farbe;

    public static ArrayList<ItemStufe> itemStufeList;
    public ItemStufe(int id, String name, String farbe) {
        this.id = id;
        this.name = name;
        this.farbe = farbe;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFarbe() {
        return farbe;
    }

    public static void registerItemStufen(){
        CompletableFuture.runAsync(() -> {
            itemStufeList = new ArrayList<>();

            try {
                String queryRessourecs = "SELECT * FROM item_stufen";

                ResultSet resultItemStufe = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecs);
                while (resultItemStufe.next()) {
                    ItemStufe newItemStufe = new ItemStufe(resultItemStufe.getInt("id"), resultItemStufe.getString("name"), resultItemStufe.getString("farbe"));

                    itemStufeList.add(newItemStufe);
                }
            }catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
            }
        }).thenRun(() -> {
            Item.registerItem();
        });
    }
    public static ItemStufe getIteStufeByName(String name){
        ItemStufe returnItemStufe = null;

        for(ItemStufe itemStufe : itemStufeList){
            if(itemStufe.getName().equals(name)){
                returnItemStufe = itemStufe;
                break;
            }
        }

        return returnItemStufe;
    }
    public static ItemStufe getIteStufeById(int id){
        ItemStufe returnItemStufe = null;

        for(ItemStufe itemStufe : itemStufeList){
            if(itemStufe.getId() == id){
                returnItemStufe = itemStufe;
                break;
            }
        }

        return returnItemStufe;
    }
}
