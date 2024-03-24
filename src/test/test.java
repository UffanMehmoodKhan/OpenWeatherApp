//i have established a connection to the database
// now i want to test it by some random thingz
// i will create a table and insert some values

// Path: src/database/testingDatabase.java
// Compare this snippet from src/test/test.java:

package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class test
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
        
      
        //add random data in table registration coloumns are id,first(for first name),last(for last name),age
        //insert data, table is already made
        //do multple insertion
        try
        {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO registration (id, first, last, age) VALUES (1, 'Zia', 'Khan', 25)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO registration (id, first, last, age) VALUES (2, 'Ali', 'Khan', 30)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO registration (id, first, last, age) VALUES (3, 'Ahmed', 'Khan', 35)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO registration (id, first, last, age) VALUES (4, 'Zia', 'Ali', 40)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO registration (id, first, last, age) VALUES (5, 'Ali', 'Ali', 45)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO registration (id, first, last, age) VALUES (6, 'Ahmed', 'Ali', 50)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO registration (id, first, last, age) VALUES (7, 'Zia', 'Ahmed', 55)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO registration (id, first, last, age) VALUES (8, 'Ali', 'Ahmed', 60)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO registration (id, first, last, age) VALUES (9, 'Ahmed', 'Ahmed', 65)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO registration (id, first, last, age) VALUES (10, 'Zia', 'Zia', 70)";
            stmt.executeUpdate(sql);
            System.out.println("Data has been inserted.");
        }
        catch (SQLException e) 
        {
            System.out.println("Data insertion failed");
            System.out.println(e.getMessage());
        }
            
    }
}