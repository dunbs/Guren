package UI.Task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.akarui.guren.R;

public class TaskListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        generateTaskCard();
    }

    private void generateTaskCard() {
        //Very demo, pls literally generate task card
        TextView taskTitle, taskTime;

        taskTitle = (TextView) findViewById(R.id.task_title);
        taskTime = (TextView) findViewById(R.id.task_time);

        taskTitle.setText("Sample Title");

        taskTime.setText("Time: 12:00 - 15:00");

    }


}