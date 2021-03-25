package com.akarui.guren;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;

import com.akarui.guren.database.Converter;
import com.akarui.guren.database.entity.Job;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

public class JobHandler {
//    /**
//     * @param job
//     * @param context
//     * @param duration duration before deadline
//     */
//    public static void createJobNotification(Job job, Context context, Duration duration){
//        if (job.getDeadline().compareTo(LocalDateTime.now()) <= 0){
//            return;
//        }
//
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(context, JobService.class);
//        intent.putExtra("JobId", job.getId());
//
//        PendingIntent deadlineIntent = PendingIntent.getActivity(context, job.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, Converter.ToTimeStamp(job.getDeadline().minus(duration)), deadlineIntent);
//    }
//
//    public static void deleteJobNotification(Job job, Context context){
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(context, JobService.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, job.getId(), intent, PendingIntent.FLAG_NO_CREATE);
//        alarmManager.cancel(pendingIntent);
//    }
}
