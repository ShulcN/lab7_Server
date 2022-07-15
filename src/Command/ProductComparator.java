package Command;

import Given.Product;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product>{

    @Override
    public Comparator<Product> reversed() {
        return Comparator.super.reversed();
    }
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getId() - o2.getId());
    }


}
