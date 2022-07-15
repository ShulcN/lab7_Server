package Command;

import Given.Person;
import Manager.CollectionManager;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends Command{
    private static final long serialVersionUID = 17L;
    private String password;

    public Login(String login, String password){
    }
    @Override
    public void DoCommand(CollectionManager collectionManager){
        String select = "SELECT * FROM users_2;";
        boolean NOUSER = true;
        String pas;
        try {
        PreparedStatement preparedStatement =  collectionManager.getConnection().prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if (resultSet.getString(1).equals(login)){
                    String salt = resultSet.getString("salt");
                    password+=salt;
                    try {
                        MessageDigest md = MessageDigest.getInstance("SHA-512");
                        byte[] messageDigest = md.digest(password.getBytes());
                        BigInteger pasBytes = new BigInteger(1, messageDigest);
                        this.password = pasBytes.toString(16);
                    } catch (NoSuchAlgorithmException e){
                        e.printStackTrace();
                    }
                    pas = resultSet.getString(2);
                    if (pas.equals(password)){
                        message="AuthorizationSuccess";
                        owner=CollectionManager.getOwners().stream().filter(p->p.getLogin().equals(login)).findFirst().orElse(null);
                        NOUSER = false;
                    }
                    break;
                }
            }
            if (NOUSER){
                message = "NoUser";
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
