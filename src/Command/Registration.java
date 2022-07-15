package Command;

import Given.Country;
import Given.Person;
import Manager.CollectionManager;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Registration extends Command{
    private static final long serialVersionUID = 18L;
    private String password;
    private String color;
    public Registration(String login, String password, String color, String nameO, float weight, Country nationality){
    }
    @Override
    synchronized public void DoCommand(CollectionManager collectionManager){
        name="reg";
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_-+=/<>{}[]~";
        StringBuilder salt = new StringBuilder(7);
        for (int i = 0; i < 7; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            salt.append(AlphaNumericString.charAt(index));
        }
        password+=salt;
        //password = password+"+!Q_81";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger pasBytes = new BigInteger(1, messageDigest);
            this.password = pasBytes.toString(16);
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        String select = "SELECT * FROM users_2;";
        boolean NOUSER = true;
        try {
            PreparedStatement preparedStatement =  collectionManager.getConnection().prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if (resultSet.getString(1).equals(login)){
                    message="LoginIsUsed";
                    NOUSER = false;
                    break;
                }
                if (resultSet.getString(3).equals(color)){
                    message="colorIsBusy";
                    NOUSER=false;
                    break;
                }
            }
            if (NOUSER){
                if (owner.getWeight()==null){
                    select = "INSERT INTO users_2(login, password, color, name, nationality, salt) VALUES ('" + login + "','" + password + "','" + color + "','" + owner.getName() + "','" + owner.getNationality() + "','" + salt + "');";
                }else {
                    select = "INSERT INTO users_2(login, password, color, name, nationality, weight, salt) VALUES ('" + login + "','" + password + "','" + color + "','" + owner.getName() + "','" + owner.getNationality() + "','" + owner.getWeight() + "','" + salt + "');";
                }
                CollectionManager.getOwners().add(owner);
                CollectionManager.getColorMap().put(login, color);
                preparedStatement =  collectionManager.getConnection().prepareStatement(select);
                preparedStatement.execute();
                //this.color=color;
                message = "AuthorizationSuccess";
            }
        } catch (SQLException e){
            e.printStackTrace();
            message="error";
        }
    }

    public String getColor() {
        return color;
    }

}
