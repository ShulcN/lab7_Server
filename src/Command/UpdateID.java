package Command;

import Given.Product;
import Manager.CollectionManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Stream;

public class UpdateID extends Command{
    private static final long serialVersionUID = 4L;
    protected String name="команда UpdateId";
    /**Замена элемента коллекции по его id*/

    public UpdateID(String Id,Product pr, String login){
        name="update";
        super.login=login;
        long id;
        try {
            id = Long.parseLong(Id);
            if (id<1)throw new Exception();
        } catch (Exception e ){
            System.out.println("введенное Id некорректно");
            return;
        }
        product=pr;
        product.setId(id);

    }
    @Override
    public void DoCommand(CollectionManager collection){
        boolean exist=false;
        Long id = product.getId();
        Stream<Product> stream = CollectionManager.getProducts().stream();
        Product p = stream.filter((s)->s.getId().equals(id)).findFirst().orElse(null);
        if(p==null){
            message= ("NoSuchId");
            return;
        }
        try {
            if (!p.getLogin().equals(login)){
                throw new NullPointerException();
            }
        } catch (NullPointerException e){
            //linkedHashSet = collection.getProducts();
            //System.out.println(linkedHashSet);
            message = "NotYours";
            return;
        }
        CollectionManager.getProducts().remove(p);
        String sql = "DELETE FROM product_2 WHERE id="+id;
        try {
            PreparedStatement preparedStatement = collection.getConnection().prepareStatement(sql);
            preparedStatement.execute();
            Add add = new Add(product);
            add.DoCommand(collection);
            //collection.getProducts().add(product);
            //linkedHashSet=collection.getProducts();
            message="SuccessfulUpdate";
        } catch (SQLException e){
            e.printStackTrace();
        }

        linkedHashSet=CollectionManager.getProducts();
        //Sort.sort(CollectionManager.getProducts());
        //Save.save(collection.getProducts());
    }

}
