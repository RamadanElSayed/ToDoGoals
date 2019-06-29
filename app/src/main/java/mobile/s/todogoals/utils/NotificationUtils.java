package mobile.s.todogoals.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import mobile.s.todogoals.R;
import mobile.s.todogoals.home.views.activities.MainActivity;


public class NotificationUtils {

    public static final int EARNING_ID = 3;
    private static final int DEFAULT_NOT_ID = 12;
    private static String TAG = NotificationUtils.class.getSimpleName();
    private NotificationManager mNotifiManager;
    private Context mContext;
    public NotificationUtils(Context mContext) {
        this.mContext = mContext;
    }

    public void showNotificationMessage(final String message, Intent intent) {

       final int icon = R.drawable.studying;
       Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        String id = mContext.getString(R.string.channel_id); // default_channel_id
        String title = mContext.getString(R.string.channel_name); // Default Channel
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;
        if (mNotifiManager == null) {
            mNotifiManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = mNotifiManager.getNotificationChannel(id);
            if (mChannel == null) {
                mChannel = new NotificationChannel(id, title, importance);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                mNotifiManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(mContext, id);
            intent = new Intent(mContext, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
            builder.setContentTitle(mContext.getString(R.string.app_name))                          // required
                    .setSmallIcon(icon)   // required
                    .setContentText(mContext.getString(R.string.notify_desc)) // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setSound(alarmSound)
                     .setLights(Color.RED, 500, 500)
                    .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
                     .setContentIntent(pendingIntent)
                    .setTicker(message)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        } else {
            builder = new NotificationCompat.Builder(mContext, id);
            intent = new Intent(mContext, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
            builder.setContentTitle(mContext.getString(R.string.app_name))                          // required
                    .setSmallIcon(icon)   // required
                    .setContentText(mContext.getString(R.string.notify_desc)) // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setSound(alarmSound)
                    .setLights(Color.RED, 500, 500)
                    .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
                    .setContentIntent(pendingIntent)
                    .setTicker(message)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        }
        Notification mNotification = builder.build();
        mNotifiManager.notify(DEFAULT_NOT_ID, mNotification);
    }
}