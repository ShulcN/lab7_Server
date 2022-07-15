package Command;

import Given.Person;
import Given.Product;
import Manager.CollectionManager;

import java.io.Serializable;
import java.util.LinkedHashSet;

public class Command implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Product product;
    protected Person owner;
    protected LinkedHashSet<Product> linkedHashSet;
    protected String message="No message";
    protected String name="команда";
    String login;
    public Command(){

    }
    public void DoCommand(CollectionManager ls){

    }
    public String getMessage(){
        return message;
    }
    @Override
    public String toString(){
        return name;
    }

    public String getLogin() {
        return login;
    }

    public Person getOwner() {
        return owner;
    }


    public Product getProduct(){
        return product;
    }

}
