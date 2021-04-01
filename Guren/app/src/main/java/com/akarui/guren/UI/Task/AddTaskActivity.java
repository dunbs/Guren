package com.akarui.guren.UI.Task;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.akarui.guren.JobHandler;
import com.akarui.guren.R;
import com.akarui.guren.UI.Authentication.data.LoginRepository;
import com.akarui.guren.database.GurenDatabase;
import com.akarui.guren.database.entity.Job;
import com.akarui.guren.database.entity.JobAssignee;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

import lombok.var;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Spinner spinner = findViewById(R.id.task_priority_value);
        String[] arraySpinner = new String[] {"Low", "Medium", "High"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arraySpinner);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        renderDatePicker();

        var addTaskButton = (Button)findViewById(R.id.add_button);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });
    }

    private void addTask() {
        var fromDate = (EditText)findViewById(R.id.task_from_date_value);
        var toDate = (EditText)findViewById(R.id.task_to_date_value);
        var fromTime = (EditText)findViewById(R.id.task_from_time_value);
        var toTime = (EditText)findViewById(R.id.task_to_time_value);
        var title = (EditText)findViewById(R.id.assigned_name_value);
        var details = (EditText)findViewById(R.id.task_detail_value);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime fromDateTime = LocalDateTime.parse(fromDate.getText().toString() + " " + fromTime.getText().toString(), dateTimeFormatter);
        LocalDateTime toDateTime = LocalDateTime.parse(toDate.getText().toString() + " " + toTime.getText().toString(), dateTimeFormatter);

        var gurenDb = GurenDatabase.getInstance(getApplicationContext());

        Job job = new Job();
        job.setCreatedDate(LocalDateTime.now());
        job.setCreatorId(LoginRepository.getInstance().getLoggedInUser().getUserId());
        job.setGroupId(gurenDb.groupDAO().loadSingleUserGroup(job.getCreatorId()).getId());
        job.setTitle(title.getText().toString());
        job.setDetail(details.getText().toString());
        job.setStartDateTime(fromDateTime);
        job.setDeadline(toDateTime);

        gurenDb.jobDAO().insertAll(job);

        job = gurenDb.jobDAO().findNewestAddedJob();
        JobHandler.createJobNotification(job, this);

        JobAssignee jobAssignee = new JobAssignee();
        jobAssignee.setAssignerId(job.getCreatorId());
        jobAssignee.setJobId(job.getId());
        jobAssignee.setUserId(job.getCreatorId());
        jobAssignee.setCreatedDate(LocalDateTime.now());

        gurenDb.jobAssigneeDAO().insertAll(jobAssignee);
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

                            timeText.setText(String.format("%02d:%02d", hourOfDay, minute) );
                        }
                    }, mHour, mMinute, false);
                timePickerDialog.show();
                
            }
        });
    }

    private void addChangeTimeListener(int assignedNameId, int fromDateTimeId, int toDateTimeId, int priorityId) {
    }
}