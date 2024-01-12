package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Ressoure {

    int id;
    String name;
    String beschreibung;
    String type;
    Material icon;
    Double minimaleKosten;
    Double maximaleKosten;
    Double aktuelleKosten;

    private static ArrayList<Ressoure> ressoureList;

    public Ressoure(int id, String name, String beschreibung, String type, String icon, Double minimaleKosten, Double maximaleKosten){
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.type = type;
        this.minimaleKosten = minimaleKosten;
        this.maximaleKosten = maximaleKosten;
        this.aktuelleKosten = maximaleKosten;
        this.icon = Material.getMaterial(icon);

        Bukkit.getScheduler().runTaskTimerAsynchronously(Shopy.getInstance(), this::ressourenRechner, 0L, (20L * 60) * 5); // Alle 5 Minuten
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

    public Double getMinimaleKosten() {
        return minimaleKosten;
    }

    public Double getMaximaleKosten() {
        return maximaleKosten;
    }

    public Double getAktuelleKosten() {
        return aktuelleKosten;
    }

    public static void registerRessouren(){
        CompletableFuture.runAsync(() -> {
            ressoureList = new ArrayList<>();

            try {
                String queryRessourecs = "SELECT * FROM ressource ORDER BY reinfolge";

                ResultSet resultRessourecs = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecs);
                while (resultRessourecs.next()) {
                    Ressoure newRessoure = new Ressoure(resultRessourecs.getInt("id"), resultRessourecs.getString("name"), resultRessourecs.getString("beschreibung"), resultRessourecs.getString("typ"), resultRessourecs.getString("icon"), resultRessourecs.getDouble("minimale_kosten"), resultRessourecs.getDouble("maximale_kosten"));
                    Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§5Ressoursce geladen: " + newRessoure.getName());
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

    private void ressourenRechner(){
        /*
            Ermittler im Regelmäßigen abstand, einen Preis für die ressoure
        */
        Random random = new Random();
        double neueKosten = minimaleKosten + (maximaleKosten - minimaleKosten) * random.nextDouble();

        this.aktuelleKosten = neueKosten;
    }
}
