package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.items.ItemKategorie;
import de.dome.shopy.utils.items.Ressoure;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
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
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/shop_template.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/shop_template_zonen.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/shop.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/ressource.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/shop_ressource.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/shop_werte.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/item_kategorie.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/item.sql"));
                    Shopy.getInstance().getMySQLConntion().query(Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/struktur/item_kosten.sql"));
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
                    String[] querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/loader/ressourceLoader.sql").split(";");
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
                    String[] querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/loader/shopTemplateLoader.sql").split(";");
                    for(String query : querys){
                        query = query.trim();
                        if(query.equals("") || query.equals(";")) continue;

                        Shopy.getInstance().getMySQLConntion().query(query);
                    }
                }
            }
            ResultSet itemKategorie = Shopy.getInstance().getMySQLConntion().resultSet("SELECT * FROM item_kategorie");

            if(itemKategorie != null){
                if(!itemKategorie.next()){
                    String[] querys = Shopy.getInstance().getMySQLConntion().readSQLFile("sql/files/loader/ItemLoader.sql").split(";");
                    for(String query : querys){
                        query = query.trim();
                        if(query.equals("") || query.equals(" ")) continue;

                        Shopy.getInstance().getMySQLConntion().query(query);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /* Ressouren und Items Laden */
        Ressoure.registerRessouren();
        ItemKategorie.registerItemKategorie();
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
                /* Items Äxte */
                if(kategorie_axte != -1) {
                    Shopy.getInstance().getMySQLConntion().query("INSERT INTO item (item_kategorie, icon, name, beschreibung, reinfolge) VALUES ('"+ kategorie_axte +"', 'WOODEN_AXE', 'Anfänger Axt I',   'Diese Axt wurde von einem Schreiner Lehrling hergestellt. Und dient nun dem Zweck das Schreinerhandwerk zu erlernen.', 1)" );
                    Shopy.getInstance().getMySQLConntion().query("INSERT INTO item (item_kategorie, icon, name, beschreibung, reinfolge) VALUES ('"+ kategorie_axte +"', 'WOODEN_AXE', 'Anfänger Axt II',  'Diese Axt wurde von einem Schreiner Lehrling hergestellt. Und dient nun dem Zweck das Schreinerhandwerk zu erlernen.', 2)" );
                    Shopy.getInstance().getMySQLConntion().query("INSERT INTO item (item_kategorie, icon, name, beschreibung, reinfolge) VALUES ('"+ kategorie_axte +"', 'WOODEN_AXE', 'Anfänger Axt III', 'Diese Axt wurde von einem Schreiner Lehrling hergestellt. Und dient nun dem Zweck das Schreinerhandwerk zu erlernen.', 3)" );
                }
            }
        });
    }

}
