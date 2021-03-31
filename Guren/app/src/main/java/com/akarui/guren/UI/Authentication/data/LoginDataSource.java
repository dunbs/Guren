package UI.Authentication.data;

import com.akarui.guren.database.GurenDatabase;
import com.akarui.guren.database.GurenDatabase_Impl;
import com.akarui.guren.database.entity.User;

import UI.Authentication.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    
    public Result<LoggedInUser> login(String username, String password) {
        
        try {
            // TODO: handle loggedInUser authentication
            User user = GurenDatabase.getInstance(null).userDAO().findByName(username);
            if (!user.getPassword().equals(password))
                return new Result.Error(new IOException("Incorrect username or password"));
            
            LoggedInUser loggedInUser =
                    new LoggedInUser(user.getId(), user.getNickname());
            return new Result.Success<>(loggedInUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }
    
    public void logout() {
        // TODO: revoke authentication
    }
}