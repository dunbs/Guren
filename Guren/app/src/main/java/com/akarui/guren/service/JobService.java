package com.akarui.guren.service;

import android.app.PendingIntent;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;

import com.akarui.guren.JobHandler;
import com.akarui.guren.MainActivity;
import com.akarui.guren.database.GurenDatabase_Impl;
import com.akarui.guren.database.entity.Job;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class JobService extends JobIntentService {
    
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        int jobId = intent.getIntExtra("JobId", -1);
        if (jobId == -1)
            return;
            
        Job job = GurenDatabase_Impl.getInstance(this).jobDAO().findJobById(jobId);
        if (job == null)
            return;
    
        Intent jobActivityIntent = new Intent(this, MainActivity.class);
        jobActivityIntent.putExtra("JobId", job.getId());
        
        PendingIntent deadlineIntent = PendingIntent.getActivity(this, job.getId(), jobActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, String.valueOf(job.getId()))
                        .setContentTitle("Your deadline is near!")
                        .setContentText(job.getTitle())
                        .setContentIntent(deadlineIntent);
    
        notificationBuilder.notify();
        
        long dayInDifferent = job.getDeadline().until(LocalDateTime.now(), ChronoUnit.DAYS);
        if (dayInDifferent > 0){
            JobHandler.createJobNotification(job, this, Duration.ofDays(dayInDifferent - 1));
        }
    }
}
