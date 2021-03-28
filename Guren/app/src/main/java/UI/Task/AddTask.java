package UI.Task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.akarui.guren.JobHandler;
import com.akarui.guren.R;
import com.akarui.guren.database.Converter;
import com.akarui.guren.database.GurenDatabase;
import com.akarui.guren.database.entity.Job;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }
}