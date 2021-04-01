package com.akarui.guren.UI.Task;

import android.content.Intent;
import android.os.Bundle;
import android.security.identity.CipherSuiteNotSupportedException;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.akarui.guren.R;
import com.akarui.guren.UI.Finance.FinanceActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        addTask();
        changeView();
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

    private void changeView() {
        Button taskBtn = findViewById(R.id.task_view_btn);
        Button financeBtn = findViewById(R.id.finance_view_btn);

        taskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalendarActivity.this, CalendarActivity.class));
            }
        });

        financeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalendarActivity.this, FinanceActivity.class));
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