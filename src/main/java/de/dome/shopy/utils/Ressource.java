package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Ressource {

    int id;
    String name;
    String beschreibung;
    String type;
    Material icon;
    Double minimaleKosten;
    Double maximaleKosten;
    Double aktuelleKosten;

    private static ArrayList<Ressource> ressourceList;

    public Ressource(int id, String name, String beschreibung, String type, String icon, Double minimaleKosten, Double maximaleKosten){
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
            ressourceList = new ArrayList<>();

            try {
                String queryRessourecs = "SELECT * FROM ressource ORDER BY reinfolge";

                ResultSet resultRessourecs = Shopy.getInstance().getMySQLConntion().resultSet(queryRessourecs);
                while (resultRessourecs.next()) {
                    Ressource newRessource = new Ressource(resultRessourecs.getInt("id"), resultRessourecs.getString("name"), resultRessourecs.getString("beschreibung"), resultRessourecs.getString("typ"), resultRessourecs.getString("icon"), resultRessourecs.getDouble("minimale_kosten"), resultRessourecs.getDouble("maximale_kosten"));
                    ressourceList.add(newRessource);
                }
            }catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(Shopy.getInstance().getPrefix() + "§4" + e.getMessage());
            }
        });
    }

    public static Ressource getRessoureByName(String name){
        Ressource ressource = null;

        for (Ressource res: ressourceList) {
            if(name.equalsIgnoreCase(res.getName())){
                ressource = res;
            }
        }

        return ressource;
    }
    public static Ressource getRessoureByID(int id){
        Ressource ressource = null;

        for (Ressource res: ressourceList) {
            if(id == res.getId()){
                ressource = res;
            }
        }

        return ressource;
    }

    private void ressourenRechner(){
        /*
            Ermittler im Regelmäßigen abstand, einen Preis für die ressoure
        */
        Random random = new Random();
        double neueKosten = minimaleKosten + (maximaleKosten - minimaleKosten) * random.nextDouble();

        this.aktuelleKosten = neueKosten;
    }

    public static ArrayList<Ressource> getRessourceList() {
        return ressourceList;
    }
}
