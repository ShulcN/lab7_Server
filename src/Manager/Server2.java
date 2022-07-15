package Manager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server2 {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    public static void main(String[] args) {
        CollectionManager collectionManager;
        try {
            LocalDataBaseManager databaseManager = new LocalDataBaseManager( "jdbc:postgresql://localhost:5432/products", "postgres", "310121");
            collectionManager=new CollectionManager(databaseManager);
            System.out.println("Включено");
            ServerSocket server = new ServerSocket(444);
            ExecutorService service = Executors.newFixedThreadPool(10);
            while (true){
                try {
                    Socket client = server.accept();
                    System.out.println("Новый клиент: "+client);
                    ClientHandler clientHandler = new ClientHandler(client, clientHandlers, collectionManager);
                    if(!clientHandlers.contains(clientHandler))clientHandlers.add(clientHandler);
                    service.execute(clientHandler);
                }catch (IOException e){
                    System.out.println("Никого пока нет");
                    e.printStackTrace();
                }

            }

        } catch (IOException | SQLException e){
            System.out.println("Ошибка при создании сервера");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
