package com.example.chatter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

//intended for dashboard page
public class DashboardActivity extends AppCompatActivity {

    EditText secretCodeBox;          //user is allowed to choose secret code of his/her own choice
    Button joinBtn, shareBtn;        //user can either join or share the code with other members

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //identifies the attributes created on OnCreate
        secretCodeBox = findViewById(R.id.codeBox);
        joinBtn = findViewById(R.id.joinBtn);
        shareBtn = findViewById(R.id.shareBtn);

        URL serverURL;

        //used jitsi application to join a meeting
        try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions =
                    new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverURL)
                            .setWelcomePageEnabled(false)
                            .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        //function call to join button
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                       .setRoom(secretCodeBox.getText().toString())
                       .setWelcomePageEnabled(false)
                       .build();

                JitsiMeetActivity.launch(DashboardActivity.this, options);
            }
        });
    }
}