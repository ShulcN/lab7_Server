package Manager;

import java.io.Console;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server2 {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    public static void main(String[] args) {
        CollectionManager collectionManager;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ваша база данных локальная?(y/n)");
            String s = scanner.next();
            if (s.equals("y")){
            System.out.println("Введите название базы данных:");
            String adr = scanner.next();
            System.out.println("Введите пользователя:");
            String log = scanner.next();
            System.out.println("Введите пароль:");
            String password =  scanner.next();
            LocalDataBaseManager databaseManager = new LocalDataBaseManager("jdbc:postgresql://localhost:5432/"+adr, log, password);
            collectionManager=new CollectionManager(databaseManager);
            } else if (s.equals("n")){
                System.out.println("Введите адрес сервера:");
                String adr = scanner.next();
                System.out.println("Введите порт подключения к серверу:");
                String port = scanner.next();
                System.out.println("Введите имя хоста:");
                String hostName =  scanner.next();
                System.out.println("Введите название базы данных:");
                String databaseName = scanner.next();
                System.out.println("Введите пользователя:");
                String log = scanner.next();
                System.out.println("Введите пароль:");
                String password =  scanner.next();
                DatabaseManager databaseManager = new DatabaseManager(adr, Integer.parseInt(port), hostName, databaseName, log, password);
                collectionManager = new CollectionManager(databaseManager);
            } else return;
            System.out.println("Collection is ready!");
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
