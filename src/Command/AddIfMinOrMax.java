package Command;

import Given.*;
import Manager.CollectionManager;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

public class AddIfMinOrMax extends Command{
    private static final long serialVersionUID = 12L;
    private boolean MOM;
    public AddIfMinOrMax(boolean MOM){

    }
    public AddIfMinOrMax(String[] fields, boolean MOM){
    }
    /** метод добавления элемента в коллекцию, при определенной цене*/
    public void DoCommand(CollectionManager collection){
        double cost;
        Stream<Product> str = CollectionManager.getProducts().stream();
        if (MOM) {
            cost=str.map(Product::getPrice).reduce(Double::min).orElse(-1.0);
            if (product.getPrice()<cost){
                //collection.getProducts().add(product);
                Add add = new Add(product);
                add.DoCommand(collection);
                message="SuccessfulAdded";
                //Sort.sort(CollectionManager.getProducts());
                //linkedHashSet=collection.getProducts();
            } else {
                //linkedHashSet=collection.getProducts();
                message="CostProblem";
            }
        } else {
            cost=str.map(Product::getPrice).reduce(Double::max).orElse(-1.0);
            if (product.getPrice()>cost) {
                Add add = new Add(product);
                add.DoCommand(collection);
                message = "SuccessfulAdded";
                //Sort.sort(CollectionManager.getProducts());
                //linkedHashSet=collection.getProducts();
            } else {
                //linkedHashSet=collection.getProducts();
                message="CostProblem";
            }
        }
        linkedHashSet=CollectionManager.getProducts();

    }

}
