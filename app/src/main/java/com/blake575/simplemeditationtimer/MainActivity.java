package com.blake575.simplemeditationtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private boolean isGoing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button clicker = (Button) findViewById(R.id.Start);
        final TextView label = (TextView) findViewById(R.id.textView2);
        final Button restart = (Button) findViewById(R.id.Restart);

        restart.setEnabled(false);
        clicker.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v){
                    isGoing = true;
                    CountDownTimer timer;
                    restart.setEnabled(true);
                    new CountDownTimer(600000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            if(isGoing){
                            clicker.setEnabled(false);
                            int seconds = (int) (millisUntilFinished / 1000);
                            int minutes = seconds / 60;
                            seconds = seconds % 60;
                            label.setText(minutes + ":" + seconds);
                        }
                        else{
                                cancel();
                            }

                        }

                        public void onFinish() {
                            clicker.setEnabled(true);
                            restart.setEnabled(false);
                            MediaPlayer je = MediaPlayer.create(MainActivity.this,R.raw.je);
                            je.start();
                            label.setText("10:00");
                            cancel();
                        }
                    }.start();

        }


        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGoing = false;
                clicker.setEnabled(true);
                restart.setEnabled(false);
                label.setText("10:00");
            }
        });

    }
}
