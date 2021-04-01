package com.akarui.guren.UI.Task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.akarui.guren.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        addTask();
        addCalendarDateChangeListener();
    }

    private void addTask() {
        FloatingActionButton addTaskBtn;
        addTaskBtn = (FloatingActionButton) findViewById(R.id.fab_calendar);

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CalendarActivity.this, AddTaskActivity.class));
            }
        });
    }

    private void addCalendarDateChangeListener() {
        CalendarView calendarView = (CalendarView) findViewById(R.id.task_calendar);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Intent intent = new Intent(CalendarActivity.this, TaskListActivity.class);
                intent.putExtra("selectedDate", LocalDate.of(year, month + 1, dayOfMonth));
                startActivity(intent);
            }
        });
    }
}