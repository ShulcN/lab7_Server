package Command;

import Given.Product;
import Manager.CollectionManager;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterStartsWithName extends Command{
    private static final long serialVersionUID = 10L;
    protected String name="команда filter_starts_with_name";
    LinkedHashSet<Product> linkedHashSet;
    private String s;
    public FilterStartsWithName(String s, String login){
    }
    /**метод для вывода в консоль товаров, названия которых начинаются с переданной строки*/
    @Override
    public void DoCommand(CollectionManager collection){
        message=("Это все названия продуктов, которые начинаются c "+s+": ");
        //Stream<Product> stream = collection.getProducts().stream();
        Iterator<Product> iterator = collection.getProducts().iterator();
        linkedHashSet=new LinkedHashSet<>();
        while (iterator.hasNext()){
            Product p = iterator.next();
            if(p.getName().startsWith(s)){
                linkedHashSet.add(p);
            }
        }

        //linkedHashSet=(LinkedHashSet<Product>) stream.filter(str->str.getName().startsWith(s)).collect(Collectors.toSet());
        //message+=linkedHashSet;
    }

    public LinkedHashSet<Product> getLinkedHashSet() {
        return linkedHashSet;
    }
}
