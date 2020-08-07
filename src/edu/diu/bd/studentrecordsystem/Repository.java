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
public class Repository {

    private final Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Repository() {
        conn = new SqlConnector().getConnection("student_record_system", "root", "root");
        System.out.println("Connection Established!");
    }

//    public final void createAuthTableIfNotExists() {
//        final String mAuthTable = "CREATE TABLE IF NOT EXISTS auth (uuid BINARY(16), username VARCHAR(50) PRIMARY KEY, password VARCHAR(100));";
//        try {
//            Statement statement = conn.createStatement();
//            statement.executeUpdate(mAuthTable);
//        } catch (SQLException e) {
//            System.out.println("Error to create auth table. " + e.getLocalizedMessage());
//        }
//    }
    public boolean selectStudentRecord(String username, String password) {
        final String select = "SELECT id FROM students WHERE username = '"
                + username
                + "' AND password = '"
                + password
                + "';";

        // SELECT * FROM students WHERE username = 'root' AND password = 'root';
        try {
            preparedStatement = conn.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();

            int i = 0;
            int id;
            while (resultSet.next()) {
                i++;
                id = resultSet.getInt("id");
                System.out.println("ID: " + id);
            }
            System.out.println("Result Set Count: " + i);

            preparedStatement.close();
            resultSet.close();

            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex);
            return false;
        }
    }

    public boolean insertStudentRecord(
            String username,
            String password,
            String f_name,
            String l_name,
            String student_id,
            String email,
            int mobile,
            int course_count,
            String current_semester
    ) {
        final String insert = "INSERT INTO students (f_name,l_name,"
                + "studenet_id,email,mobile,"
                + "course_count,current_semester,"
                + "username,password) VALUES ('"
                + f_name + "','"
                + l_name + "','"
                + student_id + "','"
                + email + "',"
                + mobile + ","
                + course_count + ",'"
                + current_semester + "','"
                + username + "','"
                + password + "');";

        try {
            preparedStatement = conn.prepareStatement(insert);
            preparedStatement.execute();

            preparedStatement.close();

            return true;
        } catch (SQLException ex) {
            System.out.println("Failed to create new account. Error :" + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean deleteStudentRecord(String username) {
        final String delete = "DELETE FROM  auth WHERE username = '"
                + username
                + "';";

        try {
            preparedStatement = conn.prepareStatement(delete);
            preparedStatement.execute();

            preparedStatement.close();
            resultSet.close();

            return true;
        } catch (SQLException ex) {
            System.out.println("Failed to create new account. Error :" + ex.getLocalizedMessage());
            return false;
        }
    }
}
