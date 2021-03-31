package UI.Authentication.ui.login;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.akarui.guren.R;
import com.akarui.guren.database.GurenDatabase;
import com.akarui.guren.database.entity.Group;
import com.akarui.guren.database.entity.User;

import java.time.LocalDateTime;

import UI.Authentication.data.LoginRepository;
import UI.Task.AddTask;

public class RegisterActivity extends AppCompatActivity {
    
    private LoginViewModel loginViewModel;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GurenDatabase.getInstance(getApplicationContext());
        
        setContentView(R.layout.activity_register);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
        
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText nicknameEditText = findViewById(R.id.displayName);
        final EditText emailEditText = findViewById(R.id.email);
        final EditText passwordEditText = findViewById(R.id.password);
        final EditText passwordConfirmEditText = findViewById(R.id.confirmPassword);
        final Button registerButton = findViewById(R.id.register);
        final TextView signTextView = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        
        registerButton.setEnabled(true);
        
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!passwordEditText.getText().toString().equals(passwordConfirmEditText.getText().toString())){
                    passwordConfirmEditText.setError("Password must match confirm password");
                    passwordEditText.setError("Password must match confirm password");
                    return;
                }
                
                if (!Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches()){
                    emailEditText.setError("Invalid email!");
                    return;
                }
                
                try {
                    User user = new User();
                    user.setUsername(usernameEditText.getText().toString());
                    user.setNickname(nicknameEditText.getText().toString());
                    user.setEmail(emailEditText.getText().toString());
                    user.setPassword(passwordEditText.getText().toString());
                    user.setCreatedDate(LocalDateTime.now());
                    GurenDatabase gurenDatabase = GurenDatabase.getInstance(getApplicationContext());
                    gurenDatabase.userDAO().insertAll(user);
                    user = gurenDatabase.userDAO().findByName(user.getUsername());
                    
                    Group group = new Group();
                    group.setCreatorId(user.getId());
                    group.setSingleUser(true);
                    group.setName(user.getUsername() + "_generated_group");
                    group.setCreatedDate(LocalDateTime.now());
                    gurenDatabase.groupDAO().insertAll(group);
    
                    LoginRepository.getInstance(null).login(user.getUsername(), user.getPassword());
                    
                    Intent intent = new Intent(RegisterActivity.this, CalendarActivity.class);
                    startActivity(intent);
                    
                } catch (SQLiteConstraintException e){
                    Toast.makeText(RegisterActivity.this, "Username or email existed!", Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(RegisterActivity.this, "Unexpected error!", Toast.LENGTH_SHORT).show();
                    Log.e("Register", e.getMessage(), e);
                }
            }
        });
        
        signTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}