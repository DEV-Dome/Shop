package de.dome.shopy.utils.items;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.shop.Shop;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class ItemVerzauberungSet {

    int id;
    String name;
    String beschreibung;
    String verbesserung1;
    String verbesserung2;
    Ressource aufwerter;
    String customModelDataHelm;
    String customModelDataRuestung;
    String customModelDataHose;
    String customModelDataSchuhe;
    static ArrayList<ItemVerzauberungSet> itemItemVerzauberungSetList;


    public ItemVerzauberungSet(int id, String name, String beschreibung, String verbesserung1, String verbesserung2, Ressource aufwerter, String customModelDataHelm, String customModelDataRuestung, String customModelDataHose, String customModelDataSchuhe) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.verbesserung1 = verbesserung1;
        this.verbesserung2 = verbesserung2;
        this.aufwerter = aufwerter;
        this.customModelDataHelm = customModelDataHelm;
        this.customModelDataRuestung = customModelDataRuestung;
        this.customModelDataHose = customModelDataHose;
        this.customModelDataSchuhe = customModelDataSchuhe;
    }

    public static void registerItemVerzauberungenSet(){
        CompletableFuture.runAsync(() -> {
            itemItemVerzauberungSetList = new ArrayList<>();

            try {
                String queryVerzauberungen = "SELECT * FROM item_verzauberungen_sets";

                ResultSet resultVerzauberungen = Shopy.getInstance().getMySQLConntion().resultSet(queryVerzauberungen);
                while (resultVerzauberungen.next()) {
                    Ressource aufwerter = Ressource.getRessoureByID(resultVerzauberungen.getInt("ressource"));
                    ItemVerzauberungSet itemVerzauberungSet = new ItemVerzauberungSet(resultVerzauberungen.getInt("id"), resultVerzauberungen.getString("name"), resultVerzauberungen.getString("beschreibung"), resultVerzauberungen.getString("verbesserung_1"), resultVerzauberungen.getString("verbesserung_2"), aufwerter, resultVerzauberungen.getString("custom_model_data_helm"), resultVerzauberungen.getString("custom_model_data_ruestung"), resultVerzauberungen.getString("custom_model_data_hose"), resultVerzauberungen.getString("custom_model_data_schuhe"));

                    itemItemVerzauberungSetList.add(itemVerzauberungSet);
                }
            }catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
            }
        });
    }

    public static ItemVerzauberungSet getItemVerzauberungenSetByName(String name){
        ItemVerzauberungSet itemVerzauberungSetReturn = null;

        for(ItemVerzauberungSet itemVerzauberungSet : getItemItemVerzauberungSetList()){
            if(itemVerzauberungSet.getName().equalsIgnoreCase(name)){
                itemVerzauberungSetReturn = itemVerzauberungSet;
                break;
            }
        }

        return itemVerzauberungSetReturn;
    }

    public static ItemVerzauberungSet getItemVerzauberungenSetById(int id){
        ItemVerzauberungSet itemVerzauberungSetReturn = null;

        for(ItemVerzauberungSet itemVerzauberungSet : getItemItemVerzauberungSetList()){
            if(itemVerzauberungSet.getId() == id){
                itemVerzauberungSetReturn = itemVerzauberungSet;
                break;
            }
        }

        return itemVerzauberungSetReturn;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ItemStack buildIconBaseItem(Shop shop){
        ArrayList<String> beschreibung = new ArrayList<>();
        beschreibung.add("§7Um das Item aufzuwerten, benötigst ");
        beschreibung.add("§7du den endsprechenden Aufwerter:");
        beschreibung.add("");
        beschreibung.add("§e" + shop.getShopRessourenManger().getRessourceValue(getAufwerter()) + " §7/ §e1 §7Aufwerter");
        beschreibung.add("");
        beschreibung.add("§7" + getBeschreibung());
        beschreibung.add("§2Bonus 1: §7" + getVerbesserung1());
        beschreibung.add("§2Bonus 2: §7" + getVerbesserung2());

        return Shopy.getInstance().createItemWithLore(getAufwerter().getIcon(), "§d" + getName(), beschreibung);
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public String getVerbesserung1() {
        return verbesserung1;
    }

    public String getVerbesserung2() {
        return verbesserung2;
    }

    public Ressource getAufwerter() {
        return aufwerter;
    }

    public String getCustomModelDataHelm() {
        return customModelDataHelm;
    }

    public String getCustomModelDataRuestung() {
        return customModelDataRuestung;
    }

    public String getCustomModelDataHose() {
        return customModelDataHose;
    }

    public String getCustomModelDataSchuhe() {
        return customModelDataSchuhe;
    }

    public static ArrayList<ItemVerzauberungSet> getItemItemVerzauberungSetList() {
        return itemItemVerzauberungSetList;
    }
}
