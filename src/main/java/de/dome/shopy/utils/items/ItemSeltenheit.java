package de.dome.shopy.utils.items;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class ItemSeltenheit {

    int id;
    String name;
    String farbe;

    int aufwerterMenge;
    Ressource aufwerter;

    public static ArrayList<ItemSeltenheit> itemSeltenheitList;
    public ItemSeltenheit(int id, String name, String farbe, int aufwerterMenge, Ressource aufwerter) {
        this.id = id;
        this.name = name;
        this.farbe = farbe;
        this.aufwerterMenge = aufwerterMenge;
        this.aufwerter = aufwerter;
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

    public int getAufwerterMenge() {
        return aufwerterMenge;
    }

    public Ressource getAufwerter() {
        return aufwerter;
    }

    public static void registerItemStufen(){
        CompletableFuture.runAsync(() -> {
            itemSeltenheitList = new ArrayList<>();

            try {
                String queryRessourecs = "SELECT * FROM item_seltenheit";

                ResultSet resultItemStufe = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecs);
                while (resultItemStufe.next()) {
                    Ressource aufwerter = null;
                    if(resultItemStufe.getInt("aufwertung_ressource") != 1) aufwerter = Ressource.getRessoureByID(resultItemStufe.getInt("aufwertung_ressource"));

                    ItemSeltenheit newItemSeltenheit = new ItemSeltenheit(resultItemStufe.getInt("id"), resultItemStufe.getString("name"), resultItemStufe.getString("farbe"), resultItemStufe.getInt("aufwertung_menge"), aufwerter);

                    itemSeltenheitList.add(newItemSeltenheit);
                }
            }catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
            }
        }).thenRun(() -> {
            Item.registerItem();
        });
    }
    public static ItemSeltenheit getItemStufeByName(String name){
        ItemSeltenheit returnItemSeltenheit = null;

        for(ItemSeltenheit itemSeltenheit : itemSeltenheitList){
            if(itemSeltenheit.getName().equals(name)){
                returnItemSeltenheit = itemSeltenheit;
                break;
            }
        }

        return returnItemSeltenheit;
    }
    public static ItemSeltenheit getItemStufeById(int id){
        ItemSeltenheit returnItemSeltenheit = null;

        for(ItemSeltenheit itemSeltenheit : itemSeltenheitList){
            if(itemSeltenheit.getId() == id){
                returnItemSeltenheit = itemSeltenheit;
                break;
            }
        }

        return returnItemSeltenheit;
    }
}
