package de.dome.shopy.utils.items;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressoure;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Item {
    int id;
    ItemKategorie itemKategorie;
    String name;
    String beschreibung;
    Material icon;
    ItemSeltenheit itemSeltenheit;
    public static ArrayList<Item> itemList;
    public ArrayList<ItemRessourecsKosten> ressourecsKostenList;

    public Item(int id, ItemKategorie itemKategorie, String name, String beschreibung, Material icon) {
        this.id = id;
        this.itemKategorie = itemKategorie;
        this.name = name;
        this.beschreibung = beschreibung;
        this.icon = icon;

        ressourecsKostenList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ItemKategorie getItemKategorie() {
        return itemKategorie;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public Material getIcon() {
        return icon;
    }

    public ArrayList<ItemRessourecsKosten> getRessourecsKostenList() {
        return ressourecsKostenList;
    }

    public ItemSeltenheit getItemSeltenheit() {
        return itemSeltenheit;
    }

    public static void registerItem(){
        CompletableFuture.runAsync(() -> {
            itemList = new ArrayList<>();

            try {
                String queryRessourecs = "SELECT * FROM item ORDER BY reinfolge";

                ResultSet resultItem = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecs);
                while (resultItem.next()) {
                    Item newItem = new Item(resultItem.getInt("id"), ItemKategorie.getItemKategorieById(resultItem.getInt("item_kategorie")), resultItem.getString("name"), resultItem.getString("beschreibung"), Material.getMaterial(resultItem.getString("icon")));

                    /*Kosten Laden*/
                    String queryRessourecsKosten = "SELECT * FROM item_kosten WHERE item = " + newItem.getId();
                    ResultSet resultItemKosten = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecsKosten);
                    while (resultItemKosten.next()) {
                        newItem.getRessourecsKostenList().add(new ItemRessourecsKosten(resultItemKosten.getInt("id"), newItem, Ressoure.getRessoureByID(resultItemKosten.getInt("ressource")), resultItemKosten.getInt("menge")));
                    }
                    /* Item Stufe */
                    newItem.itemSeltenheit = ItemSeltenheit.getIteStufeById(resultItem.getInt("item_seltenheit"));

                    Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + newItem.getItemSeltenheit().getFarbe() +"Item geladen: " + newItem.getName());

                    itemList.add(newItem);
                }
            }catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
            }
        });
    }
}
