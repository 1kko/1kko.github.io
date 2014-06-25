package com.betterofficelife.ninetosix;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.betterofficelife.constants.Constants;

@SuppressLint("HandlerLeak")
public class SplashActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Log.d(Constants.TAG, "SplashActivity.onCreate()");

		//3초후, MainActivity로 전환 
		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				startActivity(new Intent(SplashActivity.this, MainActivity.class));

				finish();
			}
		};
		handler.sendEmptyMessageDelayed(0, 1000);
	}
}
