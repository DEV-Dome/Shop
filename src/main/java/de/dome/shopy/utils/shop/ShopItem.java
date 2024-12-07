package de.dome.shopy.utils.shop;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.items.Item;
import de.dome.shopy.utils.items.ItemKategorie;
import de.dome.shopy.utils.items.ItemRessourecenKosten;
import de.dome.shopy.utils.items.ItemSeltenheit;
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
    Material icon;
    ItemSeltenheit itemSeltenheit;
    double schaden = 0;
    double angriffsgeschwindigkeit = 0;
    double rustung = 0;

    int haltbarkeit = 0;
    Item baseItem;

    public ShopItem(int id, int baseItemID, ItemKategorie itemKategorie, String name, String beschreibung, Material icon, ItemSeltenheit itemSeltenheit, double schaden, double angriffsgeschwindigkeit, double rustung, int haltbarkeit) {
        this.id = id;
        this.itemKategorie = itemKategorie;
        this.name = name;
        this.beschreibung = beschreibung;
        this.icon = icon;
        this.itemSeltenheit = itemSeltenheit;
        this.schaden = roundToTwoDecimalPlaces(schaden);
        this.angriffsgeschwindigkeit = roundToTwoDecimalPlaces(angriffsgeschwindigkeit);
        this.rustung = roundToTwoDecimalPlaces(rustung);
        this.haltbarkeit = haltbarkeit;
        baseItem = Item.getItemById(baseItemID);
    }

    public double getItemPreis(){
        double preis = 0;

        for(ItemRessourecenKosten irk : baseItem.getRessourecsKostenList()){
            double durschnittlicheKosten = (irk.getRessoure().getMinimaleKosten() + irk.getRessoure().getMaximaleKosten()) / 2;
            if(irk.getRessoure().getType().equalsIgnoreCase("DUNGEON-LOOT")) durschnittlicheKosten = 25.0;

            preis += (durschnittlicheKosten * irk.getMenge());
        }

        if(haltbarkeit < 3) preis/=4;

        return preis;
    }

    public ItemStack buildBaseItem(){
        ItemStack item = Shopy.getInstance().createItemWithLore(getIcon(), getVollenName(), getVolleBeschreibung());
        ItemMeta meta = item.getItemMeta();

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

        /* ID anzeigen*/
        volleBeschreibung.add("§7Item-ID: " + getId() + "");
        volleBeschreibung.add("§7Haltbarkeit: §e" + getHaltbarkeit() + "");

        volleBeschreibung.add("");

        String[] beschreibungsArray = getBeschreibung().split("\n");

        for (String itemBeschreibung : beschreibungsArray) {
            volleBeschreibung.add(itemBeschreibung.trim());
        }

        return volleBeschreibung;
    }

    public Material getIcon() {
        return icon;
    }
    public ItemSeltenheit getItemSeltenheit() {
        return itemSeltenheit;
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

    public int getHaltbarkeit() {
        return haltbarkeit;
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
            delteItem();
            zerstört = true;
        }

        return zerstört;
    }

    public void delteItem(){
        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("DELETE FROM shop_item_werte WHERE item  = '" + id +"'");
            Shopy.getInstance().getMySQLConntion().query("DELETE FROM shop_item WHERE id = '" + id +"'");
        });
    }

    private double roundToTwoDecimalPlaces(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
