package com.betterofficelife.ninetosix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.betterofficelife.constants.DinnerMenuRowItem;

public class DinnerMenuActivity extends Activity {
	public String dinner_room_name;
	public int dinner_room_img2;
	Class parent = DinnerRoomActivity.class;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Intent intent = getIntent();
		dinner_room_name = intent.getExtras().getString("dinner_room_name");
		dinner_room_img2 = intent.getExtras().getInt("dinner_room_img2");

		requestWindowFeature(Window.FEATURE_NO_TITLE);//DinnerMenuActivity의 타이틀바제거

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dinner_menu);

		ArrayAdapter<DinnerMenuRowItem> adapter;
		ListView dinner_menu_listview = (ListView) findViewById(R.id.dinner_menu_list);
		ImageView dinner_menu_listview_img = (ImageView)findViewById(R.id.dinner_menu_list_img);
		
		TextView tv_dinner_room_name = (TextView) findViewById(R.id.title_dinner_room_name);
		ImageView img_dinner_room_img2 = (ImageView)findViewById(R.id.dinner_room_img2);

		Button btn_menu_confirm = (Button)findViewById(R.id.btn_menu_confirm);

		tv_dinner_room_name.setText(dinner_room_name);

		DinnerMenuRowItem[] BudaeItems = { 
				new DinnerMenuRowItem("부대찌개"), 
				new DinnerMenuRowItem("두루치기"), 
		};


		DinnerMenuRowItem[] SundaeItems = { 
				new DinnerMenuRowItem("순대국밥"), 
				new DinnerMenuRowItem("뼈다귀해장국"), 
		};

		DinnerMenuRowItem[] JincookItems = { 
				new DinnerMenuRowItem("수육국밥")
		};

		DinnerMenuRowItem[] PhocurryItems = { 
				new DinnerMenuRowItem("돈까스커리"), 
				new DinnerMenuRowItem("오므라이스"), 
				new DinnerMenuRowItem("양지칼국수")
		};

		DinnerMenuRowItem[] RotutuItems = { 
				new DinnerMenuRowItem("햄에그 샌드위치 + 음료"), 
				new DinnerMenuRowItem("스테이크 샌드위치"), 
				new DinnerMenuRowItem("베이컨 샌드위치")
		};

		DinnerMenuRowItem[] ShingisoItems = { 
				new DinnerMenuRowItem("가츠돈"), 
				new DinnerMenuRowItem("김치 가츠돈"), 
				new DinnerMenuRowItem("우동"),
				new DinnerMenuRowItem("알밥"), 
				new DinnerMenuRowItem("김치우동"), 
				new DinnerMenuRowItem("회덮밥")
		};


		DinnerMenuRowItem[] KimbabItems = { 
		};

		if(dinner_room_name.equals("369부대찌개")) {
			img_dinner_room_img2.setBackgroundResource(R.drawable.img_budae_logo);
			dinner_menu_listview_img.setVisibility(View.GONE);
			
			adapter = new ArrayAdapter<DinnerMenuRowItem>(this,
					R.layout.dinner_menu_list_item, BudaeItems);
			dinner_menu_listview.setAdapter(adapter);
		}

		if(dinner_room_name.equals("병천순대")) {
			img_dinner_room_img2.setBackgroundResource(R.drawable.img_sundae);
			dinner_menu_listview_img.setVisibility(View.GONE);
			
			adapter = new ArrayAdapter<DinnerMenuRowItem>(this,
					R.layout.dinner_menu_list_item, SundaeItems);
			dinner_menu_listview.setAdapter(adapter);
		}

		if(dinner_room_name.equals("진국")) {
			img_dinner_room_img2.setBackgroundResource(R.drawable.img_jincook);
			dinner_menu_listview_img.setVisibility(View.GONE);
			
			adapter = new ArrayAdapter<DinnerMenuRowItem>(this,
					R.layout.dinner_menu_list_item,JincookItems );
			dinner_menu_listview.setAdapter(adapter);
		}

		if(dinner_room_name.equals("포커리")) {
			img_dinner_room_img2.setBackgroundResource(R.drawable.img_phocurry);
			dinner_menu_listview_img.setVisibility(View.GONE);
			
			adapter = new ArrayAdapter<DinnerMenuRowItem>(this,
					R.layout.dinner_menu_list_item, PhocurryItems);
			dinner_menu_listview.setAdapter(adapter);

		}

		if(dinner_room_name.equals("로뚜뚜커피")) {
			img_dinner_room_img2.setBackgroundResource(R.drawable.img_rotutu);
			dinner_menu_listview_img.setVisibility(View.GONE);
			
			
			adapter = new ArrayAdapter<DinnerMenuRowItem>(this,
					R.layout.dinner_menu_list_item, RotutuItems);
			dinner_menu_listview.setAdapter(adapter);
		}
		if(dinner_room_name.equals("신기소")) {
			img_dinner_room_img2.setBackgroundResource(R.drawable.img_singiso);
			dinner_menu_listview_img.setVisibility(View.GONE);
			
			adapter = new ArrayAdapter<DinnerMenuRowItem>(this,
					R.layout.dinner_menu_list_item, ShingisoItems);
			dinner_menu_listview.setAdapter(adapter);
		}

		if(dinner_room_name.equals("김밥천국")) {
			img_dinner_room_img2.setBackgroundResource(R.drawable.img_kimbab);
			adapter = new ArrayAdapter<DinnerMenuRowItem>(this,
					R.layout.dinner_menu_list_item, KimbabItems);
			dinner_menu_listview.setAdapter(adapter);
		
		}



		//확인 버튼 클릭 시,해당 액티비티 close
		btn_menu_confirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("onclick","onono");
				onBackPressed();
			}
		});
	}

	public void onBackPressed(){
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		finish();
	}
}
