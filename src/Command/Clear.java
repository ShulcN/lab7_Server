package Command;

import Given.Product;
import Manager.CollectionManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Clear extends Command{
    private static final long serialVersionUID = 9L;
    private boolean yes;
    private LinkedHashSet<Product> linkedHashSet;
    protected String name="команда clear";
    public Clear(){

    }
    /**Очищение коллекции с подтвержединем*/
    @Override
    public void DoCommand(CollectionManager collection){
        if(yes){
            Iterator<Product> stream = collection.getProducts().iterator();
            LinkedHashSet<Product> set= new LinkedHashSet<>();
            while (stream.hasNext()){
                try {
                    Product p = stream.next();
                    if (p.getLogin().equals(login)){
                        set.add(p);
                    }
                } catch (NullPointerException e){
                }
            }
            if (set.isEmpty()) {
                message = "Ваших элементов в коллекции нет";
                //linkedHashSet= collection.getProducts();
                return;
            }
            Stream<String> longStream = set.stream().map(Product::getId).map(String::valueOf);
            String DelId = "";
            DelId += longStream.collect(Collectors.joining(","));
            String sql = "DELETE FROM product WHERE id IN ("+DelId+");";
            try {
                System.out.println(sql);
                PreparedStatement preparedStatement = collection.getConnection().prepareStatement(sql);
                preparedStatement.execute();
                CollectionManager.getProducts().removeAll(set);
                linkedHashSet=set;
                message= "Элементы коллекции принадлежащие вам удалены";
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //Save.save(collection.getProducts());


            return;
        }
        message =  "Очищение коллекции не подтверждено, может попробовать ввести команду снова";
    }

    public LinkedHashSet<Product> getLinkedHashSet() {
        return linkedHashSet;
    }
}
