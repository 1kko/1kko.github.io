package com.betterofficelife.ninetosix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.betterofficelife.constants.Constants;


public class MainActivity extends Activity {
	BackPressCloseHandler backPressCloseHandler;
	Button m_parking_register_btn;
	Button m_lunchmenu_btn;
	Button m_coffeeorder_btn;
	Button m_dinnerroom_btn;

	ParkingPreference pref = new ParkingPreference(this);

	String parkingFlag = "";


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.d(Constants.TAG, "MainActivity.onCreate()");

		backPressCloseHandler = new BackPressCloseHandler(this);
		//단말별 해상도 맞추기
		/*
		 1 - 480 x 800 : 갤럭시S, 갤럭시S2, 옵티머스 2X, Nexus S, Nexus One, HTC Desire HD, HTC Desire HD2
		 2 - 800 x 1280 : 갤럭시탭 10.1, 갤럭시노트1, 넥서스 7
		 3 - 720 x 1280 : 갤럭시S3, 갤럭시S2 HD, 갤럭시노트2, 옵티머스G
		 5 - 1080 x 1920 : G2, 갤럭시S4, 갤럭시노트3

		 [None]
		 4 - 1200 x 1920 : 넥서스 7(2013)
		 */
		DisplayMetrics displayMatrics = new DisplayMetrics();
		Constants.width = getWindowManager().getDefaultDisplay().getWidth();
		Constants.height = getWindowManager().getDefaultDisplay().getHeight();

		System.out.print(Constants.width);
		System.out.print(Constants.height);

		//1 - 480 x 800
		if(Constants.height == 800) { 
			Log.d("DeviceResolution", "480 x 800 : 갤럭시S, 갤럭시S2, 옵티머스 2X, Nexus S, Nexus One, HTC Desire HD, HTC Desire HD2");
			setContentView(R.layout.activity_main_480x800);
		}

		//2 - 800 x 1280
		else if ( Constants.width == 800 && Constants.height == 1280) { 
			Log.d("DeviceResolution", "800 x 1280 : 800 x 1280 : 갤럭시탭 10.1, 갤럭시노트1, 넥서스 7");
			setContentView(R.layout.activity_main_800x1280);
		}

		//3 - 720 x 1280
		else if ( Constants.width == 720 && Constants.height == 1280) { 
			Log.d("DeviceResolution", "720 x 1280 : 갤럭시S3, 갤럭시S2 HD, 갤럭시노트2, 옵티머스G");
			setContentView(R.layout.activity_main);
		}
		//5 - 1080 x 1920
		else if (Constants.width == 1080 && Constants.height == 1920) {
			Log.d("DeviceResolution", "1080 x 1920 : G2, 갤럭시S4, 갤럭시노트3");
			setContentView(R.layout.activity_main);
		}

		//exception : 갤럭시 넥서스 
		else if (Constants.width == 720 && Constants.height == 1184) {
			Log.d("DeviceResolution", "720 x 1184 : 갤럭시 넥서스");
			setContentView(R.layout.activity_main_720x1280_galaxy_nexus);
		}
		else {
			setContentView(R.layout.activity_main);
		}

		m_parking_register_btn = (Button)findViewById(R.id.btn_parking_register);
		m_coffeeorder_btn = (Button)findViewById(R.id.btn_coffeeorder);
		m_dinnerroom_btn = (Button)findViewById(R.id.btn_dinnermenu);

		//주차 위치화면으로 이동
		m_parking_register_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,ParkingRegisterActivity.class);
				startActivity(intent);
			}
		});

		m_coffeeorder_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,CafeteriaActivity.class);
				startActivity(intent);
			}
		});

		//사외 야근식당 화면으로 이동
		m_dinnerroom_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,DinnerRoomActivity.class);
				startActivity(intent);
			}
		});



	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// XML로 옵션메뉴 추가 하기
		MenuInflater inflater =  getMenuInflater();
		inflater.inflate(R.layout.menu, menu);

		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.infoMenu : 
			//Toast.makeText(this, "menu1 selected", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(MainActivity.this,InfoActivity.class);
			startActivity(intent);
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}



	@Override
	protected void onPause() {
		super.onPause();

		Log.d(Constants.TAG, "MainActivity.onPause()");	
	}

	@Override
	protected void onResume() {
		super.onResume();

		Log.d(Constants.TAG, "MainActivity.onResume()");	
	}

	@Override
	protected void onRestart() {
		super.onRestart();

		Log.d(Constants.TAG, "MainActivity.onRestart()");	
	}

	public void onBackPressed() {
		backPressCloseHandler.onBackPressed();
	}
}

