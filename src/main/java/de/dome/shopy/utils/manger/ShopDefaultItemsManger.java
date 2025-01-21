package de.dome.shopy.utils.manger;

import de.dome.shopy.Shopy;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class ShopDefaultItemsManger {

    private static ShopDefaultItemsManger INSTANCE;
    ItemStack ressourcenMark;
    ItemStack werkbank;
    ItemStack itemLager;
    ItemStack ressourcenLager;
    ItemStack aufgabenTisch;
    ItemStack rustungStander;
    ItemStack tresen;
    ItemStack upgrader;
    ItemStack verzauber;
    ItemStack reparaturTisch;

    HashMap<Material, ItemStack> standertItemMatrialList;

    private ShopDefaultItemsManger(){
        INSTANCE = this;
        standertItemMatrialList = new HashMap<>();

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

        ressourcenMark = Shopy.getInstance().createItemWithLore(Material.CARTOGRAPHY_TABLE, "§9Ressourcen Mark", beschreibung);
        standertItemMatrialList.put(ressourcenMark.getType(), ressourcenMark);

        beschreibung = new ArrayList<>();
        beschreibung.add("§5Auf diesem Gegenstand können mächtige");
        beschreibung.add("§5Waffen und Werkzeuge hergestellt werden.");
        beschreibung.add("");
        beschreibung.add(buildingHinweis);
        beschreibung.add(buildingHinweis1);
        beschreibung.add(buildingHinweis2);
        beschreibung.add(buildingHinweis3);

        werkbank = Shopy.getInstance().createItemWithLore(Material.CRAFTING_TABLE, "§9Werkbank", beschreibung);
        standertItemMatrialList.put(werkbank.getType(), werkbank);

        beschreibung = new ArrayList<>();
        beschreibung.add("§5Pro Item lager können 10");
        beschreibung.add("§5hergestellte Items gelagert werden.");
        beschreibung.add("");
        beschreibung.add(buildingHinweis);
        beschreibung.add(buildingHinweis1);
        beschreibung.add(buildingHinweis2);
        beschreibung.add(buildingHinweis3);

        itemLager = Shopy.getInstance().createItemWithLore(Material.TRAPPED_CHEST, "§9Item Lager", beschreibung);
        standertItemMatrialList.put(itemLager.getType(), itemLager);

        beschreibung = new ArrayList<>();
        beschreibung.add("§5Pro Ressourcen lager können 10");
        beschreibung.add("§5Ressourcen gelagert werden");
        beschreibung.add("");
        beschreibung.add(buildingHinweis);
        beschreibung.add(buildingHinweis1);
        beschreibung.add(buildingHinweis2);
        beschreibung.add(buildingHinweis3);

        ressourcenLager = Shopy.getInstance().createItemWithLore(Material.BARREL, "§9Ressourcen Lager", beschreibung);
        standertItemMatrialList.put(ressourcenLager.getType(), ressourcenLager);

        beschreibung = new ArrayList<>();
        beschreibung.add("§5Erhalte in deinem Shop,");
        beschreibung.add("§5zugriff auf die Handwerkaufgaben");
        beschreibung.add("");
        beschreibung.add(buildingHinweis);
        beschreibung.add(buildingHinweis1);
        beschreibung.add(buildingHinweis2);
        beschreibung.add(buildingHinweis3);

        aufgabenTisch = Shopy.getInstance().createItemWithLore(Material.TARGET, "§9AufgabenTisch", beschreibung);
        standertItemMatrialList.put(aufgabenTisch.getType(),aufgabenTisch);

        beschreibung = new ArrayList<>();
        beschreibung.add("§5Stelle Rüstungen aus, um die warschnlichkeit");
        beschreibung.add("§zu erhöhen diese zu verkaufen");
        beschreibung.add("");
        beschreibung.add(buildingHinweis);
        beschreibung.add(buildingHinweis1);
        beschreibung.add(buildingHinweis2);
        beschreibung.add(buildingHinweis3);

        rustungStander = Shopy.getInstance().createItemWithLore(Material.ARMOR_STAND, "§9Rüstungsständer", beschreibung);
        standertItemMatrialList.put(rustungStander.getType(), rustungStander);

        beschreibung = new ArrayList<>();
        beschreibung.add("§5Verkaufte Items welche über einem Tresen");
        beschreibung.add("§5verkauft wurden, bringen 10% mehr Erlös.");
        beschreibung.add("");
        beschreibung.add(buildingHinweis);
        beschreibung.add(buildingHinweis1);
        beschreibung.add(buildingHinweis2);
        beschreibung.add(buildingHinweis3);

        tresen = Shopy.getInstance().createItemWithLore(Material.LECTERN, "§9Tresen", beschreibung);
        standertItemMatrialList.put(tresen.getType(), tresen);

        beschreibung = new ArrayList<>();
        beschreibung.add("§5Mit diesem gerät kannst du,");
        beschreibung.add("§5deine Ausrüstung aufwerten!");
        beschreibung.add("");
        beschreibung.add(buildingHinweis);
        beschreibung.add(buildingHinweis1);
        beschreibung.add(buildingHinweis2);
        beschreibung.add(buildingHinweis3);

        upgrader = Shopy.getInstance().createItemWithLore(Material.SMITHING_TABLE, "§9Aufwerter", beschreibung);
        standertItemMatrialList.put(upgrader.getType(), upgrader);

        beschreibung = new ArrayList<>();
        beschreibung.add("§5Mit diesem gerät kannst du,");
        beschreibung.add("§5Magisch Items verzaubern!");
        beschreibung.add("");
        beschreibung.add(buildingHinweis);
        beschreibung.add(buildingHinweis1);
        beschreibung.add(buildingHinweis2);
        beschreibung.add(buildingHinweis3);

        verzauber = Shopy.getInstance().createItemWithLore(Material.AMETHYST_BLOCK, "§9Verzauberer", beschreibung);
        standertItemMatrialList.put(verzauber.getType(), verzauber);

        beschreibung = new ArrayList<>();
        beschreibung.add("§5Mit diesem gerät kannst du,");
        beschreibung.add("§5Die Haltbarkeit deiner, ");
        beschreibung.add("§5Items wiederherstellen.");
        beschreibung.add("");
        beschreibung.add(buildingHinweis);
        beschreibung.add(buildingHinweis1);
        beschreibung.add(buildingHinweis2);
        beschreibung.add(buildingHinweis3);

        reparaturTisch = Shopy.getInstance().createItemWithLore(Material.ANVIL, "§9Reparatur Tisch", beschreibung);
        standertItemMatrialList.put(reparaturTisch.getType(), reparaturTisch);
    }

    public static ShopDefaultItemsManger INSTANCE() {
        if(INSTANCE == null) INSTANCE = new ShopDefaultItemsManger();

        return INSTANCE;
    }

    public HashMap<Material, ItemStack> getStandertItemMatrialList() {
        return standertItemMatrialList;
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

    public ItemStack getRustungStander() {
        return rustungStander.clone();
    }

    public ItemStack getTresen() {
        return tresen.clone();
    }

    public ItemStack getUpgrader() {
        return upgrader.clone();
    }

    public ItemStack getVerzauber() {
        return verzauber.clone();
    }

    public ItemStack getReparaturTisch() {
        return reparaturTisch.clone();
    }
}
