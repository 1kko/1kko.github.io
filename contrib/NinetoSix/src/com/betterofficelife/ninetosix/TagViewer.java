package com.betterofficelife.ninetosix;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.betterofficelife.constants.Constants;
import com.betterofficelife.ninetosix.wheel.widget.ArrayWheelAdapter;
import com.betterofficelife.ninetosix.wheel.widget.WheelView;


@SuppressLint("NewApi")
public class TagViewer extends Activity {
	private NfcAdapter mNfcAdapter;
	private PendingIntent mPendingintent;
	private IntentFilter[] mIntentFilters;
	private String[][] mNFCTechList;
	private String autoParkingPlace[];
	private WheelView wvAutoParkingPlace;

	static final int ACTIVITY_TIMEOUT_MS = 1 * 1000;
	static final String CHARS = "0123456789ABCDEF";

	TextView tagId;
	String getParkingPosition = "";
	String initialTime = "";
	String registerTimeInterval = "";
	
	ParkingPreference pref = new ParkingPreference(this);
	TimeIntervalCalculater timeCal = new TimeIntervalCalculater(this);
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.d(Constants.TAG, "TagViewer.onCreate()");

		//단말별 해상도 맞추기
		DisplayMetrics displayMatrics = new DisplayMetrics();
		Constants.width= getWindowManager().getDefaultDisplay().getWidth();
		Constants.height = getWindowManager().getDefaultDisplay().getHeight();

		if(Constants.height == 800) { //Galaxy S2
			Log.d("DeviceResolution", "480 x 800 : 갤럭시S, 갤럭시S2, 옵티머스 2X, Nexus S, Nexus One, HTC Desire HD, HTC Desire HD2");
			setContentView(R.layout.tag_viewer_480x800);
			WheelView.TEXT_SIZE = 80;
		}

		//2 - 800 x 1280
		else if ( Constants.width == 800 && Constants.height == 1280) { 
			Log.d("DeviceResolution", "800 x 1280 : 800 x 1280 : 갤럭시탭 10.1, 갤럭시노트1, 넥서스 7");
			setContentView(R.layout.tag_viewer_800x1280);
			WheelView.TEXT_SIZE = 110;
		}
		else if (Constants.width == 720 && Constants.height== 1184) { 
			Log.d("DeviceResolution", "720 x 1184 : 갤럭시 넥서스");
			setContentView(R.layout.tag_viewer_720x1280);
			WheelView.TEXT_SIZE = 110;
		}
		else if (Constants.width == 720 && Constants.height ==1280) {
			Log.d("DeviceResolution", "720 x 1280 : 갤럭시S3, 갤럭시S2 HD, 갤럭시노트2, 옵티머스G");
			setContentView(R.layout.tag_viewer_720x1280);
			WheelView.TEXT_SIZE = 110;
		}
		//5 - 1080 x 1920
		else if (Constants.width == 1080 && Constants.height == 1920) {
			Log.d("DeviceResolution", "1080 x 1920 : G2, 갤럭시S4, 갤럭시노트3");
			setContentView(R.layout.tag_viewer_1080x1920);
			WheelView.TEXT_SIZE = 180;
		}
		else {
			Log.d("DeviceResolution", "else");

			setContentView(R.layout.tag_viewer);
		}

		tagId = (TextView) findViewById(R.id.tagId);
		wvAutoParkingPlace = (WheelView) findViewById(R.id.autoParkingPlace);

		autoParkingPlace = new String[] {"B2", "B3", "B4"};

		wvAutoParkingPlace.setVisibleItems(3);
		wvAutoParkingPlace.setAdapter(new ArrayWheelAdapter<String>(autoParkingPlace));

		// Default NfcAdaper 객체 가져올 때 사용, NFC 지원하지 않는 단말에서는 null return
		mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

		initialTime = pref.getValue(Constants.PREF_INITIAL_TIME, "");
		registerTimeInterval = pref.getValue(Constants.PREF_REGISTER_TIME_INTERVAL, "");
		
		// 태그를 계속 인식할 때 시로운 Activity 만들지 않고 현재 Activity 에서 Intent 받기 위힘
		mPendingintent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

