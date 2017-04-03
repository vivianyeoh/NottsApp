package com.example.user.nottspark.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import getresult.example.asus.nottspark.R;


public class SplashScreen extends Activity {

    /**
     * The thread to process splash screen events
     */
    private Thread mSplashThread;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Splash screen view
        setContentView(R.layout.activity_splash_screen);

        final SplashScreen sPlashScreen = this;

        // The thread to wait for splash screen events
        mSplashThread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        // Wait given period of time or exit on touch
                        wait(2000);
                    }
                } catch (InterruptedException ex) {
                }

                finish();

                // Run next activity which is your GameActivity
                Intent intent = new Intent();
                intent.setClass(sPlashScreen, LoginActivity.class); //Here You Can Replace MainActivity.class with your GameActivity

                startActivity(intent);

            }
        };

        mSplashThread.start();
    }

    /**
     * Processes splash screen touch events
     */
    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        if (evt.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized (mSplashThread) {
                mSplashThread.notifyAll();
            }
            }
        return true;
    }
    }