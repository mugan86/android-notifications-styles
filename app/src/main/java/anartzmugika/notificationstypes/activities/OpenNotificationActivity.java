package anartzmugika.notificationstypes.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import anartzmugika.notificationstypes.models.Notification;
import anartzmugika.notificationstypes.R;

public class OpenNotificationActivity extends AppCompatActivity {

    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_notification);

        activity = OpenNotificationActivity.this;

        Button create_big_style_notificationButton = (Button) findViewById(R.id.create_big_style_notificationButton);
        Button create_simple_notificationButton = (Button) findViewById(R.id.create_simple_notificationButton);

        create_big_style_notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Notification().createBigStyleNotification(activity);
            }
        });

        create_simple_notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Notification().createSimpleNotification(activity);
            }
        });

        int id = getIntent().getExtras().getInt("notificationId");

        System.out.println("ID of notification: " + id);

        new Notification().cancelNotification(activity, id, true);

        TextView select_notification_idTextView = (TextView) findViewById(R.id.select_notification_idTextView);
        select_notification_idTextView.setText("Select notification ID: " + id);
    }
}
