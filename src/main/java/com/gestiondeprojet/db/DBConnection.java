package com.gestiondeprojet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection = null;

    private DBConnection() {
        try {
            String host = "localhost";
            String port = "3306";
            String db = "GestionDeProjet";
            String user = "myroot";
            String password = "123456789";
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/GestionDeProjet?useSSL=false&serverTimezone=UTC";
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()  ) {
            instance = new DBConnection();
        }
        instance = new DBConnection();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
    	
        try {
            Connection connection = DBConnection.getInstance().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}