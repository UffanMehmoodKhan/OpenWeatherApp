// package test;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;

// public class test {

//     public static void main(String[] args) {
//         // Database connection parameters
//         String url = "jdbc:sqlserver://DESKTOP-2DS0DMR;databaseName=testing;integratedSecurity=true;TrustServerCertificate=True"; // Update with your database URL
   

//         // JDBC variables
//         Connection connection = null;
//         Statement statement = null;
//         ResultSet resultSet = null;

//         try {
//             // Connect to the database
//             connection = DriverManager.getConnection(url);

//             // Create a statement
//             statement = connection.createStatement();

//             // Execute a query to retrieve data from the table
//             String query = "SELECT * FROM registration";
//             resultSet = statement.executeQuery(query);

//             // Process the result set
//             while (resultSet.next()) {
//                 int id = resultSet.getInt("id");
//                 String firstName = resultSet.getString("first");
//                 String lastName = resultSet.getString("last");
//                 int age = resultSet.getInt("age");

//                 // Print the data to the terminal
//                 System.out.println("ID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName + ", Age: " + age);
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         } finally {
//             // Close the result set, statement, and connection
//             try {
//                 if (resultSet != null) resultSet.close();
//                 if (statement != null) statement.close();
//                 if (connection != null) connection.close();
//             } catch (SQLException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
// }
