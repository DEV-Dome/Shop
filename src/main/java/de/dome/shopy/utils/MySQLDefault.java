package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.sql.ResultSet;
import java.sql.SQLException;

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

        try {
            ResultSet ressource = Shopy.getInstance().getMySQLConntion().resultSet("SELECT * FROM ressource");

            if(ressource != null){

                if(ressource.next()){
                    //nichts tun
                }else {
                    loadDefault();
                }

                /* Ressouren Laden */
                Ressoure.registerRessouren();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadDefault(){
        sqlDefaultValue();
    }

    private void sqlStruktur(){
        Shopy.getInstance().getServer().getScheduler().runTask(Shopy.getInstance(), new Runnable() {
            @Override
            public void run() {
                Shopy.getInstance().getMySQLConntion().query("CREATE TABLE IF NOT EXISTS shop_template (" +
                        "    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "    name VARCHAR(150) NOT NULL," +
                        "    template_ordner VARCHAR(500)" +
                        ") ");

                Shopy.getInstance().getMySQLConntion().query("CREATE TABLE IF NOT EXISTS shop_template_zonen (" +
                        "    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "    template INT NOT NULL," +
                        "    locationen_1 TEXT NOT NULL," +
                        "    locationen_2 TEXT NOT NULL," +
                        "    anordnung INT," +
                        "    FOREIGN KEY (template) REFERENCES shop_template(id)" +
                        ") ");

                Shopy.getInstance().getMySQLConntion().query("CREATE TABLE IF NOT EXISTS shop (" +
                        "    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "    template INT NOT NULL," +
                        "    owner VARCHAR(36) NOT NULL," +
                        "    shop_level VARCHAR(16) NOT NULL," +
                        "    shop_ordner VARCHAR(500)," +
                        "    shop_zones INT NOT NULL," +
                        "    FOREIGN KEY (template) REFERENCES shop_template(id)" +
                        ") ");

                Shopy.getInstance().getMySQLConntion().query("CREATE TABLE IF NOT EXISTS ressource (" +
                        "    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "    icon VARCHAR(250) NOT NULL," +
                        "    name VARCHAR(50) NOT NULL," +
                        "    beschreibung TEXT NOT NULL," +
                        "    typ ENUM('STANDART', 'AUFWERTER', 'WAHRUNG') NOT NULL," +
                        "    minimale_kosten DECIMAL(8,2) NOT NULL," +
                        "    maximale_kosten DECIMAL(8,2) NOT NULL," +
                        "    reinfolge INT NOT NULL" +
                        ") ");

                Shopy.getInstance().getMySQLConntion().query("CREATE TABLE IF NOT EXISTS shop_ressource (" +
                        "    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "    shop INT NOT NULL," +
                        "    ressource INT NOT NULL," +
                        "    menge BIGINT," +
                        "    FOREIGN KEY (shop) REFERENCES shop(id)," +
                        "    FOREIGN KEY (ressource) REFERENCES ressource(id)" +
                        ") ");
                Shopy.getInstance().getMySQLConntion().query("CREATE TABLE IF NOT EXISTS shop_werte (" +
                        "    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "    shop INT NOT NULL," +
                        "    wert VARCHAR(512) NOT NULL," +
                        "    value TEXT NOT NULL," +
                        "    FOREIGN KEY (shop) REFERENCES shop(id)" +
                        ") ");
                Shopy.getInstance().getMySQLConntion().query("CREATE TABLE IF NOT EXISTS item_kategorie (" +
                        "    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "    icon VARCHAR(512) NOT NULL," +
                        "    name VARCHAR(512) NOT NULL," +
                        "    beschreibung TEXT NOT NULL," +
                        "    reinfolge INT NOT NULL" +
                        ") ");

                Shopy.getInstance().getMySQLConntion().query("CREATE TABLE IF NOT EXISTS item (" +
                        "    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "    item_kategorie INT NOT NULL," +
                        "    icon VARCHAR(512) NOT NULL," +
                        "    name VARCHAR(512) NOT NULL," +
                        "    beschreibung TEXT NOT NULL," +
                        "    FOREIGN KEY (item_kategorie) REFERENCES item_kategorie(id)" +
                        ") ");
                Shopy.getInstance().getMySQLConntion().query("CREATE TABLE IF NOT EXISTS item_kosten (" +
                        "    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "    item INT NOT NULL," +
                        "    ressource INT NOT NULL," +
                        "    menge INT NOT NULL," +
                        "    FOREIGN KEY (item) REFERENCES item(id)," +
                        "    FOREIGN KEY (ressource) REFERENCES ressource(id)" +
                        ") ");

            }
        });
    }

    private void sqlDefaultValue(){
        Shopy.getInstance().getServer().getScheduler().runTask(Shopy.getInstance(), new Runnable() {
            @Override
            public void run() {
                Shopy.getInstance().getMySQLConntion().query("INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('OAK_WOOD', 'Holz', 'Ein grundlegendes Material', 'STANDART', 1, '5', '15')" );
                Shopy.getInstance().getMySQLConntion().query("INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('STONE', 'Stein', 'Ein grundlegendes Material', 'STANDART', 2, '8', '20')" );
                Shopy.getInstance().getMySQLConntion().query("INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('IRON_INGOT', 'Eisen', 'Ein grundlegendes Material', 'STANDART', 3, '12', '22')" );

                Shopy.getInstance().getMySQLConntion().query("INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('GOLD_INGOT', 'Geld', 'Geld, zum Handeln auf der ganzen Welt', 'WAHRUNG', 100, '0', '0')" );

                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_template (name, template_ordner) VALUES ('Standart', 'vorlage1')" );

                /*Items kategorie*/
                Shopy.getInstance().getMySQLConntion().query("INSERT INTO item_kategorie (icon, name, beschreibung, reinfolge) VALUES ('NETHERITE_AXE', 'Äxte', 'Baue und erforsche die mächtigsten Äxte', 1)" );
                Shopy.getInstance().getMySQLConntion().query("INSERT INTO item_kategorie (icon, name, beschreibung, reinfolge) VALUES ('NETHERITE_SWORD', 'Schwerter', 'Baue und erforsche die mächtigsten Schwerter', 1)" );
                Shopy.getInstance().getMySQLConntion().query("INSERT INTO item_kategorie (icon, name, beschreibung, reinfolge) VALUES ('NETHERITE_SHOVEL', 'Schaufeln', 'Baue und erforsche die mächtigsten Schaufeln', 1)" );
            }
        });
    }

}
