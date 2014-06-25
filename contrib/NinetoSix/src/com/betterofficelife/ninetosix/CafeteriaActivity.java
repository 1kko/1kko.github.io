package com.betterofficelife.ninetosix;


import java.util.ArrayList;
import java.util.List;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ListView;
import android.widget.TabHost;

import com.betterofficelife.adapter.CoffeeOrderedCustomListViewAdapter;
import com.betterofficelife.constants.CoffeeOrderedRowItem;
import com.betterofficelife.constants.Constants;
import com.betterofficelife.ninetosix.coffeeorder.tabs.Bread;
import com.betterofficelife.ninetosix.coffeeorder.tabs.Coffee;
import com.betterofficelife.ninetosix.coffeeorder.tabs.Drink;
import com.betterofficelife.ninetosix.coffeeorder.tabs.Order;
import com.betterofficelife.ninetosix.coffeeorder.tabs.Tea;




@SuppressWarnings("deprecation")
public class CafeteriaActivity extends TabActivity {

	ListView listView;
	List<CoffeeOrderedRowItem> rowItems;

	CoffeeOrderedCustomListViewAdapter  listViewAdapater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//단말별 해상도 맞추기
		DisplayMetrics displayMatrics = new DisplayMetrics();
		Constants.width= getWindowManager().getDefaultDisplay().getWidth();
		Constants.height = getWindowManager().getDefaultDisplay().getHeight();

		if(Constants.height == 800) { //Galaxy S2
			Log.d("DeviceResolution", "480 x 800 : 갤럭시S, 갤럭시S2, 옵티머스 2X, Nexus S, Nexus One, HTC Desire HD, HTC Desire HD2");
			setContentView(R.layout.activity_coffee_order_480x800);
		}

		else if (Constants.width == 800 && Constants.height ==1280) {
			Log.d("DeviceResolution", "800 x 1280 : 800 x 1280 : 갤럭시탭 10.1, 갤럭시노트1, 넥서스 7");
			setContentView(R.layout.activity_coffee_order);
		}
		else if (Constants.width == 720 && Constants.height ==1280) {
			Log.d("DeviceResolution", "720 x 1280 : 갤럭시S3, 갤럭시S2 HD, 갤럭시노트2, 옵티머스G");
			setContentView(R.layout.activity_coffee_order);
		}
		else if (Constants.width == 720 && Constants.height== 1184) { //Galaxy Nexus
			Log.d("DeviceResolution", "720 x 1184 : 갤럭시 넥서스");
			setContentView(R.layout.activity_coffee_order);
		}

		else {
			setContentView(R.layout.activity_coffee_order);
		}

		TabHost mTab = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;
		final String TAB_ORDER= "Order";
		rowItems = new ArrayList<CoffeeOrderedRowItem>();

		intent = new Intent(this, Coffee.class);
		spec = mTab.newTabSpec("Coffee").setIndicator("커피").setContent(intent);
		mTab.addTab(spec);

		intent = new Intent(this, Tea.class);
		spec = mTab.newTabSpec("Tea").setIndicator("차").setContent(intent);
		mTab.addTab(spec);

		intent = new Intent(this, Drink.class);
		spec = mTab.newTabSpec("Drink").setIndicator("음료").setContent(intent);
		mTab.addTab(spec);

		intent = new Intent(this, Bread.class);
		spec = mTab.newTabSpec("Bread").setIndicator("빵").setContent(intent);
		mTab.addTab(spec);

		intent = new Intent(this, Order.class);
		spec = mTab
				.newTabSpec(TAB_ORDER)
				.setIndicator("주문")
				.setContent(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
		mTab.addTab(spec);
	}
}