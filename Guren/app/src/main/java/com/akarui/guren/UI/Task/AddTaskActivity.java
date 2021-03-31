package com.akarui.guren.UI.Task;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.akarui.guren.R;
import com.akarui.guren.database.GurenDatabase;
import com.akarui.guren.database.entity.Job;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Locale;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        renderDatePicker();
    }

    private void addTask() {
        Job job = new Job();
        GurenDatabase.getInstance(getApplicationContext()).jobDAO().insertAll(new Job());
    }

    private void renderDatePicker() {
        int fromDateValue = R.id.task_from_date_value;
        int toDateValue = R.id.task_to_date_value;
        int fromTimeValue = R.id.task_from_time_value;
        int toTimeValue = R.id.task_to_time_value;

        addChangeDateListener(fromDateValue, fromTimeValue);
        addChangeDateListener(toDateValue, toTimeValue);
    }

    private void updateLabel(EditText editText, Calendar calendar) {
        String dateFormat = "yyyy/MM/dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
        editText.setText(simpleDateFormat.format(calendar.getTime()));
    }

    private void addChangeDateListener(int dateTextId, int timeTextId) {
        final Calendar calendar = Calendar.getInstance();
        EditText dateText = (EditText) findViewById(dateTextId);
        EditText timeText = (EditText) findViewById(timeTextId);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(dateText, calendar);
            }
        };

        TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                timeText.setText(hourOfDay + " : " + minute );
            }
        };

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddTaskActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        timeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddTaskActivity.this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            timeText.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
                timePickerDialog.show();
                
            }
        });
    }

    private void addChangeTimeListener(int assignedNameId, int fromDateTimeId, int toDateTimeId, int priorityId) {
    }
}