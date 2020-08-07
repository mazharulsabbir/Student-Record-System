/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.diu.bd.studentrecordsystem;
import java.sql.*;

/**
 *
 * @author Sabbir
 */
public class SqlConnector {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/";
 
    public SqlConnector() {
    }
 
    public Connection getConnection(
            final String databaseName,
            final String username,
            final String password
    ) {
        try {
            return DriverManager.getConnection(CONNECTION_URL + databaseName + "?autoReconnect=true&useSSL=false", username, password);
        } catch (SQLException ex) {
            System.out.println("DATABASE_CONNECTION_ERR");
            System.out.println("Message: " + ex.getLocalizedMessage());
        }
 
        return null;
    }
}
