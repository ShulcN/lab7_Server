package Command;

import Given.Country;
import Given.Person;
import Given.Product;
import Manager.CollectionManager;
import com.sun.xml.internal.ws.developer.Serialization;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalInfoUpdate extends Command{
    private static final long serialVersionUID = 89L;
    private String nameUser;
    private Country nationality;
    private Float weight;
    public PersonalInfoUpdate(String login, String nameUser, Country nationality, Float weight){
        this.login=login;
        this.nameUser=nameUser;
        this.nationality=nationality;
        this.weight=weight;
    }
    @Override
    public void DoCommand(CollectionManager collectionManager){
        message="n";
        Person toDel=null;
        for (Person p:CollectionManager.getOwners()){
            if (p.getLogin().equals(login)) {
                toDel=p;
                owner= new Person(login, nameUser, weight, nationality, p.getColor());
                System.out.println("это изменение персональной инфы "+owner.getWeight());
            }
        }
        CollectionManager.getOwners().remove(toDel);
        CollectionManager.getOwners().add(owner);
        String select;
        PreparedStatement preparedStatement;
        try {
            String sql = "SELECT * FROM users_2 WHERE login='"+login+"'";
            PreparedStatement selectFromTable =collectionManager.getConnection().prepareStatement(sql);
            ResultSet selectFromTableResult = selectFromTable.executeQuery();
            String salt=null;
            String password=null;
            String color=null;
            while (selectFromTableResult.next()){
                password=selectFromTableResult.getString("password");
                salt=selectFromTableResult.getString("salt");
                color=selectFromTableResult.getString("color");
            }
            sql="DELETE FROM users_2 WHERE login='"+login+"';";
            PreparedStatement delete =collectionManager.getConnection().prepareStatement(sql);
            //delete.setString(1, lg);
            delete.execute();
            if (weight==null) {
                select = "INSERT INTO users_2(login, password, color, name, nationality, salt) VALUES ('" + login + "','" + password + "','" + color + "','" + nameUser + "','" + nationality + "','" + salt + "');";
            } else {
                select = "INSERT INTO users_2(login, password, color, name, nationality, weight, salt) VALUES ('" + login + "','" + password + "','" + color + "','" + nameUser + "','" + nationality + "','" + weight + "','" + salt + "');";
            }
            preparedStatement = collectionManager.getConnection().prepareStatement(select);
            preparedStatement.execute();
            message="updateOfPersonalInfo";
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
