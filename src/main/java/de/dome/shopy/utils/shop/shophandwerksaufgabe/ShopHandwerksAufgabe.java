package de.dome.shopy.utils.shop.shophandwerksaufgabe;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.items.Item;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItemVorlage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class ShopHandwerksAufgabe {

    Shop shop;

    ArrayList<ShopHandwerksAufgabeItem> shopHandwerksAufgabeItems;

    LocalDateTime gueltigBis;

    public ShopHandwerksAufgabe(Shop shop) {
        this.shop = shop;
        shopHandwerksAufgabeItems = new ArrayList<>();
    }

    public Shop getShop() {
        return shop;
    }

    public static ShopHandwerksAufgabe erstelleAufgabe(Shop shop){
        ShopHandwerksAufgabe aufgabe = new ShopHandwerksAufgabe(shop);

        int anzahlItem;
        final int[] shopHandwerksAufgabenKey = new int[1];
        Random random = new Random();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime gueltigBis = now.plusMinutes(30);
        aufgabe.setGueltigBis(gueltigBis);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String sqlDateTime = gueltigBis.format(formatter);

        ArrayList<Item> inFrageKommeItems = new ArrayList<>();

        if(Shopy.getInstance().isWahrscheinlichkeit(0.03)) anzahlItem = 5;
        else if(Shopy.getInstance().isWahrscheinlichkeit(0.07)) anzahlItem = 4;
        else if(Shopy.getInstance().isWahrscheinlichkeit(0.095)) anzahlItem = 3;
        else if(Shopy.getInstance().isWahrscheinlichkeit(0.16)) anzahlItem = 2;
        else anzahlItem = 1;


        CompletableFuture.runAsync(() -> {
            shopHandwerksAufgabenKey[0] = Shopy.getInstance().getMySQLConntion().queryReturnKey( "INSERT INTO shop_handwerks_aufgaben (shop , gueltig_bis) VALUES ('" + shop.getShopId() +"', '"+sqlDateTime+"')");
        }).thenRun(() ->{
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
                    while (belohnung.equals("")){
                        Ressource ressource = Ressource.getRessourceList().get(random.nextInt(Ressource.getRessourceList().size()));
                        if(ressource.getType().equals("STANDART") || ressource.getType().equals("DUNGEON-LOOT")){
                            belohnung = ressource.getName();
                        }
                    }
                }

                int menge = 1;
                if(Shopy.getInstance().isWahrscheinlichkeit(0.45)) menge = 2;
                if(Shopy.getInstance().isWahrscheinlichkeit(0.32)) menge = 3;

                int belohnungsMenge = 3;
                if(Shopy.getInstance().isWahrscheinlichkeit(0.3)) belohnungsMenge = 5;
                if(Shopy.getInstance().isWahrscheinlichkeit(0.17)) belohnungsMenge = 7;


                int finalMenge = menge;
                int finalBelohnungsMenge = belohnungsMenge;
                String finalBelohnung= belohnung;
                CompletableFuture.runAsync(() -> {
                    int shopHandwerksAufgabenItemKey = Shopy.getInstance().getMySQLConntion().queryReturnKey( "INSERT INTO shop_handwerks_aufgaben_item (item, menge, belohnung, belohnung_menge) " +
                            "VALUES ('" + item.getId() +"', '"+ finalMenge +"' , '"+ finalBelohnung  +"' , '"+ finalBelohnungsMenge +"')");

                    Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_handwerks_aufgaben_zuordnung (aufgabe, aufgaben_item) VALUES ('"+ shopHandwerksAufgabenKey[0] +"', '"+shopHandwerksAufgabenItemKey+"')");
                });
                aufgabe.getShopHandwerksAufgabeItems().add(new ShopHandwerksAufgabeItem(item, menge, belohnung, belohnungsMenge));
                hinzugefuegteItems.add(item);
            }
        });

        return aufgabe;
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
    public ArrayList<String> getBschreibung(){
        int belohungsgeld = 0;

        ArrayList<String> beschreibung = new ArrayList<>();
        beschreibung.add("§7Folgende Items musst du für die Aufgabe Liefern:");

        for(ShopHandwerksAufgabeItem aufgabenItem : shopHandwerksAufgabeItems){
            beschreibung.add("§e" + 0 + " §7/ §e" + aufgabenItem.getMenge() +" §7- " + aufgabenItem.getItem().getName());
            belohungsgeld += aufgabenItem.getItem().getItemPreis();
        }

        beschreibung.add("");
        beschreibung.add("§7Folgende Belohnung bekommst du dafür:");
        beschreibung.add("§a" + belohungsgeld + " §7€");
        for(ShopHandwerksAufgabeItem aufgabenItem : shopHandwerksAufgabeItems){
            if(aufgabenItem.getBelohnung().equals("")) continue;
            beschreibung.add("§a" + aufgabenItem.belohnungMenge + "x §7" + aufgabenItem.getBelohnung());
        }
        beschreibung.add("");
        beschreibung.add("§7Verfügbar bis zum §e" + gueltigBis.format(DateTimeFormatter.ofPattern("dd.MM.yyy")) + " §7um§e " + gueltigBis.format(DateTimeFormatter.ofPattern("HH:mm")) + " Uhr");

        return beschreibung;
    }
}