		IntentFilter ndefIntent = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);

		try {
			// 모든 mime 을 전달 받음
			ndefIntent.addDataType("text/*");
			mIntentFilters = new IntentFilter[] { ndefIntent };
		} catch(Exception e) {
			Log.e("TagDispatch", e.toString());
		}

		// 앱이 핸들링하고 싶은 태그 기술들의 배열 설정
		mNFCTechList = new String[][] { new String[] { NfcF.class.getName() } };

		resolveIntent(getIntent());
	}

	@SuppressLint("NewApi")
	void resolveIntent(Intent intent) {
		Log.d(Constants.TAG, "TagViewer.resolveIntent()");

		String action = intent.getAction();

		// Intent 로부터 Tag 객체를 덩음, 이 객체에는 페이로드가 포함되어 있으며 태그의 기술들을 확인 가능
		Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

		String intentDebug = action + "\n\n" + tag.toString();
		String textMessage = "";

		// NDEF 메시지를 parse 하여 record 중 text type 만 가져와 textMessage 에 저장
		Parcelable[] data = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

		if(data != null) { 
			try {
				for(int i = 0; i < data.length; i++) {
					NdefRecord [] recs = ((NdefMessage)data[i]).getRecords();

					for(int j = 0; j < recs.length; j++) { 
						if(recs[j].getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(recs[j].getType(), NdefRecord.RTD_TEXT)) {
							byte[] payload = recs[j].getPayload();

							String textEncoding = ((payload[0] & 0200) == 0) ? "UTF-8" : "UTF-16";

							int langCodeLen = payload[0] & 0077;

							intentDebug += ("\n\nNdefMessage[" + i + "], NdefRecord[" + j + "]:=\n\"" + new String(payload, langCodeLen + 1, payload.length - langCodeLen - 1, textEncoding) + "\"");
							textMessage += new String(payload, langCodeLen + 1, payload.length - langCodeLen - 1, textEncoding);
						}
					}
				}
			} catch(Exception e) { 
				Log.e("TagDispatch", e.toString());
			}
		}

		if (textMessage.equals(Constants.PARKING_B2)) {
			wvAutoParkingPlace.setCurrentItem(0);

			Toast.makeText(this, "B2 층에 주차되었습니다.", Toast.LENGTH_SHORT).show();

			pref.put(Constants.PREF_PARKING_POSITION, Constants.PARKING_B2);	
			//pref.put(Constants.PREF_TIME, dTime);

			getParkingPosition = pref.getValue(Constants.PREF_PARKING_POSITION, "");
			Log.d(Constants.TAG, "TagViewer.resolveIntent().getParkingPosition : " + getParkingPosition);
			
			// if : 자동 등록으로 최초 태그했을 때 / else : 수동 또는 자동으로 등록  한 번 이후 부터 적용 
			if (initialTime.equals("")) {
				Log.d(Constants.TAG, "TagViewer.resolveIntent().B2.ONCE");

				SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
				Date currentTime = new Date ( );
				initialTime = formatter.format ( currentTime );

				pref.put(Constants.PREF_INITIAL_TIME, initialTime);
				registerTimeInterval = timeCal.getTimeIntervalCalculater(initialTime);

				Log.d(Constants.TAG, "TagViewer.resolveIntent().B2.initialTime : " + initialTime);
				Log.d(Constants.TAG, "TagViewer.resolveIntent().B2.initial.registerTimeInterval : " + registerTimeInterval);

			} else {
				Log.d(Constants.TAG, "TagViewer.resolveIntent().B2.MORE_THAN_ONCE");
				
				String tempRegisterTime = pref.getValue(Constants.PREF_REGISTER_TIME, "");
				Long registerTime = Long.parseLong(tempRegisterTime);

				Log.d(Constants.TAG, "TagViewer.resolveIntent().B2.startTime : " + registerTime);
				
				// 버튼을 통해 등록할 때에만 마지막 등록 시간 변경(SharedPreferences 에 마지막 등록한 시간 변경)
				registerTimeInterval = timeCal.getTimeIntervalCalculater(registerTime,  Constants.CHANGE_TIME);
				
				Log.d(Constants.TAG, "TagViewer.resolveIntent().B2.MORE_THAN_ONCE.registerTimeInterval : " + registerTimeInterval);
			}
			
			pref.put(Constants.PREF_REGISTER_TIME_INTERVAL, registerTimeInterval);
		} else if (textMessage.equals(Constants.PARKING_B3)) {
			wvAutoParkingPlace.setCurrentItem(1);

			Toast.makeText(this, "B3 층에 주차되었습니다.", Toast.LENGTH_SHORT).show();

			pref.put(Constants.PREF_PARKING_POSITION, Constants.PARKING_B3);
			//pref.put(Constants.PREF_TIME, dTime);

			getParkingPosition = pref.getValue(Constants.PREF_PARKING_POSITION, "");
			Log.d(Constants.TAG, "TagViewer.resolveIntent().getParkingPosition : " + getParkingPosition);
			
			// if : 자동 등록으로 최초 태그했을 때 / else : 수동 또는 자동으로 등록  한 번 이후 부터 적용 
			if (initialTime.equals("")) {
				Log.d(Constants.TAG, "TagViewer.resolveIntent().B3.ONCE");

				SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
				Date currentTime = new Date ( );
				initialTime = formatter.format ( currentTime );

				pref.put(Constants.PREF_INITIAL_TIME, initialTime);
				registerTimeInterval = timeCal.getTimeIntervalCalculater(initialTime);

				Log.d(Constants.TAG, "TagViewer.resolveIntent().B3.initialTime : " + initialTime);
				Log.d(Constants.TAG, "TagViewer.resolveIntent().B3.initial.registerTimeInterval : " + registerTimeInterval);

			} else {
				Log.d(Constants.TAG, "TagViewer.resolveIntent().B3.MORE_THAN_ONCE");
				
				String tempRegisterTime = pref.getValue(Constants.PREF_REGISTER_TIME, "");
				Long registerTime = Long.parseLong(tempRegisterTime);

				Log.d(Constants.TAG, "TagViewer.resolveIntent().B3.startTime : " + registerTime);
				
				// 버튼을 통해 등록할 때에만 마지막 등록 시간 변경(SharedPreferences 에 마지막 등록한 시간 변경)
				registerTimeInterval = timeCal.getTimeIntervalCalculater(registerTime,  Constants.CHANGE_TIME);
				
				Log.d(Constants.TAG, "TagViewer.resolveIntent().B3.MORE_THAN_ONCE.registerTimeInterval : " + registerTimeInterval);
			}
			
			pref.put(Constants.PREF_REGISTER_TIME_INTERVAL, registerTimeInterval);
		} else if (textMessage.equals(Constants.PARKING_B4)) {
			wvAutoParkingPlace.setCurrentItem(2);

			Toast.makeText(this, "B4 층에 주차되었습니다.", Toast.LENGTH_SHORT).show();

			pref.put(Constants.PREF_PARKING_POSITION, Constants.PARKING_B4);
			//pref.put(Constants.PREF_TIME, dTime);

			getParkingPosition = pref.getValue(Constants.PREF_PARKING_POSITION, "");
			Log.d(Constants.TAG, "TagViewer.resolveIntent().getParkingPosition : " + getParkingPosition);
			
			// if : 자동 등록으로 최초 태그했을 때 / else : 수동 또는 자동으로 등록  한 번 이후 부터 적용 
			if (initialTime.equals("")) {
				Log.d(Constants.TAG, "TagViewer.resolveIntent().B4.ONCE");

				SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
				Date currentTime = new Date ( );
				initialTime = formatter.format ( currentTime );

				pref.put(Constants.PREF_INITIAL_TIME, initialTime);
				registerTimeInterval = timeCal.getTimeIntervalCalculater(initialTime);

				Log.d(Constants.TAG, "TagViewer.resolveIntent().B4.initialTime : " + initialTime);
				Log.d(Constants.TAG, "TagViewer.resolveIntent().B4.initial.registerTimeInterval : " + registerTimeInterval);

			} else {
				Log.d(Constants.TAG, "TagViewer.resolveIntent().B4.MORE_THAN_ONCE");
				
				String tempRegisterTime = pref.getValue(Constants.PREF_REGISTER_TIME, "");
				Long registerTime = Long.parseLong(tempRegisterTime);

				Log.d(Constants.TAG, "TagViewer.resolveIntent().B4.startTime : " + registerTime);
				
				// 버튼을 통해 등록할 때에만 마지막 등록 시간 변경(SharedPreferences 에 마지막 등록한 시간 변경)
				registerTimeInterval = timeCal.getTimeIntervalCalculater(registerTime,  Constants.CHANGE_TIME);
				
				Log.d(Constants.TAG, "TagViewer.resolveIntent().B4.MORE_THAN_ONCE.registerTimeInterval : " + registerTimeInterval);
			}
			
			pref.put(Constants.PREF_REGISTER_TIME_INTERVAL, registerTimeInterval);
		}  else {
			setContentView(R.layout.none_tag);

			//findViewById(R.id.autoParkingPlace).setVisibility(View.GONE);
			//findViewById(R.id.tagId).setVisibility(View.VISIBLE);
		}

		//3초 후에 해당 액티비티 종료 
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				TagViewer.this.finish();
			}
		}, 2000) ;

		/*if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
			Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
			NdefMessage[] msgs;

			if (rawMsgs != null) {
				msgs = new NdefMessage[rawMsgs.length];

				for (int i = 0; i < rawMsgs.length; i++) {
					msgs[i] = (NdefMessage) rawMsgs[i];
				}

				Log.d(Constants.TAG, "TagViewer.resolveIntent().msgs" + msgs);
			} else {
				// Unknown tag type
				byte[] empty = new byte[] {};
				NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty);
				NdefMessage msg = new NdefMessage(new NdefRecord[] {record});
				msgs = new NdefMessage[] {msg};
			}

			Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

			if (tag != null) {
				byte[] tagId = tag.getId();

				if (toHexString(tagId).equals(b1)) {
					tagDesc.setText(Constants.PARKING_B1);

					Toast.makeText(this, mCurrentDate.getText() + " "+"B1에 주차되었습니다.", Toast.LENGTH_SHORT).show();

					pref.put(Constants.PREF_PARKING_POSITION, Constants.PARKING_B1);	

					AlarmRegister.initiallizeParkingPosition(this);
				} else if (toHexString(tagId).equals(b2)) {
					tagDesc.setText(Constants.PARKING_B2);

					Toast.makeText(this, mCurrentDate.getText() + " "+"B2에 주차되었습니다.", Toast.LENGTH_SHORT).show();

					pref.put(Constants.PREF_PARKING_POSITION, Constants.PARKING_B2);

					AlarmRegister.initiallizeParkingPosition(this);
				} else if (toHexString(tagId).equals(b3)) {
					tagDesc.setText(Constants.PARKING_B3);

					Toast.makeText(this, mCurrentDate.getText() + " "+"B3에 주차되었습니다.", Toast.LENGTH_SHORT).show();

					pref.put(Constants.PREF_PARKING_POSITION, Constants.PARKING_B3);

					AlarmRegister.initiallizeParkingPosition(this);
				} else if (toHexString(tagId).equals(b4)) {
					tagDesc.setText(Constants.PARKING_B4);

					Toast.makeText(this, mCurrentDate.getText() + " "+"B4에 주차되었습니다.", Toast.LENGTH_SHORT).show();

					pref.put(Constants.PREF_PARKING_POSITION, Constants.PARKING_B4);

					AlarmRegister.initiallizeParkingPosition(this);
				} else {
					Toast.makeText(this, "해당하는 주차 위치가 없습니다.", Toast.LENGTH_LONG).show();
				}

				//2초 후에 해당 액티비티 종료 
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						TagViewer.this.finish();
					}
				}, 3000) ;
			}
		} else {
			Log.e(TAG, "Unknown intent " + intent);

			finish();

			return;
		}*/
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onNewIntent(android.content.Intent)
	 * 태그가 인식되면
	 */
	@Override
	public void onNewIntent(Intent intent) {
		Log.d(Constants.TAG, "TagViewer.onNewIntent()");

		resolveIntent(intent);
	}

	/*
	 * byte[] data 를 String 으로 변환
	 */
	public static String toHexString(byte[] data) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < data.length; ++i) {
			sb.append(CHARS.charAt((data[i] >> 4) & 0x0F))
			.append(CHARS.charAt(data[i] & 0x0F));
		}

		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 * Activity 가 화면에 보이고 있을 때에만 NFC 태그 인식 위해 onReusme(), onPuase() 사용
	 * 하지만 onCreate 에서 이미 resolveIntent 로 넘어가기 떄문에 onResume(), onPuase(), onNewIntent() 불리지 않음
	 */
	@SuppressLint("NewApi")
	public void onResume() {
		super.onResume();

		Log.d(Constants.TAG, "TagViewer.onResume()");

		if(mNfcAdapter != null)
			mNfcAdapter.enableForegroundDispatch(this, mPendingintent, mIntentFilters, mNFCTechList);
	}

	public void onPause() { 
		super.onPause();

		Log.d(Constants.TAG, "TagViewer.onPause()");

		if(mNfcAdapter != null)
			mNfcAdapter.disableForegroundDispatch(this);
	}
}