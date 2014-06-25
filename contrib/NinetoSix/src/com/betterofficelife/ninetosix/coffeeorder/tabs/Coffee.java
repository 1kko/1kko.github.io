package com.betterofficelife.ninetosix.coffeeorder.tabs;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ListView;

import com.betterofficelife.adapter.CoffeeMenuCustomListViewAdapter;
import com.betterofficelife.constants.CoffeeMenuCounts;
import com.betterofficelife.constants.CoffeeMenuRowItem;
import com.betterofficelife.constants.Constants;
import com.betterofficelife.ninetosix.R;

public class Coffee extends Activity{
	public static final String[] names = new String[] { "에스프레소",
		"카푸치노", "아메리카노", "카라멜마끼아또", "카페모카", "바닐라라떼", "카페라떼", "녹차라떼", "고구마라떼"};


	ListView listView;
	List<CoffeeMenuRowItem> rowItems;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//단말별 해상도 맞추기
		DisplayMetrics displayMatrics = new DisplayMetrics();
		Constants.width= getWindowManager().getDefaultDisplay().getWidth();
		Constants.height = getWindowManager().getDefaultDisplay().getHeight();

		if(Constants.height == 800) { //Galaxy S2
			Log.d("[Coffee]DeviceResolution", "480 x 800 : 갤럭시S, 갤럭시S2, 옵티머스 2X, Nexus S, Nexus One, HTC Desire HD, HTC Desire HD2");
			setContentView(R.layout.coffee_menu_info_480x800);
		}

		else if (Constants.width == 800 && Constants.height ==1280) {
			Log.d("DeviceResolution", "800 x 1280 : 800 x 1280 : 갤럭시탭 10.1, 갤럭시노트1, 넥서스 7");
			setContentView(R.layout.coffee_menu_info_800x1280);
		}
		else if (Constants.width == 720 && Constants.height ==1280) {
			Log.d("DeviceResolution", "720 x 1280 : 갤럭시S3, 갤럭시S2 HD, 갤럭시노트2, 옵티머스G");
			setContentView(R.layout.coffee_menu_info_720x1280);
		}
		else if (Constants.width == 720 && Constants.height== 1184) { //Galaxy Nexus
			Log.d("DeviceResolution", "720 x 1184 : 갤럭시 넥서스");
			setContentView(R.layout.coffee_menu_info_720x1280);
		}
		else {
			setContentView(R.layout.coffee_menu_info);
		}



		String activityName = getClass().getSimpleName();

		/*TextView tv = new TextView(this);
		tv.setText(activityName + "한식");
		setContentView(tv);*/

		rowItems = new ArrayList<CoffeeMenuRowItem>();
		for (int i = 0; i < names.length; i++) {
			CoffeeMenuRowItem item = new CoffeeMenuRowItem(names[i]);
			rowItems.add(item);
		}

		listView = (ListView) findViewById(R.id.menuList);


		CoffeeMenuCustomListViewAdapter adapter = new CoffeeMenuCustomListViewAdapter(this,
				R.layout.coffee_menu_info_item, rowItems);
		listView.setAdapter(adapter);


	}


	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK ){
			CoffeeMenuCounts.InitiateCoffeeMenuCounts();
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
