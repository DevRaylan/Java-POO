package br.edu.ifsc.fln.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseMySQL implements Database {

    private Connection connection;

    @Override
    public Connection conectar() {
        try {
            final String DRIVER = "com.mysql.cj.jdbc.Driver";//MySQL 8
            final String URL = "jdbc:mysql://localhost:3306/db_lavacao3_70?useTimezone=true&serverTimezone=UTC";//MySQL 8
            final String USER = "root";
            final String PASS = "root1234";
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexão realizada com sucesso!");
            return this.connection;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Falha na conexão com o banco de dados.");
            return null;
        }
    }

    @Override
    public void desconectar(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
