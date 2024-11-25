package de.dome.shopy.utils.shop;

import de.dome.shopy.Shopy;
import dev.sergiferry.playernpc.api.NPC;
import dev.sergiferry.playernpc.api.NPCLib;
import org.bukkit.Location;
import org.bukkit.Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class ShopKunden {

    public static ArrayList<String[]> KundenSkins = new ArrayList<>();

    public ShopKunden (Location pos){
        int index = (int)(Math.random() * KundenSkins.size());

        ArrayList<String> npcText = new ArrayList<>();
        npcText.add("§9Test Kunde");
        npcText.add("§7Joa mal ein Erster Test!");

        NPC.Global npc = NPCLib.getInstance().generateGlobalNPC(Shopy.getInstance(), "TestKunde", pos);
        npc.setText(npcText);
        npc.setSkin(KundenSkins.get(index)[0], KundenSkins.get(index)[1]);
        npc.createAllPlayers();
        npc.show();
    }


    public static void ladeKunden(){
        CompletableFuture.runAsync(() -> {
            try {
                String query = "SELECT * FROM kunden_skins";
                ResultSet result = Shopy.getInstance().getMySQLConntion().resultSet(query);

                while (result.next()){
                    String[] newSkin = {result.getString("value"), result.getString("signature")};
                    KundenSkins.add(newSkin);
                }
            } catch (SQLException e) { }
        });
    }
}
