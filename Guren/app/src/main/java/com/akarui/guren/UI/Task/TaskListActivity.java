package com.akarui.guren.UI.Task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.akarui.guren.R;
import com.akarui.guren.database.GurenDatabase;
import com.akarui.guren.database.entity.Job;
import com.akarui.guren.utils.generateViews.GenerateTaskView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        insertTaskCard();
        addTask();
    }

    private void addTask() {
        FloatingActionButton addTaskBtn;
        addTaskBtn = (FloatingActionButton) findViewById(R.id.fab_task_list);

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TaskListActivity.this, AddTaskActivity.class));
            }
        });
    }

    private void insertTaskCard() {
        GenerateTaskView generateTaskView = new GenerateTaskView();
        List<Job> taskList = GurenDatabase.getInstance(this).jobDAO().findJobsByGroup(1);

        ConstraintLayout taskListLayout = findViewById(R.id.task_list_container);

        ConstraintSet taskListLayoutSet = new ConstraintSet();
        CardView taskCard = generateTaskView.generateTaskCard(this, "Sample Title", "Sample Time", "Sample Content");
        taskCard.setId(taskCard.generateViewId());
        taskListLayout.addView(taskCard);

        int prevId, currentId;

        currentId = taskCard.getId();

        taskListLayoutSet.clone(taskListLayout);
        taskListLayoutSet.connect(taskCard.getId(), ConstraintSet.TOP, taskListLayout.getId(), ConstraintSet.TOP, 3);
        taskListLayoutSet.connect(taskCard.getId(), ConstraintSet.START, taskListLayout.getId(), ConstraintSet.START, 2);
        taskListLayoutSet.applyTo(taskListLayout);


        for(Job task : taskList) {
            String taskTitle = task.getTitle();
            String taskTime = task.getDeadline().toString();
            String taskContent = task.getDetail();

            prevId = currentId;
            currentId = taskCard.generateViewId();

            taskCard =  generateTaskView.generateTaskCard(this, taskTitle, taskTime ,taskContent);
            taskCard.setId(currentId);
            taskListLayout.addView(taskCard);

            taskListLayoutSet.clone(taskListLayout);
            taskListLayoutSet.connect(currentId, ConstraintSet.TOP, prevId, ConstraintSet.BOTTOM, 20);
            taskListLayoutSet.connect(currentId, ConstraintSet.START, taskListLayout.getId(), ConstraintSet.START, 3);
            taskListLayoutSet.applyTo(taskListLayout);

        }

    }


}