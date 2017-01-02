package anartzmugika.notificationstypes.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import anartzmugika.notificationstypes.Notification;
import anartzmugika.notificationstypes.R;
import anartzmugika.notificationstypes.databases.NotificationsDB;


public class MainActivity extends AppCompatActivity {
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = MainActivity.this;
        Button create_big_style_notificationButton = (Button) findViewById(R.id.create_big_style_notificationButton);

        create_big_style_notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Notification().createBigStyleNotification(activity);
            }
        });

        NotificationsDB dbHelper = new NotificationsDB(activity);
        dbHelper.getNotification();

        dbHelper.close();
    }


}
