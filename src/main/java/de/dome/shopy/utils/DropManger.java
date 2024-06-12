package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.shop.ShopRessourenManger;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;

public class DropManger {

    public ArrayList<Ressource> dropMobLoot(Dungeon spielerDungeon, String[] mobLoot){
        ArrayList<Ressource> gedroppterLoot = new ArrayList<>();
        Player p = spielerDungeon.getShop().getOwner();
        Random random = new Random();

        String[] StandardLoot = {"Monsterhaut", "Monsterbeeren", "Monsterfaden", "Monsteraugen", "Monsterfleisch"};
        int dropItem = 0;

        /* Item Anzahl auswählen, aufgrundlage von warscheinlichkeiten */
        if(Shopy.getInstance().isWahrscheinlichkeit(0.0115)) dropItem = 3;
        else if(Shopy.getInstance().isWahrscheinlichkeit(0.030)) dropItem = 2;
        else if(Shopy.getInstance().isWahrscheinlichkeit(0.41)) dropItem = 1;

        /* Schliefe zur auswahl des Items */
        for(int i = 1; i <= dropItem; i++){
            String dropItemString = "";

            /* Endscheiden welche art von Item fällt */
            if(Shopy.getInstance().isWahrscheinlichkeit(0.68)){
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
        String[] specialLoot = {"Mondkristall", "Schriftrollenpapier", "Aufwärtspulver Stufe 3"};
        int dropItem = 1;
        if(Shopy.getInstance().isWahrscheinlichkeit(0.0098)) dropItem = 4;
        else if(Shopy.getInstance().isWahrscheinlichkeit(0.027)) dropItem = 3;
        else if(Shopy.getInstance().isWahrscheinlichkeit(0.5)) dropItem = 2;

        for(int i = 1; i <= dropItem; i++){
            String dropItemString = "";

            /* Endscheiden welche art von Item fällt */
            if(Shopy.getInstance().isWahrscheinlichkeit(0.05)){
                /* Special  loot */

                int zufallsIndex = random.nextInt(specialLoot.length);
                dropItemString = specialLoot[zufallsIndex];
            }else if(Shopy.getInstance().isWahrscheinlichkeit(0.1)){
                /* Aufwerter Loot  */

                int zufallsIndex = random.nextInt(aufwerterLoot.length);
                dropItemString = aufwerterLoot[zufallsIndex];
            }else if(Shopy.getInstance().isWahrscheinlichkeit(0.4)){
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
