package de.dome.shopy.utils.manger;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.items.*;
import de.dome.shopy.utils.shop.*;
import de.dome.shopy.utils.shop.shophandwerksaufgabe.ShopHandwerksAufgabeItem;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItemData;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Map;

public class ShopInventarManger {

    Shop shop;

    public ShopInventarManger(Shop shop){
        this.shop = shop;
    }


    public void openMarkplatzInventar(){
        RyseInventory.builder().title("§9Ressouren Markplatz").rows(4).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                int solt = 10;
                int zaheler = 0;
                for (Map.Entry<Ressource, Integer> shopRessoure : Shopy.getInstance().getSpielerShops().get(shop.getOwner().getUniqueId()).getRessourenShopManger().getShopRessoure().entrySet()) {
                    Ressource ressource = shopRessoure.getKey();
                    if(!ressource.getType().equalsIgnoreCase("STANDART")) continue;

                    String colorkey = "§e";
                    if(shopRessoure.getValue() <  shop.getRessourcenLager()) colorkey = "§e";
                    if(shopRessoure.getValue() == shop.getRessourcenLager()) colorkey = "§a";
                    if(shopRessoure.getValue() >  shop.getRessourcenLager()) colorkey = "§c";

                    double kosten = Math.round(ressource.getAktuelleKosten());
                    if(shop.getReduzierteMaterialienKosten() > 0) kosten -= kosten * ((double) shop.getReduzierteMaterialienKosten() / 100);

                    ArrayList<String> beschreibung = new ArrayList<>();
                    beschreibung.add("§7Deine Menge: " + colorkey +  shopRessoure.getValue() + " §7/§e " + shop.getRessourcenLager() + " §7" + ressource.getName());
                    beschreibung.add("§7Aktuelle Kosten: §e" + kosten + " §7€");
                    beschreibung.add("");
                    beschreibung.add("§6(Du hast " + shop.getRessourenShopManger().getRessourceValue(Ressource.getRessoureByName("geld")) + " €)");
                    beschreibung.add("");
                    beschreibung.add("§5" + ressource.getBeschreibung());

                    contents.updateOrSet(solt, Shopy.getInstance().createItemWithLore(ressource.getIcon(), "§9" + ressource.getName(), beschreibung));

                    /* Items Anordenen */
                    zaheler++;
                    if (zaheler == 7) {
                        solt += 3;
                        zaheler = 0;
                    }else if(solt == 14 ){
                        solt += 5;
                        zaheler = 0;
                    }else if(solt == 20){
                        solt += 8;
                        zaheler = 0;
                    } else {
                        solt++;
                    }
                }
            }
            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(shop.getOwner());
    }
    public void openWaffenCraftInventar(int seite, ItemKategorie itemKategorie){
        RyseInventory.builder().title("§9Werkbank " + itemKategorie.getName() + " Seite " + seite).rows(3).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                int solt = 11;
                int zaheler = 0;
                int startBei = seite * 5;

                boolean letztesItemGesetzt = false;
                boolean erstesItemgesetzt = false;

                for (ShopItemVorlage shopItemVorlage : shop.getShopItemVorlage()) {
                    Item item = shopItemVorlage.getItem();

                    if (item.getItemKategorie().getId() != itemKategorie.getId()) continue;
                    if (startBei > zaheler) {
                        zaheler++;
                        continue;
                    } else if (startBei != -1) {
                        zaheler = 0;
                        startBei = -1;
                    }
                    if (!erstesItemgesetzt) erstesItemgesetzt = true;

                    if (shopItemVorlage.isfreigeschaltet()) {
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("");

                        beschreibung.add("§7Kosten:");
                        for (ItemRessourecenKosten itr : item.getRessourecsKostenList()) {
                            if(itr == null) continue;
                            beschreibung.add("  §7- §e" + itr.getMenge() + " §7" + itr.getRessoure().getName());
                        }

                        beschreibung.add("");
                        beschreibung.add("§7Herstellungen: §e" + shopItemVorlage.getHergestellt());
                        if(shopItemVorlage.isMeisterung()) beschreibung.add("§7Diese gegenstand wurde §egemeistert!");
                        else beschreibung.add("§7Meisterung: §e" + item.getMeisterMenge());
                        beschreibung.add("");

                        beschreibung.add("§7Erfahrungspunkt:");
                        beschreibung.add("  §7- §e" + item.getShopXp() + " §7Shop Erfahrungspunkte");
                        beschreibung.add("  §7- §e" + item.getKategorieXp() + " §7Item-linien Erfahrungspunkte");
                        beschreibung.add("");

                        String[] beschreibungsArray = item.getBeschreibung().split("\n");
                        for (String itemBeschreibung : beschreibungsArray) {
                            beschreibung.add("§3" + itemBeschreibung.trim());
                        }

                        String itemName = "§9" + item.getName() + " " + item.getItemSeltenheit().getFarbe() + " [" + item.getItemSeltenheit().getName() + "]";


                        ItemStack craftItem = item.getIcon();
                        ItemMeta im = craftItem.getItemMeta();
                        im.setDisplayName(itemName);
                        im.setLore(beschreibung);
                        craftItem.setItemMeta(im);

                        contents.updateOrSet(solt, craftItem);
                    } else {
                        String FreischlatTyp = "§cNicht bekannt";
                        Item freischaltItem = Item.getItemByFreischaltItem(item);

                        if (item.getFreischlatTyp().equalsIgnoreCase("STANDART")) FreischlatTyp = "Aufleveln von Items";
                        if (item.getFreischlatTyp().equalsIgnoreCase("ITEM")) FreischlatTyp = "Benutzen eines Bauplans";

                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("");
                        if (freischaltItem != null) {
                            int hergestellt = shop.getShopItemVorlageByItem(freischaltItem.getId()).getHergestellt();
                            int freischaltMenege = freischaltItem.getFreischaltMenge();
                            String freischaltItemName = freischaltItem.getName();

                            beschreibung.add("§9" + freischaltItemName + ":");
                            beschreibung.add("§e" + hergestellt + " §7/ §e" + freischaltMenege);
                        } else {
                            beschreibung.add("§7wird Freigeschaltet durch:");
                            beschreibung.add("§e" + FreischlatTyp);
                        }
                        beschreibung.add("");
                        beschreibung.add("§7Dieses Item wurde noch nicht freigeschaltet.");
                        beschreibung.add("§7Schalte das Item frei, in dem du mehr Items");
                        beschreibung.add("§7produzierst oder dir einen Bauplan, kaufst.");


                        String itemName = "§eItem noch nicht freigeschaltet";
                        contents.updateOrSet(solt, Shopy.getInstance().createItemWithLore(Material.GRAY_DYE, itemName, beschreibung));
                    }

                    zaheler++;
                    solt += 1;

                    if (zaheler >= 5) {
                        letztesItemGesetzt = true;
                        break;
                    }
                }
                if (!erstesItemgesetzt) {
                    if(seite > 0){
                        openWaffenCraftInventar(seite - 1, itemKategorie);
                        return;
                    }
                }

                /*Menü Regeler */
                if (seite != 0) contents.updateOrSet(9, Shopy.getInstance().createItem(Material.ARROW, "§7Letzte Seite"));
                if (letztesItemGesetzt) contents.updateOrSet(17, Shopy.getInstance().createItem(Material.ARROW, "§7Nächste Seite"));

                contents.updateOrSet(18, Shopy.getInstance().createItem(Material.CRAFTING_TABLE, "§7Zurück zur Übersicht"));
                contents.updateOrSet(26, Shopy.getInstance().createItem(Material.BARRIER, "§7Menü Schlissen"));

                contents.updateOrSet(4, Shopy.getInstance().createItemWithLore(itemKategorie.getIcon(), "§9" + itemKategorie.getName() + " Statistk", itemKategorie.getAnzeigeBeschreibung(shop)));
            }

            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(shop.getOwner());
    }
    public void openWerkbankInventar(){
        RyseInventory.builder().title("§9Werkbank-Kategorie").rows(5).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                int solt = 10;
                int zaheler = 0;
                for(ShopItemKategorie shopItemKategorie : shop.getShopItemKategorie().values()){

                    if(shopItemKategorie.isFreigeschaltet()){
                        ArrayList<String> beschreibung = shopItemKategorie.getItemKategorie().getAnzeigeBeschreibung(shop);

                        beschreibung.add("");
                        String[] beschreibungsArray = shopItemKategorie.getItemKategorie().getBeschreibung().split("\n");
                        for (String itemBeschreibung : beschreibungsArray) {
                            beschreibung.add("§3" +itemBeschreibung.trim());
                        }

                        contents.updateOrSet(solt, Shopy.getInstance().createItemWithLore(shopItemKategorie.getItemKategorie().getIcon(), "§9" + shopItemKategorie.getItemKategorie().getName(), beschreibung));
                    }else {
                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("");
                        beschreibung.add("§7Diese Kategorie wurde noch nicht freigeschaltet.");
                        beschreibung.add("§7Die Kategorie kann freigeschaltet werden, in dem");
                        beschreibung.add("§7sie bei Mona gekauft wird!");

                        String itemName = "§eKategorie noch nicht Freischalten.";
                        contents.updateOrSet(solt, Shopy.getInstance().createItemWithLore(Material.GRAY_DYE, itemName, beschreibung));
                    }

                    zaheler+=2;
                    if (zaheler <= 7) {
                        solt += 3;
                        zaheler = 0;
                    } else {
                        solt++;
                    }
                }
            }
            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(shop.getOwner());
    }

    public void openItemLagerInventar(int seite){
        RyseInventory.builder().title("§9Item Lager Seite " + seite).rows(6).provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {
                        boolean letztesItemGsetzt = false;
                        int soltZahlerMinus = 0;

                        for(int i = (0 + (seite * 44)); i <= 44 * (seite + 1); i++){
                            /* Item Slot unabhängig der Shop Seite. Darf nicht größer 53 sein */
                            int slot = i - (seite * 44) - soltZahlerMinus;

                            if(i <= shop.getItemLagerSize() - 1){
                                if(i <= shop.getShopItems().size() - 1) {
                                    letztesItemGsetzt = true;

                                    /* Item laden */
                                    ShopItem shopItem = shop.getShopItems().get(i);
                                    if(shopItem.getHaltbarkeit() <= 0){
                                        soltZahlerMinus++;
                                        continue;
                                    }

                                    ArrayList<String> beschreibung = shopItem.getVolleBeschreibung();
                                    if(shopItem.isAusgestellt()){
                                        beschreibung.add("");
                                        beschreibung.add("§aDieses Item ist zurzeit ausgestellt.");
                                    }else beschreibung.add("");


                                    /* Beschreibung angepassten, je nachdem ob man in einem Dungeon ist */
                                    if(Shopy.getInstance().getSpielerDungeon().containsKey(shop.getOwner().getUniqueId())) {
                                        if(shopItem.isAusgestellt()){
                                            soltZahlerMinus++;
                                            continue;
                                        }
                                        beschreibung.add("§a- Linksklick zum Item entnehmen");
                                    }else {
                                        beschreibung.add("§c- Rechtsklick zum Löschen");
                                    }

                                    ItemStack item = shopItem.buildBaseItem();
                                    item.setLore(beschreibung);

                                    contents.updateOrSet(slot, item);
                                }
                            }else {
                                if(i > shop.getItemLagerSize() - 1){
                                    letztesItemGsetzt = false;

                                    ArrayList beschreibung = new ArrayList();
                                    beschreibung.add("");
                                    beschreibung.add("§7Du kannst diesen Solt freischalten, in dem du mehr Item Lager in deinem Shop aufbaust.");

                                    contents.updateOrSet(slot, Shopy.getInstance().createItemWithLore(Material.GRAY_DYE, "§7Solt noch nicht freigeschaltet", beschreibung));
                                }
                            }
                        }

                        // Option nicht in einem Dungeon anbieten
                        if(!Shopy.getInstance().getSpielerDungeon().containsKey(shop.getOwner().getUniqueId())) {
                            contents.updateOrSet(45, Shopy.getInstance().createItem(Material.TRAPPED_CHEST, "§7zur Shopübersicht"));
                            contents.updateOrSet(46, Shopy.getInstance().createItem(Material.BARRIER, "§7Menü Schlissen"));
                        }else contents.set(45, Shopy.getInstance().createItem(Material.BARRIER, "§7Menü Schlissen"));


                        if(seite != 0)          contents.updateOrSet(52, Shopy.getInstance().createItem(Material.ARROW, "§7Letzte Seite"));
                        if(letztesItemGsetzt)   contents.updateOrSet(53, Shopy.getInstance().createItem(Material.ARROW, "§7Nächste Seite"));

                    }
                    @Override
                    public void update(Player player, InventoryContents contents) {
                        init(player, contents);
                    }
                }).build(Shopy.getInstance()).open(shop.getOwner());
    }
    public void openRessourenUbersicht(String type){
        RyseInventory.builder().title("§9Shop - Ressourcen Übersicht")
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {
                        int solt = 10;
                        int zaheler = 0;
                        for (Map.Entry<Ressource, Integer> shopRessoure : shop.getRessourenShopManger().getShopRessoure().entrySet()) {
                            Ressource ressource = shopRessoure.getKey();
                            if(!ressource.getType().equalsIgnoreCase(type)) continue;

                            ArrayList<String> beschreibung = new ArrayList<>();
                            beschreibung.add("§7Menge: §e" + shopRessoure.getValue());
                            beschreibung.add("");

                            String[] beschreibungsArray = ressource.getBeschreibung().split("\n");
                            for(String itemBeschreibung : beschreibungsArray){
                                beschreibung.add(itemBeschreibung.trim());
                            }

                            contents.set(solt, Shopy.getInstance().createItemWithLore(ressource.getIcon(), "§9" + ressource.getName(), beschreibung));

                            /* Items Anordenen */
                            zaheler++;
                            if(zaheler == 7){
                                solt += 3;
                                zaheler = 0;
                            }else if(solt == 14 && type.equals("STANDART")){
                                solt += 5;
                                zaheler = 0;
                            }else if(solt == 14 && type.equals("DUNGEON-LOOT")){
                                solt += 5;
                                zaheler = 0;
                            }else if(solt == 20 && type.equals("STANDART")){
                                solt += 8;
                                zaheler = 0;
                            }else if(solt == 14 && type.equals("AUFWERTER")){
                                solt += 5;
                                zaheler = 0;
                            }else {
                                solt++;
                            }
                        }

                        ArrayList<String> beschreibung = new ArrayList<>();
                        beschreibung.add("§7Zum Öffnen der Kategorie hier klicken");

                        /* Menü Items bauen. Sodass diese via Verzauberung anzeigen, welches Menü aktiv ist. */
                        boolean aktivItem = false;
                        if(type.equals("STANDART")) aktivItem = true;
                        contents.set(47, Shopy.getInstance().createItemWithLore(Material.OAK_WOOD, "§e" + "Herstellungsmaterialien", beschreibung, aktivItem, false));

                        aktivItem = false;
                        if(type.equals("DUNGEON-LOOT")) aktivItem = true;
                        contents.set(48, Shopy.getInstance().createItemWithLore(Material.AMETHYST_SHARD, "§e" + "Dungoenmaterialien", beschreibung, aktivItem, false));

                        aktivItem = false;
                        if(type.equals("SPECIAL")) aktivItem = true;
                        contents.set(49, Shopy.getInstance().createItemWithLore(Material.ECHO_SHARD, "§e" + "Specialmatrialen", beschreibung, aktivItem, false));

                        aktivItem = false;
                        if(type.equals("AUFWERTER")) aktivItem = true;
                        contents.set(50, Shopy.getInstance().createItemWithLore(Material.BLUE_DYE, "§e" + "Aufwerter", beschreibung, aktivItem, false));
                    }
                    @Override
                    public void update(Player player, InventoryContents contents) {
                        init(player, contents);
                    }
                }).build(Shopy.getInstance()).open(shop.getOwner());
    }
    public void openHandwerksmeisterPaulUbersicht(){
        RyseInventory.builder().title("§2Handwerksmeister Paul").rows(3).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                if(shop.getShopHandwerksAufgabe().get(0).isErledigt()){
                    contents.updateOrSet(10, Shopy.getInstance().createItemWithLore(Material.SKULL_BANNER_PATTERN, "§9Aufgabe 1 [Erledigt]", shop.getShopHandwerksAufgabe().get(0).getBeschreibung()));
                }else {
                    contents.updateOrSet(10, Shopy.getInstance().createItemWithLore(Material.FLOWER_BANNER_PATTERN, "§9Aufgabe 1", shop.getShopHandwerksAufgabe().get(0).getBeschreibung()));
                }

                if(shop.getErledigteHandwerksAufgaben() >= 100){
                    if(shop.getShopHandwerksAufgabe().get(1).isErledigt()){
                        contents.updateOrSet(13, Shopy.getInstance().createItemWithLore(Material.SKULL_BANNER_PATTERN, "§9Aufgabe 2 [Erledigt]", shop.getShopHandwerksAufgabe().get(1).getBeschreibung()));
                    }else {
                        contents.updateOrSet(13, Shopy.getInstance().createItemWithLore(Material.FLOWER_BANNER_PATTERN, "§9Aufgabe 2", shop.getShopHandwerksAufgabe().get(1).getBeschreibung()));
                    }
                }else {
                    ArrayList<String> beschreibungAufgabe2 = new ArrayList<>();
                    beschreibungAufgabe2.add("");
                    beschreibungAufgabe2.add("§7Erledige Handwerksaufgaben");
                    beschreibungAufgabe2.add("§e" + shop.getErledigteHandwerksAufgaben() + " §7/§e 100");

                    contents.updateOrSet(13, Shopy.getInstance().createItemWithLore(Material.GRAY_DYE, "§eAufgabe noch nicht freigeschaltet", beschreibungAufgabe2));
                }

                if(shop.getErledigteHandwerksAufgaben() >= 250){
                    if(shop.getShopHandwerksAufgabe().get(2).isErledigt()){
                        contents.updateOrSet(16, Shopy.getInstance().createItemWithLore(Material.SKULL_BANNER_PATTERN, "§9Aufgabe 3 [Erledigt]", shop.getShopHandwerksAufgabe().get(2).getBeschreibung()));
                    }else {
                        contents.updateOrSet(16, Shopy.getInstance().createItemWithLore(Material.FLOWER_BANNER_PATTERN, "§9Aufgabe 3", shop.getShopHandwerksAufgabe().get(2).getBeschreibung()));
                    }
                }else {
                    ArrayList<String> beschreibungAufgabe3 = new ArrayList<>();
                    beschreibungAufgabe3.add("");
                    beschreibungAufgabe3.add("§7Erledige Handwerksaufgaben");
                    beschreibungAufgabe3.add("§e" + shop.getErledigteHandwerksAufgaben() + " §7/§e 250");

                    contents.updateOrSet(16, Shopy.getInstance().createItemWithLore(Material.GRAY_DYE, "§eAufgabe noch nicht freigeschaltet", beschreibungAufgabe3));
                }

                contents.updateOrSet(18, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));
            }
            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(shop.getOwner());

    }
    public void openHandwerksmeisterPaulAufgabenAnsicht(int aufgabenNummer, int aufgabenID){
        RyseInventory.builder().title("§2Item Abgabe Aufgabe " + aufgabenID).rows(6).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                int i = 0;
                int aufgabenIndex = aufgabenNummer - 1;
                for(ShopItem shopItem : shop.getShopItems()){
                    if(i >= 5 * 9) break;
                    /* prüfe ist Item Relavant für Aufgabe  */
                    boolean gefunden = false;

                    for(ShopHandwerksAufgabeItem handwerksAufgabeItem : shop.getShopHandwerksAufgabe().get(aufgabenIndex).getShopHandwerksAufgabeItems()){
                        if(handwerksAufgabeItem.getItem().getName().equals(shopItem.getName())){
                            if(handwerksAufgabeItem.getMenge() > handwerksAufgabeItem.getFortschritt()){
                                gefunden = true;
                            }
                        }
                    }
                    if(!gefunden) continue;
                    if(shopItem.isAusgestellt()) continue;

                    /* Item bauen */
                    ArrayList<String> beschreibung = shopItem.getVolleBeschreibung();
                    beschreibung.add("");

                    beschreibung.add("§a- Linksklick zum Item abgeben");

                    ItemStack item = shopItem.buildBaseItem();
                    item.setLore(beschreibung);

                    contents.updateOrSet(i, item);
                    i++;
                }

                contents.updateOrSet(45, Shopy.getInstance().createItem(Material.DIAMOND, "§7Zurück zur Aufgabenübersicht"));
                contents.updateOrSet(53, Shopy.getInstance().createItem(Material.BARRIER, "§7Menü Schlissen"));
                contents.updateOrSet(46, Shopy.getInstance().createItemWithLore(Material.FLOWER_BANNER_PATTERN, "§9Aufgabe " + aufgabenNummer, shop.getShopHandwerksAufgabe().get(aufgabenIndex).getBeschreibung()));
            }
            @Override
            public void update(Player player, InventoryContents contents) {
                for(IntelligentItemData ii : contents.getAllData()) contents.removeFirst();

                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(shop.getOwner());
    }
    public void openRustungsausteller(int itemAusstellerID){
        RyseInventory.builder().title("§9Itemaussteller " + itemAusstellerID).rows(4).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                ShopItemHalter shopItemHalter = shop.shopItemHalterById(itemAusstellerID);

                /*Rüstung */
                if(shopItemHalter.getItem1() != null) contents.updateOrSet(4, shopItemHalter.getItem1().buildBaseItem());
                else contents.updateOrSet(4, Shopy.getInstance().createItem(Material.ARMOR_STAND, "§7Helm wählen"));

                if(shopItemHalter.getItem2() != null) contents.updateOrSet(13, shopItemHalter.getItem2().buildBaseItem());
                else contents.updateOrSet(13, Shopy.getInstance().createItem(Material.ARMOR_STAND, "§7Brustplatte wählen"));

                if(shopItemHalter.getItem3() != null) contents.updateOrSet(22, shopItemHalter.getItem3().buildBaseItem());
                else contents.updateOrSet(22, Shopy.getInstance().createItem(Material.ARMOR_STAND, "§7Hose wählen"));

                if(shopItemHalter.getItem4() != null) contents.updateOrSet(31, shopItemHalter.getItem4().buildBaseItem());
                else contents.updateOrSet(31, Shopy.getInstance().createItem(Material.ARMOR_STAND, "§7Schuhe wählen"));

                /*Hand und Offhand*/
                if(shopItemHalter.getItem5() != null) contents.updateOrSet(12, shopItemHalter.getItem5().buildBaseItem());
                else contents.updateOrSet(12, Shopy.getInstance().createItem(Material.ARMOR_STAND, "§7Haupthand wählen"));

                /* Actions */
                contents.updateOrSet(27, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));
            }


            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(shop.getOwner());
    }

    public void openRustungsAustellerAuswahl(ArrayList<ItemKategorie> itemKategorie, int itemAusstellerID, int seite){
        if(itemKategorie != null){
            String bereichName = "";
            if(itemKategorie.size() == 1) bereichName = itemKategorie.get(0).getName();
            else bereichName = "Handitem";

            RyseInventory.builder().title("§9Itemausstelluswahl " + bereichName + " Seite " + seite + " " + itemAusstellerID).rows(4).provider(new InventoryProvider() {
                @Override
                public void init(Player player, InventoryContents contents) {
                    int i = 0;
                    int start_item = (seite - 1) * 27; // 0
                    int max_item = seite * 27; // 27
                    for (ShopItem shopItem : shop.getShopItems()){
                        if(i < start_item) {
                            i++;
                            continue;
                        }
                        if(i >= max_item) break;

                        if(itemKategorie.contains(shopItem.getItemKategorie()) && !shopItem.isAusgestellt()){
                            contents.updateOrSet(i - start_item, shopItem.buildBaseItem());
                            i++;
                        }
                    }

                    contents.updateOrSet(27, Shopy.getInstance().createItem(Material.ARMOR_STAND, "§7Zurück"));
                    contents.updateOrSet(28, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));
                    contents.updateOrSet(31, Shopy.getInstance().createItem(Material.ARMOR_STAND, "§7Item löschen"));

                    if(seite != 1) contents.updateOrSet(34, Shopy.getInstance().createItem(Material.ARROW, "§7Letzte Seite"));
                    if(!contents.get(26).isEmpty()) contents.updateOrSet(35, Shopy.getInstance().createItem(Material.ARROW, "§7Nächste Seite"));
                }

                @Override
                public void update(Player player, InventoryContents contents) {
                    init(player, contents);
                }
            }).build(Shopy.getInstance()).open(shop.getOwner());
        }else {
            shop.getOwner().sendMessage(Shopy.getInstance().getKonatktSupport());
        }
    }

    public void openAufwerter(ShopItem item){
        /* Größe je nach ansicht festlegen */
        int inventarGroeße = 3;
        if(item != null) inventarGroeße = 4;

        RyseInventory.builder().title("§9Aufwerter").rows(inventarGroeße).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {

                if(item == null){
                    contents.updateOrSet(12, Shopy.getInstance().createItem(Material.ARMOR_STAND, "§7Item auswählen"));
                    contents.updateOrSet(14, Shopy.getInstance().createItem(Material.GLASS_BOTTLE, "§7Aufwertungsmaterialien"));

                    contents.updateOrSet(18, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));
                }else {
                    contents.updateOrSet(12, item.buildBaseItem());

                    if(item.getItemSeltenheit().getAufwerter() != null){
                        Ressource aufwerter = item.getItemSeltenheit().getAufwerter();
                        Material icon = item.getItemSeltenheit().getAufwerter().getIcon();
                        int aufwerterMenge = Shopy.getInstance().getSpielerShops().get(player.getUniqueId()).getShopRessourenManger().getRessourceValue(aufwerter);

                        ArrayList<String> beschreibungAufwertungsMatrial = new ArrayList<>();
                        beschreibungAufwertungsMatrial.add("§7Du brauchst folgende Matrialen zum aufwerten:");
                        beschreibungAufwertungsMatrial.add("§e"+ aufwerterMenge +" §7/ §e" + item.getItemSeltenheit().getAufwerterMenge() + " §7" + aufwerter.getName());

                        contents.updateOrSet(14, Shopy.getInstance().createItemWithLore(icon, "§9Aufwertungsmaterialien", beschreibungAufwertungsMatrial));


                    }else {
                        player.closeInventory();
                        player.sendMessage(Shopy.getInstance().getKonatktSupport());
                    }

                    contents.updateOrSet(22, Shopy.getInstance().createItem(Material.SMITHING_TABLE, "§9Aufwerten"));
                    contents.updateOrSet(27, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));
                }
            }

            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(shop.getOwner());
    }

    public void openAuswerterItemAuswahl(int seite){
            RyseInventory.builder().title("§9Aufwerterauswahl Seite " + seite).rows(4).provider(new InventoryProvider() {
                @Override
                public void init(Player player, InventoryContents contents) {
                    int i = 0;
                    int start_item = (seite - 1) * 27; // 0
                    int max_item = seite * 27; // 27
                    for (ShopItem shopItem : shop.getShopItems()){
                        if(i < start_item) {
                            i++;
                            continue;
                        }
                        if(shopItem.isAusgestellt()) continue;
                        if(shopItem.getItemSeltenheit().getId() >= 5) continue;
                        if(i >= max_item) break;

                        contents.updateOrSet(i - start_item, shopItem.buildBaseItem());
                        i++;
                    }

                    contents.updateOrSet(27, Shopy.getInstance().createItem(Material.ARMOR_STAND, "§7Zurück"));
                    contents.updateOrSet(28, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));

                    if(seite != 1) contents.updateOrSet(34, Shopy.getInstance().createItem(Material.ARROW, "§7Letzte Seite"));
                    if(!contents.get(26).isEmpty()) contents.updateOrSet(35, Shopy.getInstance().createItem(Material.ARROW, "§7Nächste Seite"));
                }

                @Override
                public void update(Player player, InventoryContents contents) {
                    init(player, contents);
                }
            }).build(Shopy.getInstance()).open(shop.getOwner());
    }

    public void openVerzaubere(ShopItem item){
        /* Größe je nach ansicht festlegen */
        int inventarGroeße = 3;
        if(item != null) inventarGroeße = 4;

        RyseInventory.builder().title("§9Verzaubere").rows(inventarGroeße).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                ArrayList<String> beschreibung = new ArrayList<>();
                beschreibung.add("§7Eine verzauberung kostetet");
                beschreibung.add("§7immer §e1000 €§7. Es ist zufällig,");
                beschreibung.add("§7welche verzauberung du bekommst.");

                if(item == null){
                    contents.updateOrSet(12, Shopy.getInstance().createItem(Material.ARMOR_STAND, "§7Item auswählen"));
                    contents.updateOrSet(14, Shopy.getInstance().createItemWithLore(Material.GOLD_INGOT, "§9Verzauberungsmaterialien", beschreibung));

                    contents.updateOrSet(18, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));
                }else {
                    contents.updateOrSet(12, item.buildBaseItem());
                    contents.updateOrSet(14, Shopy.getInstance().createItemWithLore(Material.GOLD_INGOT, "§9Verzauberungsmaterialien", beschreibung));

                    contents.updateOrSet(22, Shopy.getInstance().createItem(Material.AMETHYST_BLOCK, "§9Verzaubern"));
                    contents.updateOrSet(27, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));
                }
            }

            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(shop.getOwner());
    }
    public void openVerzaubereItemAuswahl(int seite){
        RyseInventory.builder().title("§9Verzaubereauswahl Seite " + seite).rows(4).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                int i = 0;
                int start_item = (seite - 1) * 27;
                int max_item = seite * 27;
                for (ShopItem shopItem : shop.getShopItems()){
                    if(i < start_item) {
                        i++;
                        continue;
                    }
                    if(shopItem.isAusgestellt()) continue;
                    if(shopItem.getItemVerzauberungenSet() != null) continue;
                    if(shopItem.getItemSeltenheit().getId() < 2) continue;
                    if(i >= max_item) break;

                    contents.updateOrSet(i - start_item, shopItem.buildBaseItem());
                    i++;
                }

                contents.updateOrSet(27, Shopy.getInstance().createItem(Material.ARMOR_STAND, "§7Zurück"));
                contents.updateOrSet(28, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));

                if(seite != 1) contents.updateOrSet(34, Shopy.getInstance().createItem(Material.ARROW, "§7Letzte Seite"));
                if(!contents.get(26).isEmpty()) contents.updateOrSet(35, Shopy.getInstance().createItem(Material.ARROW, "§7Nächste Seite"));
            }

            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(shop.getOwner());
    }

    public void openReparaturTisch(ShopItem item){
        /* Größe je nach ansicht festlegen */
        int inventarGroeße = 3;
        if(item != null) inventarGroeße = 4;

        RyseInventory.builder().title("§9Reparatur Tisch").rows(inventarGroeße).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                if(item == null){
                    ArrayList<String> beschreibung = new ArrayList<>();
                    beschreibung.add("§7Eine reparatur kostetet");
                    beschreibung.add("§7§e50 €§7 und §e1 Repatur Material§7.");

                    contents.updateOrSet(12, Shopy.getInstance().createItem(Material.ARMOR_STAND, "§7Item auswählen"));
                    contents.updateOrSet(14, Shopy.getInstance().createItemWithLore(Material.GOLD_INGOT, "§9Repaturmaterialien", beschreibung));

                    contents.updateOrSet(18, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));
                }else {
                    Ressource repaturMaterial = item.getHauptMaterial();

                    ArrayList<String> beschreibung = new ArrayList<>();
                    beschreibung.add("§7Eine reparatur kostetet");
                    beschreibung.add("§7§e50 €§7 und §e1 "+ repaturMaterial.getName() +"§7.");

                    contents.updateOrSet(12, item.buildBaseItem());
                    contents.updateOrSet(14, Shopy.getInstance().createItemWithLore(Material.GOLD_INGOT, "§9Repaturmaterialien", beschreibung));

                    contents.updateOrSet(22, Shopy.getInstance().createItem(Material.ANVIL, "§9Reparieren"));
                    contents.updateOrSet(27, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));
                }
            }

            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(shop.getOwner());
    }

    public void openReparaturTischAuswahl(int seite){
        RyseInventory.builder().title("§9Reparatur Tisch Seite " + seite).rows(4).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                int i = 0;
                int start_item = (seite - 1) * 27;
                int max_item = seite * 27;
                for (ShopItem shopItem : shop.getShopItems()){
                    if(i < start_item) {
                        i++;
                        continue;
                    }
                    if(i >= max_item) break;

                    contents.updateOrSet(i - start_item, shopItem.buildBaseItem());
                    i++;
                }

                contents.updateOrSet(27, Shopy.getInstance().createItem(Material.ARMOR_STAND, "§7Zurück"));
                contents.updateOrSet(28, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));

                if(seite != 1) contents.updateOrSet(34, Shopy.getInstance().createItem(Material.ARROW, "§7Letzte Seite"));
                if(!contents.get(26).isEmpty()) contents.updateOrSet(35, Shopy.getInstance().createItem(Material.ARROW, "§7Nächste Seite"));
            }

            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(shop.getOwner());
    }

    public void openSetAufwerter(ShopItem item, ItemVerzauberungSet itemVerzauberungSet){
        /* Größe je nach ansicht festlegen */
        int inventarGroeße = 3;
        if(item != null) inventarGroeße = 4;

        RyseInventory.builder().title("§9Set-Aufwerter").rows(inventarGroeße).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                ArrayList<String> beschreibung = new ArrayList<>();
                beschreibung.add("§7Eine Set Aufwertung kostet");
                beschreibung.add("§e1000 €§7 und §e1 Set-Material§7.");

                if(item == null){
                    contents.updateOrSet(11, Shopy.getInstance().createItem(Material.ARMOR_STAND, "§7Item auswählen"));

                    if(itemVerzauberungSet != null){
                        contents.updateOrSet(14, itemVerzauberungSet.buildIconBaseItem(shop));
                    }else{
                        contents.updateOrSet(14, Shopy.getInstance().createItem(Material.ALLAY_SPAWN_EGG, "§7Set auswählen"));
                    }

                    contents.updateOrSet(15, Shopy.getInstance().createItemWithLore(Material.GOLD_INGOT, "§9Aufwertungsmaterialien", beschreibung));

                    contents.updateOrSet(18, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));
                }else {
                    contents.updateOrSet(11, item.buildBaseItem());

                    if(itemVerzauberungSet != null){
                        contents.updateOrSet(14, itemVerzauberungSet.buildIconBaseItem(shop));
                        contents.updateOrSet(22, Shopy.getInstance().createItem(Material.CALIBRATED_SCULK_SENSOR, "§9Aufwerten"));
                    }else{
                        contents.updateOrSet(14, Shopy.getInstance().createItem(Material.ALLAY_SPAWN_EGG, "§7Set auswählen"));
                    }

                    contents.updateOrSet(15, Shopy.getInstance().createItemWithLore(Material.GOLD_INGOT, "§9Aufwertungsmaterialien", beschreibung));

                    contents.updateOrSet(27, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));
                }
            }

            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(shop.getOwner());
    }

    public void openSetAufwerterItemAuswahl(int seite, ItemVerzauberungSet itemVerzauberungSet){
        RyseInventory.builder().title("§9Set-Aufwerter Itemauswahl Seite " + seite).rows(4).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                int i = 0;
                int start_item = (seite - 1) * 27;
                int max_item = seite * 27;
                for (ShopItem shopItem : shop.getShopItems()){
                    if(i < start_item) {
                        i++;
                        continue;
                    }
                    if(i >= max_item) break;
                    if(shopItem.getItemSeltenheit().getId() != 4) continue;
                    if(shopItem.getItemKategorie().getId() == 1 || shopItem.getItemKategorie().getId() == 2 || shopItem.getItemKategorie().getId() == 3) continue;
                    if(shopItem.getItemVerzauberung() != null) continue;

                    contents.updateOrSet(i - start_item, shopItem.buildBaseItem());
                    i++;
                }

                contents.updateOrSet(27, Shopy.getInstance().createItem(Material.ARMOR_STAND, "§7Zurück"));
                contents.updateOrSet(28, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));

                if(itemVerzauberungSet != null) contents.updateOrSet(31,  itemVerzauberungSet.buildIconBaseItem(shop));


                if(seite != 1) contents.updateOrSet(34, Shopy.getInstance().createItem(Material.ARROW, "§7Letzte Seite"));
                if(!contents.get(26).isEmpty()) contents.updateOrSet(35, Shopy.getInstance().createItem(Material.ARROW, "§7Nächste Seite"));
            }

            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(shop.getOwner());
    }
    public void openSetAufwerterSetAuswahl(int seite, ShopItem shopItem){
        RyseInventory.builder().title("§9Set-Aufwerter Setauswahl Seite " + seite).rows(4).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                int i = 0;
                int start_item = (seite - 1) * 27;
                int max_item = seite * 27;
                for (ItemVerzauberungSet itemVerzauberungSet : ItemVerzauberungSet.getItemItemVerzauberungSetList()){
                    if(i < start_item) {
                        i++;
                        continue;
                    }
                    if(i >= max_item) break;

                    ArrayList<String> beschreibung = new ArrayList<>();
                    beschreibung.add("§7Um das Item aufzuwerten, benötigst ");
                    beschreibung.add("§7du den endsprechenden Aufwerter:");
                    beschreibung.add("");
                    beschreibung.add("§e" + shop.getShopRessourenManger().getRessourceValue(itemVerzauberungSet.getAufwerter()) + " §7/ §e1 §7Aufwerter");

                    contents.updateOrSet(i - start_item, itemVerzauberungSet.buildIconBaseItem(shop));
                    i++;
                }

                contents.updateOrSet(27, Shopy.getInstance().createItem(Material.ARMOR_STAND, "§7Zurück"));
                contents.updateOrSet(28, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));

                if(shopItem != null) contents.updateOrSet(31, shopItem.buildBaseItem());

                if(seite != 1) contents.updateOrSet(34, Shopy.getInstance().createItem(Material.ARROW, "§7Letzte Seite"));
                if(!contents.get(26).isEmpty()) contents.updateOrSet(35, Shopy.getInstance().createItem(Material.ARROW, "§7Nächste Seite"));
            }

            @Override
            public void update(Player player, InventoryContents contents) {
                init(player, contents);
            }
        }).build(Shopy.getInstance()).open(shop.getOwner());
    }
    public void openMuelleimer(){
        RyseInventory.builder().title("§9Mülleimer").rows(3).provider(new InventoryProvider() {
            @Override
            public void init(Player player, InventoryContents contents) {
                ArrayList<String> beschreibung = new ArrayList<>();
                beschreibung.add("§7Klicke alle Items an,");
                beschreibung.add("§7welche gelöscht werden sollen!");

                contents.updateOrSet(13, Shopy.getInstance().createItemWithLore(Material.LAVA_BUCKET, "§9Info", beschreibung));
                contents.updateOrSet(18, Shopy.getInstance().createItem(Material.BARRIER, "§7Schlissen"));
            }
        }).build(Shopy.getInstance()).open(shop.getOwner());
    }
}
