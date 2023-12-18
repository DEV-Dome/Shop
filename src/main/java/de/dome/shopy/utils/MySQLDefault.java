package de.dome.shopy.utils;

import de.dome.shopy.Shopy;

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

    public void sqlStartUp(){
        sqlStruktur();
        sqlDefaultValue();
    }

    private void sqlStruktur(){
        Shopy.getInstance().getMySQLConntion().query("CREATE TABLE IF NOT EXISTS shop (" +
                "    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "    owner VARCHAR(36) NOT NULL," +
                "    shop_level VARCHAR(16) NOT NULL," +
                "    shop_order VARCHAR(500)" +
                ") ");

        Shopy.getInstance().getMySQLConntion().query("CREATE TABLE IF NOT EXISTS ressource (" +
                "    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "    name VARCHAR(50) NOT NULL," +
                "    beschreibung TEXT NOT NULL," +
                "    typ ENUM('STANDART', 'AUFWERTER') NOT NULL" +
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

    private void sqlDefaultValue(){
        Shopy.getInstance().getMySQLConntion().query("INSERT INTO ressource (name, beschreibung, typ) VALUES ('Holz', 'Ein grundlegendes Material', 'STANDART')" );
        Shopy.getInstance().getMySQLConntion().query("INSERT INTO ressource (name, beschreibung, typ) VALUES ('Stein', 'Ein grundlegendes Material', 'STANDART')" );
        Shopy.getInstance().getMySQLConntion().query("INSERT INTO ressource (name, beschreibung, typ) VALUES ('Eisen', 'Ein grundlegendes Material', 'STANDART')" );
    }

}
