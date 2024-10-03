package com.gestiondeprojet.db;




import java.sql.Connection;
import java.sql.DriverManager;



public class DBConnection {

    private static DBConnection instance;
    private Connection connection = null;

    private DBConnection() {
        try {
        	
        	String host = "localhost";       
        	String port = "3306";            
        	String db = "gestiondeprojet";        // le nom de votre base de données
        	String user = "root";            // votre nom d'utilisateur MySQL
        	String password = "";      
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=false&serverTimezone=UTC";
        	connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        instance = new DBConnection();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        DBConnection.getInstance().getConnection();
    }
}
