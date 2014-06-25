package com.betterofficelife.ninetosix;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.betterofficelife.adapter.DinnerRoomCustomListViewAdapter;
import com.betterofficelife.constants.DinnerRoomRowItem;

public class DinnerRoomActivity extends Activity {
	public static final String[] names = new String[] { 
		"369부대찌개","병천순대", "진국",
		"포커리","로뚜뚜커피",
		"신기소",
	    "김밥천국"};

	public static final String[] main_dishes = new String[] {
		"부대찌개, 두루치기", "순대국밥, 뼈다귀해장국","수육국밥",
		"돈까스커리, 오므라이스, 양지 쌀국수","세트 : 햄 에그 샌드위치 + 음료"+"\n"+"단품 : 스테이크, 베이컨 샌드위치",
		"가쓰돈, 김치가쓰돈, 우동,"+"\n"+"알밥, 김치우동, 회덮밥", 
	"분 식 류"};

	public static final String[] prices = new String[] {
		"6,000원", "6,000원", "6,000원",
		"6,000원","6,000원",
		"6,000원",
	"6,000원"};
	public static final String[] refs = new String[] {
		"2인 이상", " ", "24시 운영",
		"할인 없음","20분 단위 배달",
		"",
		"한도내 실비청구"+"\n"+"할인 없음"};
	public static final String[] locations = new String[] {
		"경기도 성남시 분당구 삼평동 679번지 삼환하이펙스 B동 204호","경기도 성남시 분당구 삼평동 680번지 H스퀘어 S동 115호","경기도 성남시 분당구 삼평동 680번지 H스퀘어 S동 121호",
		"경기도 성남시 분당구 삼평동 680번지 H스퀘어 S동 123호","경기도 성남시 분당구 삼평동 680번지",
		"경기도 성남시 분당구 삼평동 680번지 H스퀘어 지하 1층",
	"경기도 성남시 분당구 삼평동 680번지 H스퀘어 S동 지하 1층"};

	public static final double[] longitude = new double[] {
		127.1105175, 127.1086497, 127.1086497,
		127.1092964,127.1086497,
		127.1086497,
		127.1086497};
	public static final double[] latitude = new double[] {
		37.4013666, 37.4014414,  37.4014414 ,
		37.4014204, 37.4014414,
		37.4014414,
		37.4014414};

	public static final String[] callings = new String[] {
		"031-696-0300","031-789-3502","031-789-3555",
		"031-789-3514","031-696-7455",
		"031-789-3737", 
	"031-696-7599"};


	public static final Integer[] images = { 
		R.drawable.img_budae_logo,
		R.drawable.img_sundae,
		R.drawable.img_jincook,
		R.drawable.img_phocurry, 
		R.drawable.img_rotutu,
		R.drawable.img_singiso,
		R.drawable.img_kimbab};

	ListView listView;
	List<DinnerRoomRowItem> rowItems;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.dinner_room_info);
		String activityName = getClass().getSimpleName();

		/*TextView tv = new TextView(this);
		tv.setText(activityName + "한식");
		setContentView(tv);*/

		rowItems = new ArrayList<DinnerRoomRowItem>();
		for (int i = 0; i < names.length; i++) {
			DinnerRoomRowItem item = new DinnerRoomRowItem(images[i], names[i], main_dishes[i],
					prices[i], refs[i], locations[i],longitude[i],latitude[i], callings[i]);
			rowItems.add(item);
		}


		listView = (ListView) findViewById(R.id.list);
		DinnerRoomCustomListViewAdapter adapter = new DinnerRoomCustomListViewAdapter(this,
				R.layout.dinner_room_info_item, rowItems);
		listView.setAdapter(adapter);

	}


}
