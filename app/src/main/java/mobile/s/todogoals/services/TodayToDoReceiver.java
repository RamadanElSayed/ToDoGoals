package mobile.s.todogoals.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import mobile.s.todogoals.R;
import mobile.s.todogoals.home.views.activities.MainActivity;
import mobile.s.todogoals.utils.NotificationUtils;

public class TodayToDoReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationUtils notificationUtils = new NotificationUtils(context);
        Intent earningIntent = new Intent(context, MainActivity.class);
        notificationUtils.showNotificationMessage(context.getString(R.string.app_name)
                ,earningIntent);
    }
}