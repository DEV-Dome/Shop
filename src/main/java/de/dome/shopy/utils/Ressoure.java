package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Ressoure {

    int id;
    String name;
    String beschreibung;
    String type;
    Material icon;

    private static ArrayList<Ressoure> ressoureList;

    public Ressoure(int id, String name, String beschreibung, String type, String icon){
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.type = type;
        this.icon = Material.getMaterial(icon);
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

    public Material getIcon() {
        return icon;
    }

    public String getType() {
        return type;
    }
    public static void registerRessouren(){
        CompletableFuture.runAsync(() -> {
            ressoureList = new ArrayList<>();

            try {
                String queryRessourecs = "SELECT * FROM ressource ORDER BY reinfolge";

                ResultSet resultRessourecs = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecs);
                while (resultRessourecs.next()) {
                    Ressoure newRessoure = new Ressoure(resultRessourecs.getInt("id"), resultRessourecs.getString("name"), resultRessourecs.getString("beschreibung"), resultRessourecs.getString("typ"), resultRessourecs.getString("icon"));
                    ressoureList.add(newRessoure);
                }
            }catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
            }
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
