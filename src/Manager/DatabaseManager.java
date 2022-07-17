package Manager;

import com.jcraft.jsch.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    protected Connection connection;
    public DatabaseManager(String ServAdr, int tunnelPort, String tunnelHost, String dataBaseName, String log, String pas) throws SQLException, JSchException {
        JSch jSch = new JSch();
        try {
            InetAddress.getByName(ServAdr);
        } catch (UnknownHostException e){
            e.printStackTrace();
        }
        Session session = jSch.getSession(log,ServAdr, tunnelPort);
        session.setPassword(pas);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        session.setPortForwardingL(tunnelPort, tunnelHost, 5432);
        //int asignedPort = session.setPortForwardingL(7777,"pg",5432);
       //connection = DriverManager.getConnection(adr, log, pas);
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:"+tunnelPort+"/"+dataBaseName, log, pas);
        System.out.println("Connected to the database!");
    }

    public DatabaseManager() {
    }

    public Connection getConnection() {
        return connection;
    }
}
