package de.dome.shopy.utils.items;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.shop.Shop;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Item {
    int id;
    ItemKategorie itemKategorie;
    String name = "Nicht geladen ....";
    String beschreibung;
    ItemStack icon;
    ItemSeltenheit itemSeltenheit;
    String freischlatTyp;
    String customModelData = "";
    int freischaltItemID;
    int freischaltMenge;
    boolean immerFreigeschaltet;
    int shopXp;
    int meisterMenge;
    int kategorieXp;
    double minSchaden = 0;
    double maxSchaden = 0;
    double minAngriffsgeschwindigkeit = 0;
    double maxAngriffsgeschwindigkeit = 0;
    double minRuestung = 0;
    double maxRuestung = 0;
    Ressource hauptMaterial = null;
    public static ArrayList<Item> itemList;
    public ArrayList<ItemRessourecenKosten> ressourecsKostenList;

    public Item(int id, ItemKategorie itemKategorie, String name, String beschreibung, ItemStack icon) {
        this.id = id;
        this.itemKategorie = itemKategorie;
        this.name = name;
        this.beschreibung = beschreibung;
        this.icon = icon;

        ressourecsKostenList = new ArrayList<>();
    }

    public void freischalten(Shop shop){
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_vorlage SET freigeschaltet='JA' WHERE item ='" + getId() +"' AND shop = '" + shop.getShopId() +"'");
        });
    }

    public double getItemPreis(){
        double preis = 0;

        for(ItemRessourecenKosten irk : getRessourecsKostenList()){
            double durschnittlicheKosten = (irk.getRessoure().getMinimaleKosten() + irk.getRessoure().getMaximaleKosten()) / 2;
            if(irk.getRessoure().getType().equalsIgnoreCase("DUNGEON-LOOT")) durschnittlicheKosten = 25.0;

            preis += (durschnittlicheKosten * irk.getMenge());
        }

        return preis;
    }

    private Ressource ermitteleHauptMaterial(){
        Ressource ressource = null;
        int ressourceMenge = 0;

        for(ItemRessourecenKosten itemRessourecenKosten : getRessourecsKostenList()){
            if(ressourceMenge < itemRessourecenKosten.getMenge()){
                ressource = itemRessourecenKosten.getRessoure();
                ressourceMenge = itemRessourecenKosten.getMenge();
            }
        }

        return ressource;
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

    public ItemStack getIcon() {
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

    public boolean isImmerFreigeschaltet() {
        return immerFreigeschaltet;
    }

    public int getMeisterMenge() {
        return meisterMenge;
    }

    public double getMinSchaden() {
        return minSchaden;
    }

    public double getMaxSchaden() {
        return maxSchaden;
    }

    public double getMinAngriffsgeschwindigkeit() {
        return minAngriffsgeschwindigkeit;
    }

    public double getMaxAngriffsgeschwindigkeit() {
        return maxAngriffsgeschwindigkeit;
    }

    public double getMinRuestung() {
        return minRuestung;
    }

    public double getMaxRuestung() {
        return maxRuestung;
    }

    public String getCustomModelData() {
        return customModelData;
    }

    public Ressource getHauptMaterial() {
        return hauptMaterial;
    }

    public static void registerItem(){
        CompletableFuture.runAsync(() -> {
            itemList = new ArrayList<>();

            try {
                String queryRessourecs = "SELECT * FROM item ORDER BY reinfolge";

                ResultSet resultItem = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecs);
                while (resultItem.next()) {
                    ItemStack hinzugefuegendesIcons = Shopy.getInstance().createItem(Material.getMaterial(resultItem.getString("icon")), "");
                    Item newItem = new Item(resultItem.getInt("id"), ItemKategorie.getItemKategorieById(resultItem.getInt("item_kategorie")), resultItem.getString("name"), resultItem.getString("beschreibung"), hinzugefuegendesIcons);


                    /*Kosten Laden*/
                    String queryRessourecsKosten = "SELECT * FROM item_kosten WHERE item = " + newItem.getId();
                    ResultSet resultItemKosten = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecsKosten);

                    while (resultItemKosten.next()) {
                        newItem.getRessourecsKostenList().add(new ItemRessourecenKosten(resultItemKosten.getInt("id"), newItem, Ressource.getRessoureByID(resultItemKosten.getInt("ressource")), resultItemKosten.getInt("menge")));
                    }

                    /*Werte Laden*/
                    String queryRessourecsWerte = "SELECT * FROM item_werte WHERE item = " + newItem.getId();
                    ResultSet resultItemWerte = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecsWerte);

                    while (resultItemWerte.next()) {
                        if(resultItemWerte.getString("schlussel").equals("shop_xp")) newItem.shopXp = Integer.parseInt(resultItemWerte.getString("inhalt"));
                        if(resultItemWerte.getString("schlussel").equals("kategorie_xp")) newItem.kategorieXp = Integer.parseInt(resultItemWerte.getString("inhalt"));
                        if(resultItemWerte.getString("schlussel").equals("meister_menge")) newItem.meisterMenge = Integer.parseInt(resultItemWerte.getString("inhalt"));

                        if(resultItemWerte.getString("schlussel").equals("freigeschlatet_typ")) newItem.freischlatTyp = resultItemWerte.getString("inhalt");
                        if(resultItemWerte.getString("schlussel").equals("freigeschlatet_item")) newItem.freischaltItemID = Integer.parseInt(resultItemWerte.getString("inhalt"));
                        if(resultItemWerte.getString("schlussel").equals("freigeschlatet_menge")) newItem.freischaltMenge = Integer.parseInt(resultItemWerte.getString("inhalt"));

                        if(resultItemWerte.getString("schlussel").equals("min_schaden")) newItem.minSchaden = Double.parseDouble(resultItemWerte.getString("inhalt"));
                        if(resultItemWerte.getString("schlussel").equals("max_schaden")) newItem.maxSchaden = Double.parseDouble(resultItemWerte.getString("inhalt"));

                        if(resultItemWerte.getString("schlussel").equals("min_angriffsgeschwindigkeit")) newItem.minAngriffsgeschwindigkeit = Double.parseDouble(resultItemWerte.getString("inhalt"));
                        if(resultItemWerte.getString("schlussel").equals("max_angriffsgeschwindigkeit")) newItem.maxAngriffsgeschwindigkeit = Double.parseDouble(resultItemWerte.getString("inhalt"));

                        if(resultItemWerte.getString("schlussel").equals("min_ruestung")) newItem.minRuestung = Double.parseDouble(resultItemWerte.getString("inhalt"));
                        if(resultItemWerte.getString("schlussel").equals("max_ruestung")) newItem.maxRuestung = Double.parseDouble(resultItemWerte.getString("inhalt"));

                        /* Wenn eine Cotom Textur gefunden wird, anwenden! */
                        if(resultItemWerte.getString("schlussel").equals("custom_model_data")){
                            ItemStack newIcon = CustomStack.getInstance(resultItemWerte.getString("inhalt")).getItemStack();
                            newItem.icon = newIcon;
                            newItem.customModelData = resultItemWerte.getString("inhalt");
                        }

                        if(resultItemWerte.getString("schlussel").equals("immer_freigeschlatet")){
                            if(resultItemWerte.getString("inhalt").equalsIgnoreCase("ja")) newItem.immerFreigeschaltet = true;
                            else newItem.immerFreigeschaltet = false;
                        }
                    }

                    /* Item Stufe */
                    newItem.itemSeltenheit = ItemSeltenheit.getItemStufeById(resultItem.getInt("item_seltenheit"));
                    newItem.hauptMaterial = newItem.ermitteleHauptMaterial();

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
