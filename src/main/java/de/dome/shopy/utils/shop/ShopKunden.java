package de.dome.shopy.utils.shop;

import de.dome.shopy.Shopy;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class ShopKunden {

    private static ArrayList<String[]> KundenSkins = new ArrayList<>();
//    private ArrayList<NPC.Global> SpawnKundenListe = new ArrayList<>();

    private boolean goshopKundenManger = true;
    private int shopKundenMangerTaskId = -1;
    Shop shop = null;
    //SPÄTER LÖSCHEN!
    int zeahlerName = 1;

    public ShopKunden (Shop shop){
        this.shop = shop;

//        shopKundenManger();
    }

//    private void shopKundenManger(){
//
//        shopKundenMangerTaskId = Bukkit.getScheduler().runTaskTimer(Shopy.getInstance(), new Runnable() {
//            @Override
//            public void run() {
//                if(!goshopKundenManger) Bukkit.getScheduler().cancelTask(shopKundenMangerTaskId);
//
//                if(SpawnKundenListe.size() <= 2) spawnNpcInShop("Test-" + zeahlerName, -1, null);
//                zeahlerName++;
//            }
//        }, 0L, 100L).getTaskId(); // 6000 Ticks entsprechen 5 Minuten (20 Ticks = 1 Sekunde)
//    }
//    public void spawnNpcInShop(String name,  int skinIndex, Location spawnPoint){
//        if(!goshopKundenManger) return;
//
//        if(skinIndex == -1) skinIndex = (int)(Math.random() * KundenSkins.size());
//        if(spawnPoint == null) spawnPoint = shop.getZones().get(0).getRandomLocation();
//
//
//        ArrayList<String> npcText = new ArrayList<>();
//        npcText.add("§9" + name);
//
//        String simpleID = "Shop_" + shop.getShopId() + "_Kunde_" + System.currentTimeMillis();
//        NPC.Global npc = NPCLib.getInstance().generateGlobalNPC(Shopy.getInstance(), simpleID, spawnPoint);
//
//        npc.setText(npcText);
//        npc.setSkin(KundenSkins.get(skinIndex)[0], KundenSkins.get(skinIndex)[1]);
//
//        npc.createAllPlayers();
//        npc.show();
//
//        SpawnKundenListe.add(npc);
//        shop.getOwner().sendMessage(Shopy.getInstance().getPrefix() + "§9Debug: " + simpleID);
//        shop.getOwner().sendMessage(Shopy.getInstance().getPrefix() + "§9Debug: " + skinIndex);
//        shop.getOwner().sendMessage(Shopy.getInstance().getPrefix() + "§9Debug: " + name);
//
//    }
//    public void loescheAlleKunden(){
//        for (NPC.Global npc : SpawnKundenListe){
//            npc.destroy();
//        }
//    }

    public static void ladeKunden(){
        CompletableFuture.runAsync(() -> {
            try {
                String query = "SELECT * FROM kunden_skins";
                ResultSet result = Shopy.getInstance().getMySQLConntion().resultSet(query);

                while (result.next()){
                    String[] newSkin = {result.getString("value"), result.getString("signature")};
                    KundenSkins.add(newSkin);
                }

                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "Skins Fertig geladen!");
            } catch (SQLException e) { }
        });
    }

    public boolean isGoshopKundenManger() {
        return goshopKundenManger;
    }

    public void setGoshopKundenManger(boolean goshopKundenManger) {
        this.goshopKundenManger = goshopKundenManger;
    }
}
