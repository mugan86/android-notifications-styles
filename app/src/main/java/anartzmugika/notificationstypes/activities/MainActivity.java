package anartzmugika.notificationstypes.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import anartzmugika.notificationstypes.models.Content;
import anartzmugika.notificationstypes.models.Notification;
import anartzmugika.notificationstypes.R;
import anartzmugika.notificationstypes.databases.NotificationsDB;


public class MainActivity extends AppCompatActivity {
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = MainActivity.this;
        Button create_notificationButton = (Button) findViewById(R.id.create_notificationButton);

        create_notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long range = 1234567L;
                Random r = new Random();
                long number = (long)(r.nextDouble()*range);
                System.out.println("Message ID: " + number);
                NotificationsDB dbHelper = new NotificationsDB(activity);
                //String image, String date, String body, String source, String author, String author_img
                dbHelper.addMessage(new Content(number, "http://media.sustatu.eus/media/argazkiak/Bis/cache/sustatu2016Bis_content.jpg",
                        "2016-11-16", "Sustatu: 15 urte teknologia berrien inguruan informatzen", "http://sustatu.eus/1479208612", "Sustatu",
                        "http://media.sustatu.eus/media/argazkiak/180/cache/FaviconSustatuFaceBook180_small_profile.png"));

                System.out.println("Messages total: "+ dbHelper.getMessages().size());

                if (dbHelper.getMessages().size() == 1) new Notification().createSimpleNotification(activity);
                else new Notification().createBigStyleNotification(activity);

                dbHelper.close();

            }
        });


    }


}
