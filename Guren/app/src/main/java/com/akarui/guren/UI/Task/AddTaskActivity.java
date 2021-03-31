package com.akarui.guren.UI.Task;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.akarui.guren.R;
import com.akarui.guren.database.GurenDatabase;
import com.akarui.guren.database.entity.Job;

import java.text.SimpleDateFormat;
import java.time.Clock;
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
        int fromId = R.id.task_from_date_value;
        int toId = R.id.task_to_date_value;

        addChangeDateListener(fromId);
        addChangeDateListener(toId);
    }

    private void updateLabel(EditText editText, Calendar calendar) {
        String dateFormat = "yyyy/MM/dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
        editText.setText(simpleDateFormat.format(calendar.getTime()));

    }

    private void addChangeDateListener(int editTextId) {
        final Calendar calendar = Calendar.getInstance();
        EditText editText = (EditText) findViewById(editTextId);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(editText, calendar);
            }
        };

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddTaskActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void addChangeTimeListener(int editTextId) {

    }
}