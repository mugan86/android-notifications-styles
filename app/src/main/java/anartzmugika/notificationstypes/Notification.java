package anartzmugika.notificationstypes;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import anartzmugika.notificationstypes.activities.OpenNotificationActivity;

/**
 * Created by anartzmugika on 2/1/17.
 */

public class Notification {

    public void createBigStyleNotification(Context context)
    {

        Intent intent = createIntentWithNotification(context, 12);

        //Hurrengo Intent-era joateko zuzen
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = createNotificationBuilder(context, R.mipmap.ic_launcher, "Event Tracker " + 12, "Events received!!", 12);

        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();

        String[] events = {"Mugan86 Github repo: https://github.com/mugan86","line 2","line 3","line 4","line 5","line 6"};
        // Sets a title for the Inbox in expanded layout
        inboxStyle.setBigContentTitle("Event tracker details:");

        // Moves events into the expanded layout
        for (int i=0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }
        // Moves the expanded layout object into the notification object.
        mBuilder.setStyle(inboxStyle);

        mBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(12/* ID of notification */, mBuilder.build());


    }

    public void createSimpleNotification(Context context)
    {

        Intent intent = createIntentWithNotification(context, 11);

        //Hurrengo Intent-era joateko zuzen
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = createNotificationBuilder(context, R.mipmap.ic_launcher, "Event Tracker " + 11, "Events received!!", 11);

        mBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(11/* ID of notification */, mBuilder.build());

    }

    private Intent createIntentWithNotification(Context context, int id)
    {
        Intent intent = new Intent(context, OpenNotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("notificationId", id);
        return intent;
    }

    private NotificationCompat.Builder createNotificationBuilder(Context context, int icon, String title, String context_text, int id)
    {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(icon)
                .setContentTitle("Event tracker " + id)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)
                .setContentText("Events received!!!!");

        return mBuilder;
    }
}
