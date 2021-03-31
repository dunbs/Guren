package com.akarui.guren.UI.Task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.akarui.guren.R;
import com.akarui.guren.UI.Authentication.data.LoginRepository;
import com.akarui.guren.UI.Authentication.data.model.LoggedInUser;
import com.akarui.guren.database.GurenDatabase;
import com.akarui.guren.database.entity.Group;
import com.akarui.guren.database.entity.Job;
import com.akarui.guren.utils.generateViews.GenerateTaskView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.LocalTime;
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
        LoggedInUser user = LoginRepository.getInstance().getLoggedInUser();
        Group userGroup = GurenDatabase.getInstance(getApplicationContext()).groupDAO().loadSingleUserGroup(user.getUserId());
        LocalDate selectedDate = (LocalDate) getIntent().getSerializableExtra("selectedDate");
        List<Job> taskList = GurenDatabase.getInstance(this).jobDAO()
                .findJobsByGroupAndDays(
                        userGroup.getId(),
                        selectedDate.atStartOfDay(),
                        selectedDate.atTime(LocalTime.MAX));
    
        ConstraintLayout taskListLayout = findViewById(R.id.task_list_container);
        ConstraintSet taskListLayoutSet = new ConstraintSet();
    
        int prevId = 0;
        int currentId = -1;

        for(Job task : taskList) {
            String taskTitle = task.getTitle();
            String taskTime = task.getDeadline().toString();
            String taskContent = task.getDetail();

            prevId = currentId;
    
            CardView taskCard =  generateTaskView.generateTaskCard(this, taskTitle, taskTime ,taskContent);
            currentId = taskCard.generateViewId();
            taskCard.setId(currentId);
            taskListLayout.addView(taskCard);

            taskListLayoutSet.clone(taskListLayout);
            
            if (prevId == -1){
                taskListLayoutSet.connect(taskCard.getId(), ConstraintSet.TOP, taskListLayout.getId(), ConstraintSet.TOP, 3);
                taskListLayoutSet.connect(taskCard.getId(), ConstraintSet.START, taskListLayout.getId(), ConstraintSet.START, 2);
            } else {
                taskListLayoutSet.connect(currentId, ConstraintSet.TOP, prevId, ConstraintSet.BOTTOM, 20);
                taskListLayoutSet.connect(currentId, ConstraintSet.START, taskListLayout.getId(), ConstraintSet.START, 3);
            }
            taskListLayoutSet.applyTo(taskListLayout);

        }

    }


}