package UI.Task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import UI.Task.TaskListActivity;

import com.akarui.guren.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        addTaskList();
    }

    private void addTaskList() {
        FloatingActionButton addTaskBtn;
        addTaskBtn = (FloatingActionButton) findViewById(R.id.fab_calendar);

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CalendarActivity.this, AddTask.class));
            }
        });
    }
}