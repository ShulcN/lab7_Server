package Command;

import Given.Product;
import Manager.CollectionManager;
import java.util.stream.Stream;

public class AverageOfPrice extends Command{
    private static final long serialVersionUID = 11L;
    protected String name="команда Average_of_price";
        /**Вывод средней цены всех товаров в коллекции*/
    public AverageOfPrice(String login){

    }
    @Override
    public void DoCommand(CollectionManager collection){
        Stream<Product> stream = CollectionManager.getProducts().stream();
        try {
            message=" "+stream.mapToDouble(Product::getPrice).average().getAsDouble();
        } catch (Exception e){
            message=" error";
        }
        linkedHashSet=CollectionManager.getProducts();

    }
}
