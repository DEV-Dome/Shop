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
                        "    typ ENUM('STANDART', 'AUFWERTER') NOT NULL," +
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
            }
        });
    }

    private void sqlDefaultValue(){
        Shopy.getInstance().getServer().getScheduler().runTask(Shopy.getInstance(), new Runnable() {
            @Override
            public void run() {
                Shopy.getInstance().getMySQLConntion().query("INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge) VALUES ('OAK_WOOD', 'Holz', 'Ein grundlegendes Material', 'STANDART', 1)" );
                Shopy.getInstance().getMySQLConntion().query("INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge) VALUES ('STONE', 'Stein', 'Ein grundlegendes Material', 'STANDART', 2)" );
                Shopy.getInstance().getMySQLConntion().query("INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge) VALUES ('IRON_INGOT', 'Eisen', 'Ein grundlegendes Material', 'STANDART', 3)" );

                Shopy.getInstance().getMySQLConntion().query("INSERT INTO shop_template (name, template_ordner) VALUES ('Standart', 'vorlage1')" );
            }
        });
    }

}
