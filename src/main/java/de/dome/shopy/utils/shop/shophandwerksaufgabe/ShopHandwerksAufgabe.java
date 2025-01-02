package de.dome.shopy.utils.shop.shophandwerksaufgabe;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.items.Item;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItemVorlage;
import org.bukkit.Bukkit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class ShopHandwerksAufgabe {
    int id;

    Shop shop;

    ArrayList<ShopHandwerksAufgabeItem> shopHandwerksAufgabeItems;

    LocalDateTime gueltigBis;
    boolean erledigt;

    public ShopHandwerksAufgabe(Shop shop,int id, boolean erledigt) {
        this.shop = shop;
        this.erledigt = erledigt;
        this.id = id;
        shopHandwerksAufgabeItems = new ArrayList<>();
    }

    public Shop getShop() {
        return shop;
    }

    public static ShopHandwerksAufgabe erstelleAufgabe(Shop shop){
        ShopHandwerksAufgabe aufgabe = new ShopHandwerksAufgabe(shop, -1,false);

        int anzahlItem;
        final int[] shopHandwerksAufgabenKey = new int[1];
        Random random = new Random();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime gueltigBis = now.plusMinutes(30);
        aufgabe.setGueltigBis(gueltigBis);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String sqlDateTime = gueltigBis.format(formatter);

        ArrayList<Item> inFrageKommeItems = new ArrayList<>();

        if(Shopy.getInstance().isWahrscheinlichkeit(0.1)) anzahlItem = 2;
        else if(Shopy.getInstance().isWahrscheinlichkeit(0.06)) anzahlItem = 3;
        else if(Shopy.getInstance().isWahrscheinlichkeit(0.04)) anzahlItem = 4;
        else if(Shopy.getInstance().isWahrscheinlichkeit(0.025)) anzahlItem = 5;
        else anzahlItem = 1;


        CompletableFuture.runAsync(() -> {
            shopHandwerksAufgabenKey[0] = Shopy.getInstance().getMySQLConntion().queryReturnKey( "INSERT INTO shop_handwerks_aufgaben (shop , gueltig_bis) VALUES ('" + shop.getShopId() +"', '"+sqlDateTime+"')");
        }).thenRun(() ->{
            aufgabe.setId(shopHandwerksAufgabenKey[0]);
            /* Holle alle Items die in frage kommen*/
            for(ShopItemVorlage shopItemVorlage : shop.getShopItemVorlage()){
                if(shopItemVorlage.isfreigeschaltet() && shopItemVorlage.getShop().getShopItemKategorie().get(shopItemVorlage.getItem().getItemKategorie().getName()).isFreigeschaltet()){
                    inFrageKommeItems.add(shopItemVorlage.getItem());
                }
            }

            /* Items in der Aufgabe erstellen */
            ArrayList<Item> hinzugefuegteItems = new ArrayList<>();
            for(int i = 0; i <= anzahlItem; i++){
                Item item = inFrageKommeItems.get(random.nextInt(inFrageKommeItems.size()));


                String belohnung = "";
                if(Shopy.getInstance().isWahrscheinlichkeit(0.63)) {
                    if(Shopy.getInstance().isWahrscheinlichkeit(0.045)){
                        belohnung = "Schriftrollenpapier";
                    }else {
                        while (belohnung.equals("")){
                            Ressource ressource = Ressource.getRessourceList().get(random.nextInt(Ressource.getRessourceList().size()));
                            if(ressource.getType().equals("STANDART") || ressource.getType().equals("DUNGEON-LOOT")){
                                belohnung = ressource.getName();
                            }
                        }
                    }
                }

                int menge = 1;
                if(Shopy.getInstance().isWahrscheinlichkeit(0.15)) menge = 2;
                else if(Shopy.getInstance().isWahrscheinlichkeit(0.6)) menge = 3;

                int belohnungsMenge = 2;
                if(Shopy.getInstance().isWahrscheinlichkeit(0.24)) belohnungsMenge = 3;
                else if(Shopy.getInstance().isWahrscheinlichkeit(0.12)) belohnungsMenge = 4;
                else if(Shopy.getInstance().isWahrscheinlichkeit(0.07)) belohnungsMenge = 5;
                if(belohnung.equals("Schriftrollenpapier")) belohnungsMenge = 1;


                int finalMenge = menge;
                int finalBelohnungsMenge = belohnungsMenge;
                String finalBelohnung= belohnung;
                final int[] shopHandwerksAufgabenItemKey = {0};
                String finalBelohnung1 = belohnung;
                int finalMenge1 = menge;
                int finalBelohnungsMenge1 = belohnungsMenge;
                CompletableFuture.runAsync(() -> {
                     shopHandwerksAufgabenItemKey[0] = Shopy.getInstance().getMySQLConntion().queryReturnKey( "INSERT INTO shop_handwerks_aufgaben_item (item, menge, belohnung, belohnung_menge) " +
                            "VALUES ('" + item.getId() +"', '"+ finalMenge +"' , '"+ finalBelohnung  +"' , '"+ finalBelohnungsMenge +"')");

                    Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_handwerks_aufgaben_zuordnung (aufgabe, aufgaben_item) VALUES ('"+ shopHandwerksAufgabenKey[0] +"', '"+ shopHandwerksAufgabenItemKey[0] +"')");
                }).thenRun(()-> {
                    aufgabe.getShopHandwerksAufgabeItems().add(new ShopHandwerksAufgabeItem(item, shopHandwerksAufgabenItemKey[0], finalMenge1, finalBelohnung1, finalBelohnungsMenge1, 0));
                    hinzugefuegteItems.add(item);
                });
            }
        });

        return aufgabe;
    }
    public boolean isAufgabeAbgeschlossen(){
        boolean abgeschlossen = true;

        for(ShopHandwerksAufgabeItem aufgabeItem : shopHandwerksAufgabeItems){
            if(aufgabeItem.getMenge() > aufgabeItem.getFortschritt()){
                abgeschlossen = false;
            }
        }

        return abgeschlossen;
    }
    public ArrayList<ShopHandwerksAufgabeItem> getShopHandwerksAufgabeItems() {
        return shopHandwerksAufgabeItems;
    }

    public LocalDateTime getGueltigBis() {
        return gueltigBis;
    }

    public void setGueltigBis(LocalDateTime gueltigBis) {
        this.gueltigBis = gueltigBis;
    }
    public ArrayList<String> getBeschreibung(){
        ArrayList<String> beschreibung = new ArrayList<>();

        if(!erledigt){
            int belohungsGeld = 0;
            beschreibung.add("§7Aufgaben-ID: " + id);
            beschreibung.add("");
            beschreibung.add("§7Folgende Items musst du für die Aufgabe Liefern:");

            for(ShopHandwerksAufgabeItem aufgabenItem : shopHandwerksAufgabeItems){
                beschreibung.add("§e" + aufgabenItem.getFortschritt() + " §7/ §e" + aufgabenItem.getMenge() +" §7- " + aufgabenItem.getItem().getName());

                double addBelohungGeld = aufgabenItem.getItem().getItemPreis();
                addBelohungGeld = addBelohungGeld + (addBelohungGeld * ((double) 12 / 100));
                belohungsGeld += addBelohungGeld;
            }

            beschreibung.add("");
            beschreibung.add("§7Folgende Belohnung bekommst du dafür:");
            beschreibung.add("§a" + belohungsGeld + " §7€");
            for(ShopHandwerksAufgabeItem aufgabenItem : shopHandwerksAufgabeItems){
                if(aufgabenItem.getBelohnung().equals("")) continue;
                beschreibung.add("§a" + aufgabenItem.belohnungMenge + "x §7" + aufgabenItem.getBelohnung());
            }
            beschreibung.add("");
            beschreibung.add("§7Verfügbar bis zum §e" + gueltigBis.format(DateTimeFormatter.ofPattern("dd.MM.yyy")) + " §7um§e " + gueltigBis.format(DateTimeFormatter.ofPattern("HH:mm")) + " Uhr");
        }else {
            beschreibung.add("§7Du hast diese Aufgabe bereits erledigt.");
            beschreibung.add("§7Nächste Aufgabe um §e" + gueltigBis.format(DateTimeFormatter.ofPattern("dd.MM.yyy")) + " §7um§e " + gueltigBis.format(DateTimeFormatter.ofPattern("HH:mm")) + " Uhr");
        }

        return beschreibung;
    }

    public boolean isErledigt() {
        return erledigt;
    }

    public void setErledigt(boolean erledigt) {
        this.erledigt = erledigt;

        final int abgeschlossen;
        if(erledigt) abgeschlossen = 1;
        else abgeschlossen = 0;

        CompletableFuture.runAsync(() -> {
            Shopy.getInstance().getMySQLConntion().query("UPDATE shop_handwerks_aufgaben SET erledigt = '"+ abgeschlossen +"' WHERE id = " + id);
        });
    }
    public String gebeBelohnung(){
        String belohungen = "";
        int belohungsGeld = 0;

        for(ShopHandwerksAufgabeItem aufgabeItem : shopHandwerksAufgabeItems){
            double addBelohungGeld = aufgabeItem.getItem().getItemPreis();
            addBelohungGeld += addBelohungGeld * ((double) 12 / 100);

            belohungsGeld += addBelohungGeld;
            if(aufgabeItem.getBelohnung().equals("")) continue;

            Ressource ressource = Ressource.getRessoureByName(aufgabeItem.getBelohnung());

            if(belohungen.equals("")) belohungen +=  aufgabeItem.getMenge() + "x " + aufgabeItem.getBelohnung() + "";
            else belohungen += ", " + aufgabeItem.getMenge() + "x " + aufgabeItem.getBelohnung() + "";

            shop.getRessourenShopManger().setRessourcenValue(ressource,  shop.getRessourenShopManger().getRessourceValue(ressource) + aufgabeItem.getMenge());
        }

        if(belohungen.equals("")) belohungen +=  belohungsGeld + "€ ";
        else belohungen += ", " + belohungsGeld + "€ ";
        shop.getRessourenShopManger().setRessourcenValue(Ressource.getRessoureByName("Geld"),  shop.getRessourenShopManger().getRessourceValue(Ressource.getRessoureByName("Geld")) + belohungsGeld);


        return belohungen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
