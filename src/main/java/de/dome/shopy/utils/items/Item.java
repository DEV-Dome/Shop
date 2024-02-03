package de.dome.shopy.utils.items;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressoure;
import de.dome.shopy.utils.shop.Shop;
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
    String freischlatTyp;
    int freischaltItemID;
    int freischaltMenge;
    int shopXp;
    int kategorieXp;
    public static ArrayList<Item> itemList;
    public ArrayList<ItemRessourecenKosten> ressourecsKostenList;

    public Item(int id, ItemKategorie itemKategorie, String name, String beschreibung, Material icon,String freischlatTyp, int freischaltItemID, int freischaltMenge) {
        this.id = id;
        this.itemKategorie = itemKategorie;
        this.name = name;
        this.beschreibung = beschreibung;
        this.icon = icon;
        this.freischlatTyp = freischlatTyp;
        this.freischaltItemID = freischaltItemID;
        this.freischaltMenge = freischaltMenge;

        ressourecsKostenList = new ArrayList<>();
    }

    public void freischalten(Shop shop){
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_vorlage SET freigeschaltet='JA' WHERE item ='" + getId() +"' AND shop = '" + shop.getShopId() +"'");
        });
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

    public String getFreischlatTyp() {
        return freischlatTyp;
    }

    public int getFreischaltItemID() {
        return freischaltItemID;
    }

    public int getFreischaltMenge() {
        return freischaltMenge;
    }

    public ArrayList<ItemRessourecenKosten> getRessourecsKostenList() {
        return ressourecsKostenList;
    }

    public ItemSeltenheit getItemSeltenheit() {
        return itemSeltenheit;
    }

    public int getShopXp() {
        return shopXp;
    }

    public int getKategorieXp() {
        return kategorieXp;
    }

    public static void registerItem(){
        CompletableFuture.runAsync(() -> {
            itemList = new ArrayList<>();

            try {
                String queryRessourecs = "SELECT * FROM item ORDER BY reinfolge";

                ResultSet resultItem = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecs);
                while (resultItem.next()) {
                    Item newItem = new Item(resultItem.getInt("id"), ItemKategorie.getItemKategorieById(resultItem.getInt("item_kategorie")), resultItem.getString("name"), resultItem.getString("beschreibung"), Material.getMaterial(resultItem.getString("icon")), resultItem.getString("freischalt_typ"), resultItem.getInt("freischalt_item"), resultItem.getInt("freischalt_menge"));

                    /*Kosten Laden*/
                    String queryRessourecsKosten = "SELECT * FROM item_kosten WHERE item = " + newItem.getId();
                    ResultSet resultItemKosten = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecsKosten);
                    while (resultItemKosten.next()) {
                        newItem.getRessourecsKostenList().add(new ItemRessourecenKosten(resultItemKosten.getInt("id"), newItem, Ressoure.getRessoureByID(resultItemKosten.getInt("ressource")), resultItemKosten.getInt("menge")));
                    }
                    /*Werte Laden*/
                    String queryRessourecsWerte = "SELECT * FROM item_werte WHERE item = " + newItem.getId();
                    ResultSet resultItemWerte = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecsWerte);
                    while (resultItemWerte.next()) {
                        if(resultItemWerte.getString("wert").equals("shop_xp")) newItem.shopXp = Integer.parseInt(resultItemWerte.getString("value"));
                        if(resultItemWerte.getString("wert").equals("kategorie_xp")) newItem.kategorieXp = Integer.parseInt(resultItemWerte.getString("value"));
                    }
                    /* Item Stufe */
                    newItem.itemSeltenheit = ItemSeltenheit.getItemStufeById(resultItem.getInt("item_seltenheit"));

                    Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + newItem.getItemSeltenheit().getFarbe() +"Item geladen: " + newItem.getName());

                    itemList.add(newItem);
                }
            }catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
            }
        });
    }

    public static Item getItemByName(String name){
        Item ret = null;

        for(Item item : itemList){
            if(item.getName().equalsIgnoreCase(name)){
                ret = item;
                break;
            }
        }

        return ret;
    }
    public static Item getItemById(int id){
        Item ret = null;

        for(Item item : itemList){
            if(item.getId() == id){
                ret = item;
                break;
            }
        }

        return ret;
    }
    public static Item getItemByFreischaltItem(Item freischaltItem){
        Item ret = null;

        for(Item item : itemList){
            if(freischaltItem.getId() == item.getFreischaltItemID()){
                ret = item;
                break;
            }
        }

        return ret;
    }
}
