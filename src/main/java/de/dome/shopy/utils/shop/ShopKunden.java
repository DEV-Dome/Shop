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
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Lectern;
import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Directional;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
    ArrayList<Vector> npcLaufWeg = new ArrayList<>();
    ArrayList<ShopItemHalter> angeschuateItemHalter = new ArrayList<>();
    Shop shop;
    Location ziel;

    public ShopKunden (Shop shop){
        this.shop = shop;

        Random random = new Random();
        int skinIndex = random.nextInt(kundenSkins.size());
        npcName = kundenName[random.nextInt(94)];

        /* Kunden Spawnen */
        npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§9" + npcName);
        npc.getOrAddTrait(SkinTrait.class).setSkinPersistent("Test", kundenSkins.get(skinIndex)[1], kundenSkins.get(skinIndex)[0]);
        npc.spawn(shop.getShopSpawn());

        /* NPC Bewegung */
        ziel = null;
        int versuche = 0;

        if(shop.getTresenPostion() != null){
            Block tresen = shop.getTresenPostion().getBlock();

            if (tresen.getType() == Material.LECTERN) {
                if (tresen.getBlockData() instanceof Lectern) {
                    Lectern lectern = (Lectern) tresen.getBlockData();

                    Vector facing = lectern.getFacing().getDirection().multiply(shop.getShopKunden().size() + 1);

                    ziel = shop.getTresenPostion().clone().subtract(facing);
                    if(npc.getNavigator().canNavigateTo(ziel)) versuche = 100;
                }
            }
        }

        while (versuche <= 10){
            ziel = shop.getZones().get(random.nextInt(shop.getZones().size())).getRandomLocation();
            if(npc.getNavigator().canNavigateTo(ziel)) break;
            versuche++;
        }


        if(ziel != null && npc.getNavigator().canNavigateTo(ziel)){
            int maxStops = 3;
            for(ShopItemHalter shopItemHalter : shop.getShopItemHalter().values()){
                if(npcLaufWeg.size() > maxStops) break;
                if(Shopy.getInstance().isWahrscheinlichkeit(0.55)){
                    if(npc.getNavigator().canNavigateTo(shopItemHalter.getLocation())){
                        npcLaufWeg.add(shopItemHalter.getLocation().toVector());
                        angeschuateItemHalter.add(shopItemHalter);
                    }
                }
            }

            try {
                npcLaufWeg.add(ziel.toVector());
                npc.getNavigator().setTarget(npcLaufWeg);
            } catch (IllegalStateException e){
                /* Wenn der Kunden nicht zum Ziel geschickt werden kann, diesen Löschen und neuen erstellen */
                loescheKunden();
                shop.getShopKunden().add(new ShopKunden(shop));
            }
        } else loescheKunden();


        /* Kauf Wunsch deffiniern */
        int interessanteKategorieMenge = 2;

        double wahrscheinlichkeit = 0.1;
        if(shop.getZusaetzlicheKategorieWahrscheinlichkeit() > 0) wahrscheinlichkeit += (shop.getZusaetzlicheKategorieWahrscheinlichkeit() / 100);

        if(Shopy.getInstance().isWahrscheinlichkeit(wahrscheinlichkeit)) interessanteKategorieMenge = 3;

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

            wahrscheinlichkeit = 0.1;
            if(shop.getZusaetzlichesItemWahrscheinlichkeit() > 0) wahrscheinlichkeit += (shop.getZusaetzlichesItemWahrscheinlichkeit() / 100);

            if(Shopy.getInstance().isWahrscheinlichkeit(wahrscheinlichkeit)) interessanteItemsMenge = 5;

            for(int i = 0; i < interessanteItemsMenge; i++){
                if(Shopy.getInstance().isWahrscheinlichkeit(0.37)){
                    gesuchteItems.add(null);
                }else {
                    ShopItemHalter shopItemHalter = null;
                    if(angeschuateItemHalter.size() != 0) shopItemHalter = angeschuateItemHalter.get(random.nextInt(angeschuateItemHalter.size()));

                    if(Shopy.getInstance().isWahrscheinlichkeit(0.125) && shopItemHalter != null){
                        if(shopItemHalter.getItem1() != null || shopItemHalter.getItem2() != null || shopItemHalter.getItem3() != null || shopItemHalter.getItem4() != null ){
                            int solt = random.nextInt(4);
                            ShopItem randomShopItem = null;

                            if(solt == 1 && shopItemHalter.getItem1() != null) randomShopItem = shopItemHalter.getItem1();
                            else if(solt == 2 && shopItemHalter.getItem2() != null) randomShopItem = shopItemHalter.getItem2();
                            else if(solt == 3 && shopItemHalter.getItem3() != null) randomShopItem = shopItemHalter.getItem3();
                            else if(solt == 4 && shopItemHalter.getItem4() != null) randomShopItem = shopItemHalter.getItem4();
                            else {
                                randomShopItem = interessanteItems.get(random.nextInt(interessanteItems.size()));
                            }

                            gesuchteItems.add(randomShopItem);
                        }else {
                            ShopItem randomShopItem = interessanteItems.get(random.nextInt(interessanteItems.size()));
                            if(!gesuchteItems.contains(randomShopItem)) gesuchteItems.add(randomShopItem);
                            else gesuchteItems.add(null);
                        }
                    }else {
                        ShopItem randomShopItem = interessanteItems.get(random.nextInt(interessanteItems.size()));
                        if(!gesuchteItems.contains(randomShopItem)) gesuchteItems.add(randomShopItem);
                        else gesuchteItems.add(null);
                    }
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
                    Equipment.EquipmentSlot equipmentSlot = Shopy.getInstance().getEquipmentSlot(shopItemVorlage.getItem().getIcon().getType());
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

        if(shop.getTresenPostion() != null){
            Location vorher = null;

            for (ShopKunden kunden : shop.getShopKunden()){
                if(kunden.npc.getUniqueId() == npc.getUniqueId() || vorher != null){
                    if(vorher != null) {
                        kunden.npc.getNavigator().cancelNavigation();
                        kunden.npc.getNavigator().setTarget(vorher);
                    }

                    vorher = kunden.getZiel();
                    kunden.setZiel(vorher);
                }
            }
        }

        if(npc.getNavigator().canNavigateTo(shop.getShopSpawn())) {
            try {
                npc.getNavigator().cancelNavigation();
                npc.getNavigator().setTarget(shop.shopSpawn);
            } catch (IllegalStateException e){}
        }

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

    public Location getZiel() {
        return ziel;
    }

    public void setZiel(Location ziel) {
        this.ziel = ziel;
    }
}
