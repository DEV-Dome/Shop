package de.dome.shopy.utils.items;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItemKategorie;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class ItemKategorie {

    int id;
    String name;
    String beschreibung;
    Material icon;

    public static ArrayList<ItemKategorie> itemKategorieList;

    public ItemKategorie(int id, String name, String beschreibung, Material icon) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.icon = icon;
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

    public Material getIcon() {
        return icon;
    }

    public static void registerItemKategorie(){
        CompletableFuture<Void> itemKategorieDaten = CompletableFuture.runAsync(() -> {
            itemKategorieList = new ArrayList<>();

            try {
                String queryRessourecs = "SELECT * FROM item_kategorie ORDER BY reinfolge";

                ResultSet resultItemKategorie = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecs);
                while (resultItemKategorie.next()) {
                    ItemKategorie newItemKategorie = new ItemKategorie(resultItemKategorie.getInt("id"), resultItemKategorie.getString("name"), resultItemKategorie.getString("beschreibung"), Material.getMaterial(resultItemKategorie.getString("icon")));

                    itemKategorieList.add(newItemKategorie);
                }
            }catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
            }
        }).thenRun(() -> {
            ItemVerzauberung.registerItemVerzauberungen();
            ItemSeltenheit.registerItemStufen();
        });
    }

    public ArrayList<String> getAnzeigeBeschreibung(Shop shop){
        ArrayList<String> beschreibung = new ArrayList<>();
        beschreibung.add("");
        beschreibung.add("§7Level: §e" + shop.getShopItemKategorie().get(getName()).getLevel());
        beschreibung.add("§e" + shop.getShopItemKategorie().get(getName()).getAkeulleXP() + " §7XP /§e " + shop.getShopItemKategorie().get(getName()).getXpZumNachstenLevel() + " §7XP");

        return beschreibung;
    }

    public static ItemKategorie getItemKategorieById(int id){
        ItemKategorie returnItemKategorie = null;

        for(ItemKategorie itemKategorie : itemKategorieList){
            if(itemKategorie.getId() == id){
                returnItemKategorie = itemKategorie;
                break;
            }
        }

        return returnItemKategorie;
    }
    public static ItemKategorie getItemKategorieByName(String name){
        ItemKategorie returnItemKategorie = null;

        for(ItemKategorie itemKategorie : itemKategorieList){
            if(itemKategorie.getName().equals(name)){
                returnItemKategorie = itemKategorie;
                break;
            }
        }

        return returnItemKategorie;
    }

}
