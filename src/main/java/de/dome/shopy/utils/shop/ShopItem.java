package de.dome.shopy.utils.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.items.*;
import dev.lone.itemsadder.api.CustomStack;
import net.citizensnpcs.api.trait.trait.Equipment;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class ShopItem {

    int id;
    ItemKategorie itemKategorie;
    String name;
    String beschreibung;
    String customModelData = "";
    ItemStack icon;
    ItemSeltenheit itemSeltenheit;
    ItemVerzauberung itemVerzauberung;
    ItemVerzauberungSet itemVerzauberungSet;
    double schaden = 0;
    double angriffsgeschwindigkeit = 0;
    double rustung = 0;

    int haltbarkeit = 0;
    int maxHaltbarkeit = 0;
    Item baseItem;
    boolean ausgestellt;

    public ShopItem(int id, int baseItemID, ItemKategorie itemKategorie, String name, String beschreibung, Material icon, ItemSeltenheit itemSeltenheit, ItemVerzauberung itemVerzauberung, ItemVerzauberungSet itemVerzauberungSet, double schaden, double angriffsgeschwindigkeit, double rustung, int haltbarkeit, int maxHaltbarkeit, boolean ausgestellt, String customModelData) {
        this.id = id;
        this.itemKategorie = itemKategorie;
        this.name = name;
        this.beschreibung = beschreibung;
        this.itemSeltenheit = itemSeltenheit;
        this.itemVerzauberung = itemVerzauberung;
        this.itemVerzauberungSet = itemVerzauberungSet;
        this.schaden = roundToTwoDecimalPlaces(schaden);
        this.angriffsgeschwindigkeit = roundToTwoDecimalPlaces(angriffsgeschwindigkeit);
        this.rustung = roundToTwoDecimalPlaces(rustung);
        this.haltbarkeit = haltbarkeit;
        this.maxHaltbarkeit = maxHaltbarkeit;
        this.ausgestellt = ausgestellt;

        baseItem = Item.getItemById(baseItemID);

        /* richtiges Icon setzten */
        this.icon = baseItem.getIcon();
        if(!customModelData.equals("")){
            ItemStack newIcon = CustomStack.getInstance(customModelData).getItemStack();
            this.icon = newIcon;
            this.customModelData = customModelData;
        }
    }

    public double getItemPreis(){
        double preis = baseItem.getItemPreis();

        if(haltbarkeit < 3) preis/=4;
        if(haltbarkeit > 3){
            preis += preis * ((double) (haltbarkeit - 3) / 100);
        }

        if(itemSeltenheit.getId() == 2) preis += preis * ((double) 7 / 100);
        else if(itemSeltenheit.getId() == 3) preis += preis * ((double) 14 / 100);
        else if(itemSeltenheit.getId() == 4) preis += preis * ((double) 22 / 100);
        else if(itemSeltenheit.getId() == 5) preis += preis * ((double) 31 / 100);
        else if(itemSeltenheit.getId() == 6) preis += preis * ((double) 40 / 100);

        if(itemVerzauberung != null){
            if(itemVerzauberung.getId() == 1){
                int prozentErhoert = 10;
                if(itemSeltenheit.getId() == 3) prozentErhoert = 15;
                if(itemSeltenheit.getId() == 4) prozentErhoert = 20;
                if(itemSeltenheit.getId() == 5) prozentErhoert = 25;

                preis += preis * ((double) (25 + prozentErhoert) / 100);
            } else preis += preis * ((double) 25 / 100);
        }
        if(itemVerzauberungSet != null) preis += preis * ((double) (30) / 100);


        return preis;
    }

    public Ressource getHauptMaterial(){
        return baseItem.getHauptMaterial();
    }

    public ItemStack buildBaseItem(){
        ItemStack item = baseItem.getIcon();
//        if(!customModelData.equals("")) item = getIcon();
//        if(itemVerzauberungSet != null){
//            if(Shopy.getInstance().getEquipmentSlot(item.getType()) == Equipment.EquipmentSlot.HELMET) item = CustomStack.getInstance(itemVerzauberungSet.getCustomModelDataHelm()).getItemStack();
//            if(Shopy.getInstance().getEquipmentSlot(item.getType()) == Equipment.EquipmentSlot.CHESTPLATE) item = CustomStack.getInstance(itemVerzauberungSet.getCustomModelDataRuestung()).getItemStack();
//            if(Shopy.getInstance().getEquipmentSlot(item.getType()) == Equipment.EquipmentSlot.LEGGINGS) item = CustomStack.getInstance(itemVerzauberungSet.getCustomModelDataHose()).getItemStack();
//            if(Shopy.getInstance().getEquipmentSlot(item.getType()) == Equipment.EquipmentSlot.BOOTS) item = CustomStack.getInstance(itemVerzauberungSet.getCustomModelDataSchuhe()).getItemStack();
//        }

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(getVollenName());
        meta.setLore(getVolleBeschreibung());
        item.setItemMeta(meta);


        // Entferne alle existierenden Attribute
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);

        // Füge einen neuen Angriffsschaden-Modifier hinzu
        if(schaden != 0){
            AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", schaden, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
            item.setItemMeta(meta);
        }

        if(angriffsgeschwindigkeit != 0){
            AttributeModifier speedModifier = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", angriffsgeschwindigkeit, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, speedModifier);
            item.setItemMeta(meta);
        }

        if(rustung != 0){
            AttributeModifier ruestungModifier = new AttributeModifier(UUID.randomUUID(), "generic.ARMOR", rustung, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR, ruestungModifier);
            item.setItemMeta(meta);
        }

        return item;
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
    public String getVollenName() {
        return "§9" + getName() +  " " + getItemSeltenheit().getFarbe() + " [" + getItemSeltenheit().getName() + "]";
    }


    public String getBeschreibung() {
        return beschreibung;
    }

    public ArrayList<String> getVolleBeschreibung() {
        ArrayList<String> volleBeschreibung = new ArrayList<>();

        /* Haltbarkeit Farbe definiern */
        String haltbarkeit = "§e" + getHaltbarkeit();
        if(getHaltbarkeit() == getMaxHaltbarkeit()) haltbarkeit = "§a" + getHaltbarkeit();
        if(getHaltbarkeit() > getMaxHaltbarkeit()) haltbarkeit = "§c" + getHaltbarkeit();

        /* ID anzeigen*/
        volleBeschreibung.add("§7Item-ID: " + getId());
        volleBeschreibung.add("§7Haltbarkeit: " + haltbarkeit + " §7/§e " + getMaxHaltbarkeit());
        volleBeschreibung.add("§7Durchschnittspreis: §e" + Math.round(getItemPreis() * 100.0) / 100.0);
        volleBeschreibung.add("");

        /* Verzauberung */
        if(itemVerzauberung != null){
            volleBeschreibung.add("§5" + itemVerzauberung.getName());
            volleBeschreibung.add("§5" + itemVerzauberung.getBeschreibung());
            volleBeschreibung.add("");

        }
        /* Set*/
        if(itemVerzauberungSet != null){
            volleBeschreibung.add("§2" + itemVerzauberungSet.getName() + ":");
            volleBeschreibung.add("§7" + itemVerzauberungSet.getBeschreibung());
            volleBeschreibung.add("");
            volleBeschreibung.add("§21ter Set Bonus (2 Teile):");
            volleBeschreibung.add("§7" + itemVerzauberungSet.getVerbesserung1());
            volleBeschreibung.add("");
            volleBeschreibung.add("§22ter Set Bonus (4 Teile):");
            volleBeschreibung.add("§7" + itemVerzauberungSet.getVerbesserung2());

            volleBeschreibung.add("");
        }
        /*Beschreibung*/
        String[] beschreibungsArray = getBeschreibung().split("\n");

        for (String itemBeschreibung : beschreibungsArray) {
            volleBeschreibung.add("§3" + itemBeschreibung.trim());
        }

        return volleBeschreibung;
    }

    public ItemStack getIcon() {
        return icon;
    }
    public ItemSeltenheit getItemSeltenheit() {
        return itemSeltenheit;
    }

    public ItemVerzauberung getItemVerzauberung() {
        return itemVerzauberung;
    }

    public double getSchaden() {
        return schaden;
    }

    public double getRustung() {
        return rustung;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAngriffsgeschwindigkeit() {
        return angriffsgeschwindigkeit;
    }

    public boolean isAusgestellt() {
        return ausgestellt;
    }

    public ItemVerzauberungSet getItemVerzauberungenSet() {
        return itemVerzauberungSet;
    }

    public void setAusgestellt(boolean ausgestellt, int austellID) {
        this.ausgestellt = ausgestellt;

        if(ausgestellt){
            CompletableFuture.runAsync(() -> {
                Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item SET ausgestellt  = '"+ austellID +"' WHERE id  = " + id );
            });
        }else {
            CompletableFuture.runAsync(() -> {
                Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item SET ausgestellt = NULL WHERE id  = " + id);
            });
        }
    }

    public void setItemVerzauberung(ItemVerzauberung itemVerzauberung) {
        this.itemVerzauberung = itemVerzauberung;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("DELETE FROM shop_item_werte WHERE item  = '" + id +"' AND schlussel = 'verzauberung'");
            Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_item_werte (item, schlussel, inhalt) VALUES ('" + id + "', 'verzauberung','" + itemVerzauberung.getId() + "')");
        });
    }
    public void setItemVerzauberungSet(ItemVerzauberungSet itemVerzauberungSet) {
        this.itemVerzauberungSet = itemVerzauberungSet;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("DELETE FROM shop_item_werte WHERE item  = '" + id +"' AND schlussel = 'verzauberung_set'");
            Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_item_werte (item, schlussel, inhalt) VALUES ('" + id + "', 'verzauberung_set','" + itemVerzauberungSet.getId() + "')");
        });
    }

    public void setRustung(double rustung) {
        this.rustung = rustung;
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_werte SET inhalt = '"+ this.rustung +"' WHERE item  = '" + id +"' AND schlussel = 'ruestung'");
        });
    }

    public void setAngriffsgeschwindigkeit(double angriffsgeschwindigkeit) {
        this.angriffsgeschwindigkeit = angriffsgeschwindigkeit;
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_werte SET inhalt = '"+ this.angriffsgeschwindigkeit +"' WHERE item  = '" + id +"' AND schlussel = 'angriffsgeschwindigkeit'");
        });
    }

    public void setSchaden(double schaden) {
        this.schaden = schaden;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_werte SET inhalt = '"+ this.schaden +"' WHERE item  = '" + id +"' AND schlussel = 'schaden'");
        });
    }

    public void setHaltbarkeit(int haltbarkeit) {
        this.haltbarkeit = haltbarkeit;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_werte SET inhalt = '"+ this.haltbarkeit +"' WHERE item  = '" + id +"' AND schlussel = 'haltbarkeit'");
        });
    }    public void setMaxHaltbarkeit(int maxHaltbarkeit) {
        this.maxHaltbarkeit = maxHaltbarkeit;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_werte SET inhalt = '"+ this.maxHaltbarkeit +"' WHERE item  = '" + id +"' AND schlussel = 'max_haltbarkeit'");
        });
    }

    public void setItemSeltenheit(ItemSeltenheit itemSeltenheit) {
        this.itemSeltenheit = itemSeltenheit;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item SET item_seltenheit = " + itemSeltenheit.getId() + " WHERE id  = " + id);
        });
    }

    public int getHaltbarkeit() {
        return haltbarkeit;
    }

    public int getMaxHaltbarkeit() {
        return maxHaltbarkeit;
    }

    public boolean HaltbarkeitVerringern(){
        boolean zerstört = false;

        this.haltbarkeit--;

        if(this.haltbarkeit > 0){
            CompletableFuture.runAsync(() -> {
                Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_werte SET inhalt = '"+ this.haltbarkeit +"' WHERE item  = '" + id +"' AND schlussel = 'haltbarkeit'");
            });
        }else {
            /* Item Löschen, da es aufgebraucht wurde */
            zerstört = true;
        }

        return zerstört;
    }

    public void deleteItem(){
            CompletableFuture.runAsync(() -> {
                Shopy.getInstance().getMySQLConntion().query("DELETE FROM shop_item_werte WHERE item  = '" + id +"'");

                Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_1 = NULL WHERE item_1 = " + id);
                Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_2 = NULL WHERE item_2 = " + id);
                Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_3 = NULL WHERE item_3 = " + id);
                Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_4 = NULL WHERE item_4 = " + id);
                Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_5 = NULL WHERE item_5 = " + id);
                Shopy.getInstance().getMySQLConntion().query("UPDATE shop_item_halter SET item_6 = NULL WHERE item_6 = " + id);

                Shopy.getInstance().getMySQLConntion().query("DELETE FROM shop_item WHERE id = '" + id +"'");
            });
    }

    public void aufwerten(){
        ItemSeltenheit newItemSeltenheit = ItemSeltenheit.getItemStufeById(getItemSeltenheit().getId() + 1);
        setItemSeltenheit(newItemSeltenheit);

        this.angriffsgeschwindigkeit += (this.angriffsgeschwindigkeit * ((double) 10 / 100));
        this.schaden += (this.schaden * ((double) 10 / 100));
        this.rustung += (this.rustung * ((double) 10 / 100));

        setMaxHaltbarkeit(getMaxHaltbarkeit() + 1);
    }

    private double roundToTwoDecimalPlaces(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }




}
