package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Ressoure {

    int id;
    String name;
    String beschreibung;
    String type;

    private static ArrayList<Ressoure> ressoureList;

    public Ressoure(int id, String name, String beschreibung, String type){
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public String getType() {
        return type;
    }
    public static void registerRessouren(){
        CompletableFuture.runAsync(() -> {
            try {
                String queryRessourecs = "SELECT * FROM ressource";

                ResultSet resultRessourecs = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecs);
                while (resultRessourecs.next()) {
                    Ressoure newRessoure = new Ressoure(resultRessourecs.getInt("id"), resultRessourecs.getString("name"), resultRessourecs.getString("beschreibung"), resultRessourecs.getString("typ"));
                    ressoureList.add(newRessoure);
                    Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "add");

                }
            }catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
            }

            Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + ressoureList.size());
        });
    }

    public static Ressoure getRessoureByName(String name){
        Ressoure ressoure = null;

        for (Ressoure res: ressoureList) {
            if(name.equalsIgnoreCase(res.getName())){
                ressoure = res;
            }
        }

        return ressoure;
    }
}
