package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.items.ItemKategorie;
import de.dome.shopy.utils.shop.ShopKunden;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class MySQLDefault {

    private static MySQLDefault INSTANCE;

    private MySQLDefault(){
        Shopy.getInstance().getMySQLConntion().connect();
    }

    public static MySQLDefault getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MySQLDefault();
        }

        return INSTANCE;
    }

    public void sqlStartUp() {
        sqlStruktur();
    }

    public void loadDefault(){
        sqlDefaultValue();
    }

    private void sqlStruktur(){
        CompletableFuture.runAsync(() -> {
                try {
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ShopTemplate.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ShopTemplateZonen.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ShopTemplateWerte.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/Shop.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ShopWerte.sql"));

                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/Ressource.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ShopRessource.sql"));

                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ItemKategorie.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ItemKategorieLevel.sql"));

                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ItemSeltenheit.sql"));

                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/Item.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ItemKosten.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ItemWerte.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ItemVerzauberungen.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ItemVerzauberungenSets.sql"));

                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ShopItem.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ShopItemHalter.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ShopItemVorlage.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ShopItemWerte.sql"));

                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/Dungeon.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/DungeonPositionen.sql"));

                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/kundenSkins.sql"));

                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ShopHandwerksAufgaben.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ShopHandwerksAufgabenItem.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ShopHandwerksAufgabenZuordnung.sql"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }).thenRunAsync(() -> {
            loadSQLData();
        });
    }

    private void loadSQLData(){
        try {
            ResultSet ressource = Shopy.getInstance().getMySQLConntion().resultSet("SELECT * FROM ressource");

            if(ressource != null){
                if(!ressource.next()){
                    Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§aDatenbank Einrichtung wird gestartet!");

                    String[] querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ZusatzFremdschluessel.sql").split(";");
                    for(String query : querys){
                        query = query.trim();
                        if(query.equals("") || query.equals(";")) continue;

                        Shopy.getInstance().getMySQLConntion().query(query);
                    }

                    querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/loader/RessourceLoader.sql").split(";");
                    for(String query : querys){
                        query = query.trim();
                        if(query.equals("") || query.equals(";")) continue;

                        Shopy.getInstance().getMySQLConntion().query(query);
                    }
                }
            }
            ResultSet shopTemplate = Shopy.getInstance().getMySQLConntion().resultSet("SELECT * FROM shop_template");

            if(shopTemplate != null){
                if(!shopTemplate.next()){
                    String[] querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/loader/ShopTemplateLoader.sql").split(";");
                    for(String query : querys){
                        query = query.trim();
                        if(query.equals("") || query.equals(";")) continue;

                        Shopy.getInstance().getMySQLConntion().query(query);
                    }
                }
            }

            ResultSet itemStufen = Shopy.getInstance().getMySQLConntion().resultSet("SELECT * FROM item_seltenheit");

            if(itemStufen != null){
                if(!itemStufen.next()){
                    String[] querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/loader/ItemSeltenheitsLoader.sql").split(";");
                    for(String query : querys){
                        query = query.trim();
                        if(query.equals("") || query.equals(" ")) continue;

                        Shopy.getInstance().getMySQLConntion().query(query);
                    }
                }
            }
            ResultSet ItemKategorieLevel = Shopy.getInstance().getMySQLConntion().resultSet("SELECT * FROM item_kategorie_level");

            if(ItemKategorieLevel != null){
                if(!ItemKategorieLevel.next()){
                    String[] querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/loader/ItemKategorieLevelLoader.sql").split(";");
                    for(String query : querys){
                        query = query.trim();
                        if(query.equals("") || query.equals(" ")) continue;

                        Shopy.getInstance().getMySQLConntion().query(query);
                    }
                }
            }

            ResultSet itemKategorie = Shopy.getInstance().getMySQLConntion().resultSet("SELECT * FROM item_kategorie");

            if(itemKategorie != null){
                if(!itemKategorie.next()){
                    /* Kategorien */
                    String[] querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/loader/ItemLoaderKategorien.sql").split(";");
                    for(String query : querys){
                        query = query.trim();
                        if(query.equals("") || query.equals(" ")) continue;

                        Shopy.getInstance().getMySQLConntion().query(query);
                    }

                    /* Verzauberungen */
                    querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/loader/ItemVerzauberungenLoader.sql").split(";");
                    for(String query : querys){
                        query = query.trim();
                        if(query.equals("") || query.equals(" ")) continue;

                        Shopy.getInstance().getMySQLConntion().query(query);
                    }

                    /* Verzauberungen Set */
                    querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/loader/ItemVerzauberungenSetsLoader.sql").split(";");
                    for(String query : querys){
                        query = query.trim();
                        if(query.equals("") || query.equals(" ")) continue;

                        Shopy.getInstance().getMySQLConntion().query(query);
                    }

                    /* Äxte */
                    querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/loader/items/ItemLoaderAxte.sql").split(";");
                    for(String query : querys){
                        query = query.trim();
                        if(query.equals("") || query.equals(" ")) continue;

                        Shopy.getInstance().getMySQLConntion().query(query);
                    }

                    /* Schwerter */
                    querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/loader/items/ItemLoaderSchwerter.sql").split(";");
                    for(String query : querys){
                        query = query.trim();
                        if(query.equals("") || query.equals(" ")) continue;

                        Shopy.getInstance().getMySQLConntion().query(query);
                    }

                    /* Hacke */
                    querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/loader/items/ItemLoaderHacke.sql").split(";");
                    for(String query : querys){
                        query = query.trim();
                        if(query.equals("") || query.equals(" ")) continue;

                        Shopy.getInstance().getMySQLConntion().query(query);
                    }

                    /* Rüstung (Helm) */
                    querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/loader/items/ItemLoaderHelm.sql").split(";");
                    for(String query : querys){
                        query = query.trim();
                        if(query.equals("") || query.equals(" ")) continue;

                        Shopy.getInstance().getMySQLConntion().query(query);
                    }

                    /* Rüstung (Brust) */
                    querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/loader/items/ItemLoaderRuestung.sql").split(";");
                    for(String query : querys){
                        query = query.trim();
                        if(query.equals("") || query.equals(" ")) continue;

                        Shopy.getInstance().getMySQLConntion().query(query);
                    }

                    /* Rüstung (Hose) */
                    querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/loader/items/ItemLoaderHose.sql").split(";");
                    for(String query : querys){
                        query = query.trim();
                        if(query.equals("") || query.equals(" ")) continue;

                        Shopy.getInstance().getMySQLConntion().query(query);
                    }

                    /* Rüstung (Schuhe) */
                    querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/loader/items/ItemLoaderSchuhe.sql").split(";");
                    for(String query : querys){
                        query = query.trim();
                        if(query.equals("") || query.equals(" ")) continue;

                        Shopy.getInstance().getMySQLConntion().query(query);
                    }

                    Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§aDatenbank Einrichtung abgeschlossen!");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /* Ressouren und Items Laden */
        Ressource.registerRessouren();
        ItemKategorie.registerItemKategorie();
        ShopKunden.ladeKunden();
    }

    private void sqlDefaultValue(){
        Shopy.getInstance().getServer().getScheduler().runTaskAsynchronously(Shopy.getInstance(), new Runnable() {
            @Override
            public void run() {
                int kategorie_axte = -1;

                try {
                    ResultSet kategorie = Shopy.getInstance().getMySQLConntion().resultSet("SELECT * FROM item_kategorie");

                    if(kategorie != null){
                        while (kategorie.next()){
                            if(kategorie.getString("name").equals("Äxte")) kategorie_axte = kategorie.getInt("id");
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

}
