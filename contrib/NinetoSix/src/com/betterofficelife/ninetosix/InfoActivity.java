package com.betterofficelife.ninetosix;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class InfoActivity extends Activity {

	Button m_info_close_btn;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);//DinnerMenuActivity의 타이틀바제거

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		m_info_close_btn = (Button)findViewById(R.id.btn_info_close);
	
		//제품정보화면에서 '닫기'버튼 눌렀을 시 이동 
		m_info_close_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onBackPressed();
				finish();
			}
		});

	}
}
