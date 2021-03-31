package com.akarui.guren.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.akarui.guren.JobHandler;
import com.akarui.guren.R;
import com.akarui.guren.UI.Task.CalendarActivity;
import com.akarui.guren.database.GurenDatabase;
import com.akarui.guren.database.entity.Job;

public class JobBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int jobId = intent.getIntExtra("JobId", -1);
        if (jobId == -1)
            return;

        Job job = GurenDatabase.getInstance(context).jobDAO().findJobById(jobId);
        if (job == null)
            return;

        Intent jobActivityIntent = new Intent(context, CalendarActivity.class);
        jobActivityIntent.putExtra("JobId", job.getId());

        PendingIntent deadlineIntent = PendingIntent.getActivity(context, job.getId(), jobActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, String.valueOf(job.getId()))
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Your deadline is near!")
                        .setContentText(job.getTitle())
                        .setContentIntent(deadlineIntent)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            CharSequence channelName = context.getString(R.string.notification_channel);
            String channelDescription = context.getString(R.string.notification_channel_description);
            NotificationChannel channel = new NotificationChannel(
                    String.valueOf(jobId),
                    channelName,
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(channelDescription);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(jobId, notificationBuilder.build());

        JobHandler.createJobNotification(job, context);
    }
}
