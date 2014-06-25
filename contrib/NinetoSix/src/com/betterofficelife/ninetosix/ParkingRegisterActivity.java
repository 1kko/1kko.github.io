package com.betterofficelife.ninetosix;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.betterofficelife.constants.Constants;
import com.betterofficelife.ninetosix.wheel.widget.ArrayWheelAdapter;
import com.betterofficelife.ninetosix.wheel.widget.OnWheelChangedListener;
import com.betterofficelife.ninetosix.wheel.widget.WheelView;


public class ParkingRegisterActivity extends Activity {

	private Button btnParkingPositionRegister;
	private WheelView wvParkingPlace;
	private TextView mCurrentDate;
	private String parkingPlace[];

	String getParkingPosition = "";

	String initialTime = "";
	String registerTimeInterval = "";

	ParkingPreference pref = new ParkingPreference(this);
	TimeIntervalCalculater timeCal = new TimeIntervalCalculater(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.d(Constants.TAG, "ParkingRegisterActivity.onCreate()");

		//단말별 해상도 맞추기
		DisplayMetrics displayMatrics = new DisplayMetrics();
		Constants.width= getWindowManager().getDefaultDisplay().getWidth();
		Constants.height = getWindowManager().getDefaultDisplay().getHeight();

		if(Constants.height == 800) { //Galaxy S2
			Log.d("DeviceResolution", "480 x 800 : 갤럭시S, 갤럭시S2, 옵티머스 2X, Nexus S, Nexus One, HTC Desire HD, HTC Desire HD2");
			setContentView(R.layout.activity_parking_register_480x800);
			WheelView.TEXT_SIZE = 90;
		}

		//2 - 800 x 1280
		else if ( Constants.width == 800 && Constants.height == 1280) { 
			Log.d("DeviceResolution", "800 x 1280 : 800 x 1280 : 갤럭시탭 10.1, 갤럭시노트1, 넥서스 7");
			setContentView(R.layout.activity_parking_register_800x1280);
			WheelView.TEXT_SIZE = 110;
		}

		else if (Constants.width == 720 && Constants.height== 1184) { 
			Log.d("DeviceResolution", "720 x 1184 : 갤럭시 넥서스");
			setContentView(R.layout.activity_parking_register_720x1280_galaxy_nexus);
			WheelView.TEXT_SIZE = 110;
		}
		else if (Constants.width == 720 && Constants.height ==1280) {
			Log.d("DeviceResolution", "720 x 1280 : 갤럭시S3, 갤럭시S2 HD, 갤럭시노트2, 옵티머스G");
			setContentView(R.layout.activity_parking_register);
			WheelView.TEXT_SIZE = 110;
		}

		//5 - 1080 x 1920
		else if (Constants.width == 1080 && Constants.height == 1920) {
			Log.d("DeviceResolution", "1080 x 1920 : G2, 갤럭시S4, 갤럭시노트3");
			setContentView(R.layout.activity_parking_register_1080x1920);
			WheelView.TEXT_SIZE = 180;
		}
		else {
			setContentView(R.layout.activity_parking_register);
		}

		wvParkingPlace = (WheelView) findViewById(R.id.parkingPlace);
		mCurrentDate = (TextView)findViewById(R.id.tv_current_date_register);		
		btnParkingPositionRegister = (Button)findViewById(R.id.btn_parking_position_register);

		parkingPlace = new String[] {"B2", "B3", "B4"};

		// WheelView 에 parkingPlace 적용
		wvParkingPlace.setVisibleItems(3);
		wvParkingPlace.setAdapter(new ArrayWheelAdapter<String>(parkingPlace));

		getParkingPosition = pref.getValue(Constants.PREF_PARKING_POSITION, "");
		initialTime = pref.getValue(Constants.PREF_INITIAL_TIME, "");
		registerTimeInterval = pref.getValue(Constants.PREF_REGISTER_TIME_INTERVAL, "");

		Log.d(Constants.TAG, "ParkingRegisterActivity.onCreate().getParkingPosition : " + getParkingPosition);
		Log.d(Constants.TAG, "ParkingRegisterActivity.onCreate().initialTime : " + initialTime);
		Log.d(Constants.TAG, "ParkingRegisterActivity.onCreate().registerTimeInterval : " + registerTimeInterval);

		if (getParkingPosition.equals("")) {
			wvParkingPlace.setCurrentItem(1);

			mCurrentDate.setText("주차 등록하지 않았습니다.");

			Toast.makeText(this, "주차 위치를 선택하세요.", Toast.LENGTH_SHORT).show();
		} else {
			String tempRegisterTime = pref.getValue(Constants.PREF_REGISTER_TIME, "");
			Long registerTime = Long.parseLong(tempRegisterTime);

			Log.d(Constants.TAG, "ParkingRegisterActivity.onCreate().startTime : " + registerTime);

			// 버튼을 통해 등록하지 않았다면 마지막으로 등록한 시간 변경하지 않음(SharedPreference 에 마지막 등록한 시간 변경 안 함)
			registerTimeInterval = timeCal.getTimeIntervalCalculater(registerTime, Constants.NO_CHANGE_TIME);

			mCurrentDate.setText(registerTimeInterval);
		}

		/*
		 * 현재 주차 위치에 따라 WheelView 에 보여주기
		 */
		if (getParkingPosition.equals(Constants.PARKING_B2)) {
			wvParkingPlace.setCurrentItem(0);

			Toast.makeText(this, "마지막 주차 위치는 B2 입니다.", Toast.LENGTH_SHORT).show();
		} else if (getParkingPosition.equals(Constants.PARKING_B3)) {
			wvParkingPlace.setCurrentItem(1);

			Toast.makeText(this, "마지막 주차 위치는 B3 입니다.", Toast.LENGTH_SHORT).show();
		} else if (getParkingPosition.equals(Constants.PARKING_B4)) {
			wvParkingPlace.setCurrentItem(2);

			Toast.makeText(this, "마지막 주차 위치는 B4 입니다.", Toast.LENGTH_SHORT).show();
		} 

		/*
		 * WheelView 움직임에 따라  getParkingPosition 에 주차 위치값 저장
		 */
		wvParkingPlace.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				if(newValue == 0) {
					//Log.d(Constants.TAG, "ParkingRegisterActivity.onCreate().wvParkingPlace.getParkingPosition : B2");

					getParkingPosition = Constants.PARKING_B2;
				} else if(newValue == 1) {
					//Log.d(Constants.TAG, "ParkingRegisterActivity.onCreate().wvParkingPlace.getParkingPosition : B3");

					getParkingPosition = Constants.PARKING_B3;
				} else if(newValue == 2) {
					//Log.d(Constants.TAG, "ParkingRegisterActivity.onCreate().wvParkingPlace.getParkingPosition : B4");

					getParkingPosition = Constants.PARKING_B4;
				}

			}
		});

		/*
		 * WheelView 에서 결정한 주차 위치에 따라 등록을 했을 때 등록 시간과 주차 위치를 SharedPreference 에 넣어줌
		 */
		btnParkingPositionRegister.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.d(Constants.TAG, "ParkingRegisterActivity.onCreate().btnParkingPositionRegister");

				// if : 최초 수동 등록했을 때 / else : 수동 또는 자동으로 등록  한 번 이후 부터 적용 
				if (initialTime.equals("")) {
					Log.d(Constants.TAG, "ParkingRegisterActivity.onCreate().btnParkingPositionRegister.ONCE");

					SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
					Date currentTime = new Date ( );
					initialTime = formatter.format ( currentTime );

					pref.put(Constants.PREF_INITIAL_TIME, initialTime);
					registerTimeInterval = timeCal.getTimeIntervalCalculater(initialTime);

					Log.d(Constants.TAG, "ParkingRegisterActivity.onCreate().btnParkingPositionRegister.initialTime : " + initialTime);
					Log.d(Constants.TAG, "ParkingRegisterActivity.onCreate().btnParkingPositionRegister.initial.registerTimeInterval : " + registerTimeInterval);

				} else {
					Log.d(Constants.TAG, "ParkingRegisterActivity.onCreate().btnParkingPositionRegister.MORE_THAN_ONCE");

					String tempRegisterTime = pref.getValue(Constants.PREF_REGISTER_TIME, "");
					Long registerTime = Long.parseLong(tempRegisterTime);

					Log.d(Constants.TAG, "ParkingRegisterActivity.onCreate().btnParkingPositionRegister.startTime : " + registerTime);

					// 버튼을 통해 등록할 때에만 마지막 등록 시간 변경(SharedPreferences 에 마지막 등록한 시간 변경)
					registerTimeInterval = timeCal.getTimeIntervalCalculater(registerTime,  Constants.CHANGE_TIME);

					Log.d(Constants.TAG, "ParkingRegisterActivity.onCreate().btnParkingPositionRegister.MORE_THAN_ONCE.registerTimeInterval : " + registerTimeInterval);
				}	

				pref.put(Constants.PREF_REGISTER_TIME_INTERVAL, registerTimeInterval);

				// 맨 처음 휠뷰를 건드리지 않았다면 wvParkingPlace.addChangingListener 메소드가 불리지 않으므로 값 없으면 휠뷰의 가운데 값 저장
				if (getParkingPosition.equals("")) {
					getParkingPosition = Constants.PARKING_B3;

					//Toast.makeText(ParkingRegisterActivity.this, "주차 위치를 선택하세요", Toast.LENGTH_SHORT).show();
				}

				pref.put(Constants.PREF_PARKING_POSITION, getParkingPosition);

				Log.d(Constants.TAG, "ParkingRegisterActivity.onCreate().btnParkingPositionRegister.getParkingPosition : " + getParkingPosition);

				Toast.makeText(ParkingRegisterActivity.this, getParkingPosition + " 층에 등록되었습니다.", Toast.LENGTH_SHORT).show();

				finish();
			}

		});

	}

	@Override
	protected void onResume() {
		super.onResume();

		Log.d(Constants.TAG, "ParkingRegisterActivity.onResume()");	
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onRestart()
	 * 만약 태그를 찍어서 TagViewer 액티비티가 시작된 후 종료되면서 기존 액티비티로 다시 전환할 때 마지막으로 
	 * 등록한 주차 위치 값과 등록 시간을 해당 액티비티에 적용함
	 */
	@Override
	protected void onRestart() {
		super.onRestart();

		Log.d(Constants.TAG, "ParkingRegisterActivity.onRestart()");	

		getParkingPosition = pref.getValue(Constants.PREF_PARKING_POSITION, "");
		initialTime = pref.getValue(Constants.PREF_INITIAL_TIME, "");
		registerTimeInterval = pref.getValue(Constants.PREF_REGISTER_TIME_INTERVAL, "");

		//Log.d(Constants.TAG, "ParkingRegisterActivity.onRestart().getParkingPosition : " + getParkingPosition);
		//Log.d(Constants.TAG, "ParkingRegisterActivity.onRestart().initialTime : " + initialTime);
		//Log.d(Constants.TAG, "ParkingRegisterActivity.onRestart().registerTimeInterval : " + registerTimeInterval);

		/*
		 * 주차 위치 등록 여부 확인
		 */
		if (getParkingPosition.equals("")) {
			wvParkingPlace.setCurrentItem(1);

			mCurrentDate.setText("주차 등록하지 않았습니다.");

			Toast.makeText(this, "주차 위치를 선택하세요.", Toast.LENGTH_SHORT).show();
		} else {
			String tempRegisterTime = pref.getValue(Constants.PREF_REGISTER_TIME, "");
			Long startTime = Long.parseLong(tempRegisterTime);

			//Log.d(Constants.TAG, "ParkingRegisterActivity.onCreate().startTime : " + startTime);

			// 버튼을 통해 등록하지 않았다면 마지막으로 등록한 시간 변경하지 않음(SharedPreference 에 마지막 등록한 시간 변경 안 함)
			registerTimeInterval = timeCal.getTimeIntervalCalculater(startTime, Constants.NO_CHANGE_TIME);

			mCurrentDate.setText(registerTimeInterval);
		}

		if (getParkingPosition.equals(Constants.PARKING_B2)) {
			wvParkingPlace.setCurrentItem(0);
		} else if (getParkingPosition.equals(Constants.PARKING_B3)) {
			wvParkingPlace.setCurrentItem(1);
		} else if (getParkingPosition.equals(Constants.PARKING_B4)) {
			wvParkingPlace.setCurrentItem(2);
		} 
	}

	@Override
	protected void onPause() {
		super.onPause();

		Log.d(Constants.TAG, "ParkingRegisterActivity.onPause()");	
	}
}