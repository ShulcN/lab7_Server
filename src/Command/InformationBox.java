package Command;

import Given.Person;
import Given.Product;

import java.util.HashMap;

public class InformationBox extends Command{
    private static final long serialVersionUID = 45L;
 //  private final LinkedHashSet<Product>  Collection;
    //private final ArrayList<Product> products;
    private final Product[] products;
    private String color;
    HashMap<String, String> colors;
//    public InformationBox(String message, LinkedHashSet<Product> products, HashMap<String, String> colors, Product p){
//        this.message=message;
//        this.Collection=products;
//        this.colors=colors;
//        this.product=p;
//    }
        public InformationBox(String message, Product[] products, HashMap<String, String> colors, Product p){
        this.message=message;
        this.products=products;
        this.colors=colors;
        this.product=p;
    }
    public void setOwner(Person person){
            this.owner=person;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
    //    public LinkedHashSet<Product> getProducts() {
//        return Collection;
//    }

    public void setLogin(String s){
            this.login =s;
    }
    public Product[] getProductsArray() {
        return products;
    }

    public HashMap<String, String> getColors() {
        return colors;
    }
}
