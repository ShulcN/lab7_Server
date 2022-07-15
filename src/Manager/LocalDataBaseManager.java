package Manager;


import java.sql.DriverManager;
import java.sql.SQLException;

public class LocalDataBaseManager extends DatabaseManager{

    public LocalDataBaseManager(String adr, String log, String pas) throws SQLException {

        this.connection = DriverManager.getConnection(adr, log, pas);
        System.out.println("Connected to the database!");

    }
}
