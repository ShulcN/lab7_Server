package Manager;

import Command.*;
import Given.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CollectionManager {
    /**Коллекция*/
    private static LinkedHashSet<Product> products = new LinkedHashSet<>();
    /** файл в из которого происходит считывание коллекции*/
    private static final HashMap<String, String> colorMap = new HashMap<>();
    /** Дата создания коллекции*/
    private final Date creationDate;
    private final Connection connection;
    private final ArrayList<Command> commands = new ArrayList<>();
    private static final LinkedHashSet<Person> owners = new LinkedHashSet<>();
    public Connection getConnection() {
        return connection;
    }

    /** конструктор коллекции запрашивающий путь файла из которого заполняется коллекция */
    public CollectionManager(DatabaseManager databaseManager) throws SQLException {
        creationDate = new Date();
        this.connection= databaseManager.getConnection();
       // connection.prepareStatement("").execute();
        String com1 ="SELECT * FROM users_2";
        PreparedStatement preparedStatement1 = connection.prepareStatement(com1);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        while (resultSet1.next()){
            colorMap.put(resultSet1.getString(1), resultSet1.getString(3));
            Person p  = new Person(resultSet1.getString(1), resultSet1.getString(4), resultSet1.getFloat(6), Country.valueOf(resultSet1.getString(5)), resultSet1.getString(3));
            owners.add(p);

            //System.out.println(resultSet1.getString(1)+" - "+colorMap.get(resultSet1.getString(1)));
        }
        String com ="SELECT * FROM product_2";
        PreparedStatement preparedStatement = connection.prepareStatement(com);
        ResultSet resultSet = preparedStatement.executeQuery();
        try{

        while (resultSet.next()){
                try{
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    String coordinates = resultSet.getString(3).replace("(","");
                    coordinates = coordinates.replace(")", "");
                    String[] coorArr = coordinates.split(",");
                    int x = Integer.parseInt(coorArr[0]);
                    float y = Float.parseFloat(coorArr[1]);
                    double price = resultSet.getDouble(5);
                    java.util.Date creationDate = new java.util.Date(resultSet.getDate(4).getTime());
                    UnitOfMeasure unitOfMeasure = UnitOfMeasure.valueOf(resultSet.getString(6));
                    ProductType type = ProductType.valueOf(resultSet.getString(8));
                    String login = resultSet.getString(7);

                    Product p = new Product(id, name, new Coordinates(x, y), price, creationDate, unitOfMeasure, type,login);
                    products.add(p);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    //e.printStackTrace();
                }}
            sort();
        } catch (Exception e){
            System.out.println("С файлом что-то не так");
            System.exit(1);
        }

//        if(products.isEmpty()){
//            System.out.println("Коллекция пуста, хотите продолжить работу без коллекции?(yes - продолжить)");
//            Scanner scanne = new Scanner(System.in);
//            String input = scanne.nextLine();
//            if(!(input.equals("yes"))){
//                System.exit(1);}
//            System.out.println("Хорошо, можете продолжать работу с пустой коллекцией");
//
//        }
    }

    public static HashMap<String, String> getColorMap() {
        return colorMap;
    }


    /** сортировка элементов коллекции по id*/
    public void sort(){
        //Sort.sort(products);
    }
    /**переопределение метода to String*/
    @Override
    public String toString(){
        return "Тип коллекции: " + products.getClass() +
                " Дата инициализации: " + creationDate +
                " Количество элементов: " +products.size();
    }
    public Date getCreationDate(){
        return creationDate;
    }
    /**Возвращает коллекцию товаров*/
    public static LinkedHashSet<Product> getProducts() {
        return products;
    }

    public static LinkedHashSet<Person> getOwners() {
        return owners;
    }

    public static void setProducts(LinkedHashSet<Product> products) {
        CollectionManager.products = products;
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }
}
