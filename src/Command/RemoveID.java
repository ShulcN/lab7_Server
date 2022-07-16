package Command;

import Given.Product;
import Manager.CollectionManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Stream;

public class RemoveID extends Command{
    protected String name="команда Remove_by_id";
    /**Удаление элемента коллекции по его id*/
    private static final long serialVersionUID = 5L;
    private final long id;
    public RemoveID(String id) throws Exception {
        try {
            this.id = Long.parseLong(id);
        } catch (Exception e){
            System.out.println("IncorrectId");
            throw new Exception();
        }
    }
    @Override
    public void DoCommand(CollectionManager collection){
        Stream<Product> stream = CollectionManager.getProducts().stream();
        Product p = stream.filter((s)->(s.getId().equals(id))).findFirst().orElse(null);
        if (p!=null){
            try {
                if (!p.getLogin().equals(login)) throw new NullPointerException();
            } catch (NullPointerException e){
                message="NotYours";
                return;
            }

            try {
                String sql = "DELETE FROM product_2 WHERE id="+id;
                PreparedStatement preparedStatement = collection.getConnection().prepareStatement(sql);
                preparedStatement.execute();
                product=p;
                CollectionManager.getProducts().remove(p);
            } catch (SQLException e){
                e.printStackTrace();
            }
            linkedHashSet=CollectionManager.getProducts();
            message = "SuccessfulRemoved";
            //Sort.sort(CollectionManager.getProducts());
            //Save.save(collection.getProducts());
            return;
        }
        message = "NoSuchId";
    }
}
