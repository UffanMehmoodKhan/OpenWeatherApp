package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class testingDatabase
{
    public static void main(String[] args)
    {
        Connection conn = null;
        try
        {
            // db parameters
           // String connectionString = "jdbc:sqlserver://DESKTOP-2DS0DMR;databaseName=testing;integratedSecurity=true;TrustServerCertificate=True";
            // create a connection to the database//DESKTOP-2DS0DMR
            String connectionString = "jdbc:sqlserver://DESKTOP-2DS0DMR;databaseName=testing;integratedSecurity=true;TrustServerCertificate=True";
            conn = DriverManager.getConnection(connectionString);
            System.out.println("Connection has been established.");
        }
        catch (SQLException e) 
        {
            System.out.println("connection failed");
            System.out.println(e.getMessage());
        }
        


       
    }
}
