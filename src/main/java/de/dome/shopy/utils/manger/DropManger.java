package de.dome.shopy.utils.manger;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Dungeon;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.shop.ShopItem;
import de.dome.shopy.utils.shop.ShopRessourenManger;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class DropManger {

    public ArrayList<Ressource> dropMobLoot(Dungeon spielerDungeon, String[] mobLoot){
        ArrayList<Ressource> gedroppterLoot = new ArrayList<>();
        Player p = spielerDungeon.getShop().getOwner();
        Random random = new Random();

        String[] StandardLoot = {"Monsterhaut", "Monsterbeeren", "Monsterfaden", "Monsteraugen", "Monsterfleisch"};
        int dropItem = 0;

        /* Wahrscheinlichkeit je Dungeon Level ermitteln */
        double wahrscheinlichkeitDrop3 = 0.0110;
        double wahrscheinlichkeitDrop2 = 0.020;
        double wahrscheinlichkeitDrop1 = 0.40;
        double wahrscheinlichkeitStandartItem = 0;

        switch (spielerDungeon.getDungeonLevel()){
            case 1:
                wahrscheinlichkeitDrop3 = 0.0115;
                wahrscheinlichkeitDrop2 = 0.030;
                wahrscheinlichkeitDrop1 = 0.41;

                wahrscheinlichkeitStandartItem = 0.68;
                break;
            case 2:
                wahrscheinlichkeitDrop3 = 0.02;
                wahrscheinlichkeitDrop2 = 0.045;
                wahrscheinlichkeitDrop1 = 0.5;

                wahrscheinlichkeitStandartItem = 0.68;
                break;
            case 3:
                wahrscheinlichkeitDrop3 = 0.025;
                wahrscheinlichkeitDrop2 = 0.05;
                wahrscheinlichkeitDrop1 = 0.6;

                wahrscheinlichkeitStandartItem = 0.60;
                break;
            case 4:
                wahrscheinlichkeitDrop3 = 0.048;
                wahrscheinlichkeitDrop2 = 0.1;
                wahrscheinlichkeitDrop1 = 0.75;

                wahrscheinlichkeitStandartItem = 0.5;
                break;
        }
        /* Verzauberung */
        ItemStack chestplate = p.getInventory().getChestplate();
        if (chestplate != null && chestplate.hasItemMeta() && chestplate.getItemMeta().hasLore()) {
            String[] chestplateLoreArray = chestplate.getItemMeta().getLore().get(0).split(":");
            if (chestplateLoreArray[0].equals("§7Item-ID")) {
                int chestplateLoreArrayItemID = Integer.parseInt(chestplateLoreArray[1].substring(1));
                ShopItem chestplateShopItem = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopItemById(chestplateLoreArrayItemID);

                if (chestplateShopItem.getItemVerzauberung().getName().equals("Bär")) {
                    double multiply = 0.07;
                    if(chestplateShopItem.getItemSeltenheit().getId() == 3) multiply = 0.13;
                    if(chestplateShopItem.getItemSeltenheit().getId() == 4) multiply = 0.17;
                    if(chestplateShopItem.getItemSeltenheit().getId() == 5) multiply = 0.22;

                    wahrscheinlichkeitDrop3 += (double) wahrscheinlichkeitDrop3 * multiply;
                    wahrscheinlichkeitDrop2 += (double) wahrscheinlichkeitDrop2 * multiply;
                    wahrscheinlichkeitDrop1 += (double) wahrscheinlichkeitDrop1 * multiply;
                }
            }
        }
        ItemStack leggins = p.getInventory().getLeggings();
        if (leggins != null && leggins.hasItemMeta() && leggins.getItemMeta().hasLore()) {
            String[] legginsLoreArray = leggins.getItemMeta().getLore().get(0).split(":");
            if (legginsLoreArray[0].equals("§7Item-ID")) {
                int legginsLoreArrayItemID = Integer.parseInt(legginsLoreArray[1].substring(1));
                ShopItem legginsShopItem = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getShopItemById(legginsLoreArrayItemID);

                if (legginsShopItem.getItemVerzauberung().getName().equals("Glückstreffer")) {
                    double multiply = 0.07;
                    if(legginsShopItem.getItemSeltenheit().getId() == 3) multiply = 0.13;
                    if(legginsShopItem.getItemSeltenheit().getId() == 4) multiply = 0.17;
                    if(legginsShopItem.getItemSeltenheit().getId() == 5) multiply = 0.22;

                    wahrscheinlichkeitStandartItem += (double) wahrscheinlichkeitStandartItem * multiply;
                }
            }
        }


        /* Item Anzahl auswählen, aufgrundlage von warscheinlichkeiten */
        if(Shopy.getInstance().isWahrscheinlichkeit(wahrscheinlichkeitDrop3)) dropItem = 3;
        else if(Shopy.getInstance().isWahrscheinlichkeit(wahrscheinlichkeitDrop2)) dropItem = 2;
        else if(Shopy.getInstance().isWahrscheinlichkeit(wahrscheinlichkeitDrop1)) dropItem = 1;

        /* Schliefe zur auswahl des Items */
        for(int i = 1; i <= dropItem; i++){
            String dropItemString = "";

            /* Endscheiden welche art von Item fällt */
            if(Shopy.getInstance().isWahrscheinlichkeit(wahrscheinlichkeitStandartItem)){
                /* Standard item */
                int zufallsIndex = random.nextInt(StandardLoot.length);
                dropItemString = StandardLoot[zufallsIndex];
            }else {
                /*Mob Item */
                int zufallsIndex = random.nextInt(mobLoot.length);
                dropItemString = mobLoot[zufallsIndex];
            }

            /* Item an Spieler vergeben */
            Ressource ressource = Ressource.getRessoureByName(dropItemString);
            if(ressource != null){
                ShopRessourenManger shopRessourenManger = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger();
                shopRessourenManger.setRessourcenValue(ressource, shopRessourenManger.getRessourceValue(ressource) + 1);

                /* Loot speichern, für die zusamenfassung */
                int anzahlLoot = 1;
                if(spielerDungeon.getDungeonLoot().containsKey(ressource)){
                    anzahlLoot = spielerDungeon.getDungeonLoot().get(ressource) + 1;
                    spielerDungeon.getDungeonLoot().remove(ressource);
                }
                spielerDungeon.getDungeonLoot().put(ressource, anzahlLoot);
                gedroppterLoot.add(ressource);
            }
        }

        return gedroppterLoot;
    }
    public ArrayList<Ressource> dropAbschlussLoot(Dungeon spielerDungeon){
        ArrayList<Ressource> gedroppterLoot = new ArrayList<>();
        Player p = spielerDungeon.getShop().getOwner();
        Random random = new Random();

        String[] standardLoot = {"Holz", "Stein", "Eisen", "Geld" };
        String[] mobLoot = {"Zombiesleim", "Zombiedreck", "Skelett Arm", "Pfeilreste", "Spinnenauge", "Hasenfell", "Sauerstoffkristall", "Stab eines Dreistacks" };
        String[] aufwerterLoot = {"Aufwärtspulver Stufe 1", "Aufwärtspulver Stufe 2", "Dungeon Schüssel der Stufe 1" };
        String[] specialLoot = {"Einhornkristall", "Schriftrollenpapier", "Aufwärtspulver Stufe 3"};

        double wahrscheinlichkeitDrop4 = 0.0090;
        double wahrscheinlichkeitDrop3 = 0.020;
        double wahrscheinlichkeitDrop2 = 0.4;

        double wahrscheinlichkeitSpecialLoot = 0.02;
        double wahrscheinlichkeitAufwerterLoot = 0.05;
        double wahrscheinlichkeitMoblLoot = 0.3;

        switch (spielerDungeon.getDungeonLevel()){
            case 1:
                wahrscheinlichkeitDrop4 = 0.0098;
                wahrscheinlichkeitDrop3 = 0.027;
                wahrscheinlichkeitDrop2 = 0.5;

                wahrscheinlichkeitSpecialLoot = 0.05;
                wahrscheinlichkeitAufwerterLoot = 0.1;
                wahrscheinlichkeitMoblLoot = 0.4;
                break;
            case 2:
                wahrscheinlichkeitDrop4 = 0.0015;
                wahrscheinlichkeitDrop3 = 0.035;
                wahrscheinlichkeitDrop2 = 0.55;

                wahrscheinlichkeitSpecialLoot = 0.07;
                wahrscheinlichkeitAufwerterLoot = 0.25;
                wahrscheinlichkeitMoblLoot = 0.5;
                break;
            case 3:
                wahrscheinlichkeitDrop4 = 0.02;
                wahrscheinlichkeitDrop3 = 0.05;
                wahrscheinlichkeitDrop2 = 0.65;

                wahrscheinlichkeitSpecialLoot = 0.85;
                wahrscheinlichkeitAufwerterLoot = 0.3;
                wahrscheinlichkeitMoblLoot = 0.55;
                break;
            case 4:
                wahrscheinlichkeitDrop4 = 0.03;
                wahrscheinlichkeitDrop3 = 0.10;
                wahrscheinlichkeitDrop2 = 0.75;

                wahrscheinlichkeitSpecialLoot = 0.1;
                wahrscheinlichkeitAufwerterLoot = 0.45;
                wahrscheinlichkeitMoblLoot = 0.6;
                break;
        }

        int dropItem = 1;
        if(Shopy.getInstance().isWahrscheinlichkeit(wahrscheinlichkeitDrop4)) dropItem = 4;
        else if(Shopy.getInstance().isWahrscheinlichkeit(wahrscheinlichkeitDrop3)) dropItem = 3;
        else if(Shopy.getInstance().isWahrscheinlichkeit(wahrscheinlichkeitDrop2)) dropItem = 2;

        for(int i = 1; i <= dropItem; i++){
            String dropItemString = "";

            /* Endscheiden welche art von Item fällt */
            if(Shopy.getInstance().isWahrscheinlichkeit(wahrscheinlichkeitSpecialLoot)){
                /* Special  loot */

                int zufallsIndex = random.nextInt(specialLoot.length);
                dropItemString = specialLoot[zufallsIndex];
            }else if(Shopy.getInstance().isWahrscheinlichkeit(wahrscheinlichkeitAufwerterLoot)){
                /* Aufwerter Loot  */

                int zufallsIndex = random.nextInt(aufwerterLoot.length);
                dropItemString = aufwerterLoot[zufallsIndex];
            }else if(Shopy.getInstance().isWahrscheinlichkeit(wahrscheinlichkeitMoblLoot)){
                /* Mob Loot  */

                int zufallsIndex = random.nextInt(mobLoot.length);
                dropItemString = mobLoot[zufallsIndex];
            } else {
                /* Standart Loot  */

                int zufallsIndex = random.nextInt(standardLoot.length);
                dropItemString = standardLoot[zufallsIndex];
            }

            /* Item an Spieler vergeben */
            Ressource ressource = Ressource.getRessoureByName(dropItemString);
            if(ressource != null){
                ShopRessourenManger shopRessourenManger = Shopy.getInstance().getSpielerShops().get(p.getUniqueId()).getRessourenShopManger();

                /* Sollte geld vergeben werden, soll nicht nur 1 € ausgezahlt werden. */
                if(dropItemString.equalsIgnoreCase("geld")) shopRessourenManger.setRessourcenValue(ressource, shopRessourenManger.getRessourceValue(ressource) + 50);
                else shopRessourenManger.setRessourcenValue(ressource, shopRessourenManger.getRessourceValue(ressource) + 1);

                gedroppterLoot.add(ressource);
            }
        }

        return gedroppterLoot;
    }

}
