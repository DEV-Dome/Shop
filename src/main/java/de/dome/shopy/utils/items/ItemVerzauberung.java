package de.dome.shopy.utils.items;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class ItemVerzauberung {

    int id;
    String name;
    String beschreibung;
    ItemKategorie itemKategorie;

    static ArrayList<ItemVerzauberung> itemItemVerzauberungList;


    public ItemVerzauberung(int id, String name, String beschreibung, ItemKategorie itemKategorie) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.itemKategorie = itemKategorie;
    }

    public static void registerItemVerzauberungen(){
        CompletableFuture.runAsync(() -> {
            itemItemVerzauberungList = new ArrayList<>();

            try {
                String queryVerzauberungen = "SELECT * FROM item_verzauberungen";

                ResultSet resultVerzauberungen = Shopy.getInstance().getMySQLConntion().resultSet(queryVerzauberungen);
                while (resultVerzauberungen.next()) {
                    ItemKategorie zugeordneteItemKategorie = ItemKategorie.getItemKategorieById(resultVerzauberungen.getInt("item_kategorie"));

                    ItemVerzauberung itemVerzauberung = new ItemVerzauberung(resultVerzauberungen.getInt("id"), resultVerzauberungen.getString("name"), resultVerzauberungen.getString("beschreibung"), zugeordneteItemKategorie);

                    itemItemVerzauberungList.add(itemVerzauberung);
                }
            }catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
            }
        });
    }
    public static ItemVerzauberung getItemVerzauberungById(int id){
        ItemVerzauberung returnItemVerzauberung = null;

        for(ItemVerzauberung itemVerzauberung : itemItemVerzauberungList){
            if(itemVerzauberung.getId() == id){
                returnItemVerzauberung = itemVerzauberung;
                break;
            }
        }

        return returnItemVerzauberung;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public ItemKategorie getItemKategorie() {
        return itemKategorie;
    }

    public static ArrayList<ItemVerzauberung> getItemItemVerzauberungList() {
        return itemItemVerzauberungList;
    }
}
