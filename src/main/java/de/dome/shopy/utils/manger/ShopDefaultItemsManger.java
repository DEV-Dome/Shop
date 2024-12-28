package de.dome.shopy.utils.manger;

import de.dome.shopy.Shopy;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ShopDefaultItemsManger {

    private static ShopDefaultItemsManger INSTANCE;
    ItemStack ressourcenMark;
    ItemStack werkbank;
    ItemStack itemLager;
    ItemStack ressourcenLager;
    ItemStack aufgabenTisch;

    private ShopDefaultItemsManger(){
        String buildingHinweis   = "§7Dieser Gegenstand kann nur in der Shop-Welt";
        String buildingHinweis1  = "§7platziert werden. Und entfaltet da einen";
        String buildingHinweis2  = "§7besonderen Effet der über das,";
        String buildingHinweis3  = "§7gewöhnlich Maß in Minecraft hinaus geht.";

        ArrayList<String> beschreibung = new ArrayList<>();
        beschreibung.add("§5Mithilfe dieses Gegenstandes können");
        beschreibung.add("§5Ressourcen gekauft werden.");
        beschreibung.add("");
        beschreibung.add(buildingHinweis);
        beschreibung.add(buildingHinweis1);
        beschreibung.add(buildingHinweis2);
        beschreibung.add(buildingHinweis3);

        ressourcenMark = Shopy.getInstance().createItemWithLore(Material.LECTERN, "§9Ressourcen Mark", beschreibung);

        beschreibung = new ArrayList<>();
        beschreibung.add("§5Auf diesem Gegenstand können mächtige");
        beschreibung.add("§5Waffen und Werkzeuge hergestellt werden.");
        beschreibung.add("");
        beschreibung.add(buildingHinweis);
        beschreibung.add(buildingHinweis1);
        beschreibung.add(buildingHinweis2);
        beschreibung.add(buildingHinweis3);

        werkbank = Shopy.getInstance().createItemWithLore(Material.CRAFTING_TABLE, "§9Werkbank", beschreibung);

        beschreibung = new ArrayList<>();
        beschreibung.add("§5Pro Item lager können 10");
        beschreibung.add("§5hergestellte Items gelagert werden.");
        beschreibung.add("");
        beschreibung.add(buildingHinweis);
        beschreibung.add(buildingHinweis1);
        beschreibung.add(buildingHinweis2);
        beschreibung.add(buildingHinweis3);

        itemLager = Shopy.getInstance().createItemWithLore(Material.TRAPPED_CHEST, "§9Item Lager", beschreibung);

        beschreibung = new ArrayList<>();
        beschreibung.add("§5Pro Ressourcen lager können 10");
        beschreibung.add("§5Ressourcen gelagert werden");
        beschreibung.add("");
        beschreibung.add(buildingHinweis);
        beschreibung.add(buildingHinweis1);
        beschreibung.add(buildingHinweis2);
        beschreibung.add(buildingHinweis3);

        ressourcenLager = Shopy.getInstance().createItemWithLore(Material.BARREL, "§9Ressourcen Lager", beschreibung);

        beschreibung = new ArrayList<>();
        beschreibung.add("§5Erhalte in deinem Shop,");
        beschreibung.add("§5zugriff auf die Handwerkaufgaben");
        beschreibung.add("");
        beschreibung.add(buildingHinweis);
        beschreibung.add(buildingHinweis1);
        beschreibung.add(buildingHinweis2);
        beschreibung.add(buildingHinweis3);

        aufgabenTisch = Shopy.getInstance().createItemWithLore(Material.TARGET, "§9AufgabenTisch", beschreibung);

        INSTANCE = this;
    }

    public static ShopDefaultItemsManger INSTANCE() {
        if(INSTANCE == null) INSTANCE = new ShopDefaultItemsManger();

        return INSTANCE;
    }

    public ItemStack getRessourcenMark() {
        return ressourcenMark.clone();
    }

    public ItemStack getWerkbank() {
        return werkbank.clone();
    }

    public ItemStack getItemLager() {
        return itemLager.clone();
    }

    public ItemStack getRessourcenLager() {
        return ressourcenLager.clone();
    }

    public ItemStack getAufgabenTisch() {
        return aufgabenTisch.clone();
    }
}
