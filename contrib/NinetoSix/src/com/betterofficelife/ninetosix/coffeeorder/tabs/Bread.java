package com.betterofficelife.ninetosix.coffeeorder.tabs;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;

import com.betterofficelife.adapter.CoffeeMenuCustomListViewAdapter;
import com.betterofficelife.constants.CoffeeMenuCounts;
import com.betterofficelife.constants.CoffeeMenuRowItem;
import com.betterofficelife.ninetosix.R;


public class Bread extends Activity{
	public static final String[] names = new String[] { "벨기에와플",
		"크림치즈프레즐", "초코칩머핀","블루베리머핀", "크로와상" , "호두브라우니"};


	ListView listView;
	List<CoffeeMenuRowItem> rowItems;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coffee_menu_info);
		String activityName = getClass().getSimpleName();

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
		}  return super.onKeyDown(keyCode, event);
	}
}
