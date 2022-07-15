package Command;

import Given.Product;
import Manager.CollectionManager;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrintDescending extends Command{
    private static final long serialVersionUID = 8L;
    protected String name="команда print_descending";
    public PrintDescending(){

    }    /**Выводит элементы коллекции в консоль в обратном порядкке*/
    @Override
    public void DoCommand(CollectionManager collections){
        message="Элементы коллекции в порядке убывания:"+System.lineSeparator();
        Stream<Product> stream = CollectionManager.getProducts().stream();
        message += stream.sorted(Comparator.reverseOrder()).map(Product::toString).collect(Collectors.joining(System.lineSeparator()));
    }
}
