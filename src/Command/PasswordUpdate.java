package Command;

import Manager.CollectionManager;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordUpdate extends Command{
    private static final long serialVersionUID = 93L;
    private String oldPassword;
    private String newPassword;
    public PasswordUpdate(String login, String oldPassword, String newPassword){
        this.login=login;
        this.newPassword=newPassword;
        this.oldPassword=oldPassword;

    }
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
                    oldPassword=encryption(oldPassword, salt);
                    pas = resultSet.getString(2);
                    if (pas.equals(oldPassword)){
                        message="ChangeSuccess";
                        newPassword=encryption(newPassword, salt);
                        PreparedStatement preparedStatement1 = collectionManager.getConnection().prepareStatement("UPDATE users_2 SET password='"+newPassword+"' WHERE login='"+login+"';");
                        preparedStatement1.execute();
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
    private String encryption(String pas, String salt){
        pas+=salt;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(pas.getBytes());
            BigInteger pasBytes = new BigInteger(1, messageDigest);
            pas = pasBytes.toString(16);
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return pas;
    }
}
