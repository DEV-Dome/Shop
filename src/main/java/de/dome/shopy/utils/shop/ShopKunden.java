package de.dome.shopy.utils.shop;

import de.dome.shopy.Shopy;

import de.dome.shopy.utils.items.ItemKategorie;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class ShopKunden {

    private static ArrayList<String[]> kundenSkins = new ArrayList<>();
    private static String[] kundenName;
    NPC npc = null;
    String npcName;
    ArrayList<ItemKategorie>  interessanteKategorien = new ArrayList<>();
    ArrayList<ShopItem> interessanteItems = new ArrayList<>();
    ArrayList<ShopItem> gesuchteItems = new ArrayList<>();
    Shop shop;

    public ShopKunden (Shop shop){
        this.shop = shop;

        // Random-Objekt erstellen
        Random random = new Random();
        int skinIndex = random.nextInt(kundenSkins.size());
        npcName = kundenName[ random.nextInt(94)];

        /* Kunden Spawnen */
        npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§9" + npcName);
        npc.getOrAddTrait(SkinTrait.class).setSkinPersistent("Test", kundenSkins.get(skinIndex)[1], kundenSkins.get(skinIndex)[0]);
        npc.spawn(shop.getShopSpawn());

        /* NPC Bewegung */
        int versuche = 0;
        Location ziel = null;
        while (versuche <= 3){
            ziel = shop.getZones().get(0).getRandomLocation();
            if(npc.getNavigator().canNavigateTo(ziel)) break;
            versuche++;
        }

        if(ziel != null) npc.getNavigator().setTarget(ziel);
        else npc.teleport(ziel, PlayerTeleportEvent.TeleportCause.UNKNOWN);

        /* Kauf Wunsch deffiniern */
        int interessanteKategorieMenge = 2;
        if(Shopy.getInstance().isWahrscheinlichkeit(0.25)) interessanteKategorieMenge = 3;
        for(int i = 0; i < interessanteKategorieMenge; i++){
            if(interessanteKategorien.size() >= i){
                ItemKategorie option = ItemKategorie.itemKategorieList.get(random.nextInt(ItemKategorie.itemKategorieList.size()));
                if(!interessanteKategorien.contains(option)) interessanteKategorien.add(option);
            }
        }

        for(ShopItem item : shop.getShopItems()){
            if(interessanteKategorien.contains(item.getItemKategorie())){
                interessanteItems.add(item);
            }
        }

        /* zu Kaufende Items aussuchen */
        if(interessanteItems.size() != 0){
            int interessanteItemsMenge = 3;
            if(Shopy.getInstance().isWahrscheinlichkeit(0.1)) interessanteItemsMenge = 5;

            for(int i = 0; i < interessanteItemsMenge; i++){
                if(Shopy.getInstance().isWahrscheinlichkeit(0.37)){
                    gesuchteItems.add(null);
                }else {
                    ShopItem randomShopItem = interessanteItems.get(random.nextInt(interessanteItems.size()));
                    if(!gesuchteItems.contains(randomShopItem)) gesuchteItems.add(randomShopItem);
                    else gesuchteItems.add(null);
                }
            }
        }else {
            if(shop.getShopItems().size() != 0 && shop.getShopItems().size() <= 10){
                gesuchteItems.add(shop.getShopItems().get(0));
            }
        }

        /* Passendes Item ausrüsten */
        for(ItemKategorie optionAusgruestet : interessanteKategorien){
            for(ShopItemVorlage shopItemVorlage : shop.getShopItemVorlage()){
                if(!shopItemVorlage.isfreigeschaltet() && shopItemVorlage.getItem().getItemKategorie() == optionAusgruestet){
                    Equipment.EquipmentSlot equipmentSlot = Shopy.getInstance().getEquipmentSlot(shopItemVorlage.getItem().getIcon());
                    if(equipmentSlot != null){
                        npc.getOrAddTrait(Equipment.class).set(equipmentSlot, new ItemStack(shopItemVorlage.getItem().getIcon()));
                        break;
                    }
                }
            }
        }

    }


    public static void ladeKunden(){
        CompletableFuture.runAsync(() -> {
            try {
                String query = "SELECT * FROM kunden_skins";
                ResultSet result = Shopy.getInstance().getMySQLConntion().resultSet(query);

                while (result.next()){
                    String[] newSkin = {result.getString("value"), result.getString("signature")};
                    kundenSkins.add(newSkin);
                }

                kundenName = new String[]{"Anna", "Ben", "Clara", "David", "Ella", "Felix", "Greta", "Hannah",
                                "Isabel", "Jonas", "Klara", "Leon", "Mia", "Noah", "Olivia", "Paul",
                                "Quentin", "Rosa", "Simon", "Tina", "Uwe", "Valeria", "William", "Xenia",
                                "Yannick", "Zoe", "Lukas", "Emma", "Sophia", "Max", "Laura", "Tim",
                                "Julia", "Chris", "Nina", "Philipp", "Sarah", "Daniel", "Leonard",
                                "Tobias", "Carla", "Marie", "Johanna", "Alexander", "Peter", "Florian",
                                "Theresa", "Gabriel", "Matthias", "Luisa", "Charlotte", "Henrik", "Ella",
                                "Franz", "Amelie", "Julian", "Sebastian", "Nele", "Lara", "Jasmin",
                                "Marc", "Linda", "Oliver", "Viktor", "Nora", "Robin", "Kevin",
                                "Angelina", "Erik", "Lea", "Sophia", "Manuel", "Vanessa", "Jonah",
                                "Helena", "Lisa", "Christian", "Sandra", "Timo", "Stefan", "Caroline",
                                "Lena", "Victoria", "Fabian", "Melina", "Anja", "Dennis", "Jan",
                                "Sven", "Celine", "Katrin", "Tom", "Eva", "Ricarda"};

            } catch (SQLException e) { }
        });
    }

    public void loescheKunden(){
        if(npc.getNavigator().canNavigateTo(shop.getShopSpawn())) npc.getNavigator().setTarget(shop.shopSpawn);

        Bukkit.getScheduler().runTaskLater(Shopy.getInstance(), () -> {
            npc.despawn();
            npc.destroy();

            shop.getShopKunden().remove(this);

            Bukkit.getScheduler().runTask(Shopy.getInstance(), () -> {
                Shopy.getInstance().getScoreboardManger().setScoreBoard(shop.getOwner());
            });
        }, 7 * 20L);

    }

    public NPC getNpc() {
        return npc;
    }

    public String getNpcName() {
        return npcName;
    }

    public ArrayList<ItemKategorie> getInteressanteKategorien() {
        return interessanteKategorien;
    }

    public ArrayList<ShopItem> getInteressanteItems() {
        return interessanteItems;
    }

    public ArrayList<ShopItem> getGesuchteItems() {
        return gesuchteItems;
    }
}
