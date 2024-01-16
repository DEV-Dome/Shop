package de.dome.shopy.utils.items;

import de.dome.shopy.Shopy;
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
    public static ArrayList<Item> itemList;

    public Item(int id, ItemKategorie itemKategorie, String name, String beschreibung, Material icon) {
        this.id = id;
        this.itemKategorie = itemKategorie;
        this.name = name;
        this.beschreibung = beschreibung;
        this.icon = icon;
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

    public static void registerItem(){
        CompletableFuture.runAsync(() -> {
            itemList = new ArrayList<>();

            try {
                String queryRessourecs = "SELECT * FROM item ORDER BY reinfolge";

                ResultSet resultItem = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecs);
                while (resultItem.next()) {
                    Item newItem = new Item(resultItem.getInt("id"), ItemKategorie.getItemKategorieById(resultItem.getInt("item_kategorie")), resultItem.getString("name"), resultItem.getString("beschreibung"), Material.getMaterial(resultItem.getString("icon")));

                    Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§5Item geladen: " + newItem.getName());

                    itemList.add(newItem);
                }
            }catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
            }
        });
    }
}
