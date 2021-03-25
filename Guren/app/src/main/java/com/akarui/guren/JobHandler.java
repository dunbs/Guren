package com.akarui.guren;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.akarui.guren.database.Converter;
import com.akarui.guren.database.entity.Job;
import com.akarui.guren.service.JobService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class JobHandler {
    public static final long[] TIMES_BEFORE_DEADLINE_NOTIFICATION = {48 * 60, 24 * 60, 60, 30, 15};
    
    public static void createJobNotification(Job job, Context context){
        LocalDateTime deadline = job.getDeadline();
        LocalDateTime now = LocalDateTime.now();
        
        long minutes = now.until(deadline, ChronoUnit.MINUTES);
        
        int i;
        for (i = 0; i < TIMES_BEFORE_DEADLINE_NOTIFICATION.length; i++){
            if (minutes < TIMES_BEFORE_DEADLINE_NOTIFICATION[i]){
                break;
            }
        }
    
        if (i >= TIMES_BEFORE_DEADLINE_NOTIFICATION.length){
            return;
        }
        createJobNotification(job, context, Duration.ofHours(TIMES_BEFORE_DEADLINE_NOTIFICATION[i]));
    }
    
    /**
     * @param job
     * @param context
     * @param duration duration before deadline
     */
    public static void createJobNotification(Job job, Context context, Duration duration){
        if (job.getDeadline().compareTo(LocalDateTime.now()) <= 0){
            return;
        }

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, JobService.class);
        intent.putExtra("JobId", job.getId());

        PendingIntent deadlineIntent = PendingIntent.getActivity(context, job.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, Converter.ToTimeStamp(job.getDeadline().minus(duration)), deadlineIntent);
    }

    public static void deleteJobNotification(Job job, Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, JobService.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, job.getId(), intent, PendingIntent.FLAG_NO_CREATE);
        alarmManager.cancel(pendingIntent);
    }
}
