import Manager.CollectionManager;

import java.net.URL;
import java.sql.*;
import java.io.*;

public class Main {
    public static void main(String args[]) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/products", "Postgres", "postgres")) {
            //CollectionManager collectionManager = new CollectionManager();
            if (conn != null) {
                System.out.println("Connected to the database!");
                //String com = "INSERT INTO products (name, coordinates, creation_date, price, unit_of_measure, owner) VALUES('wasd', ('12', '12'),'2000-01-23' ,'123', 'METERS', ('Vasia', '120', 'RED', 'RED', 'CHINA', ('25', '25', '25')));";
                String com = "SELECT * FROM products";
                PreparedStatement preparedStatement = conn.prepareStatement(com);

                //preparedStatement.execute();
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){

                    System.out.println(resultSet.getString(7));
                }
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
