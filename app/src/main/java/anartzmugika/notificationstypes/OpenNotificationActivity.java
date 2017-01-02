package anartzmugika.notificationstypes;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OpenNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_notification);

        Button create_big_style_notificationButton = (Button) findViewById(R.id.create_big_style_notificationButton);

        create_big_style_notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Notification().createBigStyleNotification(OpenNotificationActivity.this);
            }
        });

        int id = getIntent().getExtras().getInt("notificationId");

        System.out.println("ID of notification: " + id);

        NotificationManager myNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Ezabatu notifikazioa remove the notification with the specific id
        myNotificationManager.cancel(id);

        TextView select_notification_idTextView = (TextView) findViewById(R.id.select_notification_idTextView);
        select_notification_idTextView.setText("Select notification ID: " + id);
    }
}
