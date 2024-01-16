package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class MySQL {

    private final String host;
    private final String database;
    private final String username;
    private final String password;
    private Connection connection;

    public MySQL() {
        createConfig();

        File configFile = new File(Shopy.getInstance().getDataFolder(), "mysql.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        this.host = config.getString("mysql.host");
        this.database = config.getString("mysql.database");
        this.username = config.getString("mysql.username");
        this.password = config.getString("mysql.password");
        this.connection = null;
    }

    public static void createConfig(){
        File configFile = new File(Shopy.getInstance().getDataFolder(), "mysql.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        if(!config.isString("mysql.host")){
            config.set("mysql.host", "127.0.0.1");
            config.set("mysql.database", "shopy");
            config.set("mysql.username", "root");
            config.set("mysql.password", "123");

            try {
                config.save(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, username, password);
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean disconnect() {
        if (this.connection != null) {
            try {
                this.connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void query(String sql) {
        if (this.connection == null) {
            return;
        }

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    public ResultSet resultSet(String sql) {
        if (this.connection == null) {
            return null;
        }
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String readSQLFile(String filePath) throws IOException {
        // Hole den ClassLoader deines Plugins
        ClassLoader classLoader = getClass().getClassLoader();

        // Öffne einen InputStream zur Ressource
        InputStream inputStream = classLoader.getResourceAsStream(filePath);

        if (inputStream != null) {
            // Lese den Inhalt der Datei aus dem InputStream
            try (Scanner scanner = new Scanner(inputStream, "UTF-8")) {
                StringBuilder stringBuilder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    stringBuilder.append(scanner.nextLine()).append("\n");
                }
                return stringBuilder.toString();
            }
        } else {
            throw new IOException("Die SQL-Datei wurde nicht gefunden: " + filePath);
        }
    }

}
