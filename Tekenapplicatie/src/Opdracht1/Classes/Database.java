package Opdracht1.Classes;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by user on 19-3-2017.
 */
public class Database {

    String connectionUrl = "jdbc:sqlserver://msi;databaseName=KillerApp;integratedSecurity=true";

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    public ArrayList<String> loadItems() {
        try {
            ArrayList<String> strings = new ArrayList<>();
            // Maken van de verbinding
            con = DriverManager.getConnection(connectionUrl);

            // Aanmaken en uitvoeren van SQL statement die data retourneert.
            String SQL = "SELECT * FROM DrawItem";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            // Loopt door de resultaten heen.
            while (rs.next()) {
                strings.add(rs.getString(2));
                System.out.println(rs.getString(2));
            }
            System.out.println("Data is succesvol geladen");

            return strings;
        }

        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void saveItems(String drawing){
        try{
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "insert into DrawItem (Vorm)" +
                    " values (?)";

            try
                //Create mssql preparedstatement
                (PreparedStatement preparedStatement = con.prepareStatement(SQL)){
                preparedStatement.setString(1, drawing);

                preparedStatement.execute();

                System.out.println("Data is succesvol opgeslagen");

                con.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void clearDatabase(){
        try{
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "TRUNCATE TABLE DrawItem";

            stmt = con.createStatement();
            stmt.executeUpdate(SQL);

            System.out.println("Database succesvol geleegd");

            con.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
