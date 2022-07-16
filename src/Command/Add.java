package Command;

import Given.Product;
import Manager.CollectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Add extends Command {
    private static final long serialVersionUID = 2L;
    private String[] fields=null;
    protected String name="add";
    public Add(){
    }
    public Add(String[] fields){
        
    }

    public Add(Product product){
        this.product =product;
    }
    /**Метод добавления элемента в коллекцию*/
    @Override
    public void DoCommand(CollectionManager collection){
        if (product.getId()==0){
//        long id=0;
//        Stream<Product> stream = collection.getProducts().stream();
//        id = stream.map(Product::getId).reduce(Long::max).orElse(-1L)+1;
//        product.setId(id);
            String sql = "SELECT nextval('product_id_2');";
            try {
                PreparedStatement preparedStatement = collection.getConnection().prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                long id=0;
                while (resultSet.next()) {
                    id = resultSet.getLong(1);
                }
                product.setId(id);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        try {
            String sql;
            sql = "INSERT INTO product_2(id, name, coordinates, creation_date, price, unit_of_measure, owner, type) VALUES ("+product.getId()+", ?, (?, ?), ?, ?, ?::unit_of_measure, ?, ?);";
            PreparedStatement preparedStatement = collection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getCoordinates().getX());
            preparedStatement.setFloat(3, product.getCoordinates().getY());
            preparedStatement.setDate(4, new java.sql.Date(new Date().getTime()));
            preparedStatement.setDouble(5, product.getPrice());
            preparedStatement.setString(6, product.getUnitOfMeasure().toString());
            preparedStatement.setString(7, product.getLogin());
            preparedStatement.setString(8, product.getType().toString());
            preparedStatement.execute();

            CollectionManager.getProducts().add(product);
            //Sort.sort(CollectionManager.getProducts());
            //linkedHashSet=collection.getProducts();
            linkedHashSet=CollectionManager.getProducts();
            this.message="SuccessfulAdded";
        } catch (SQLException e){
            e.printStackTrace();

            System.out.println("Error");
        }



        //Save.save(collection.getProducts());
    }

}
