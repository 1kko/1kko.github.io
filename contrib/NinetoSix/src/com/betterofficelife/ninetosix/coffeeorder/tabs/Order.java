package com.betterofficelife.ninetosix.coffeeorder.tabs;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.betterofficelife.adapter.CoffeeOrderedCustomListViewAdapter;
import com.betterofficelife.constants.CoffeeMenuCounts;
import com.betterofficelife.constants.CoffeeMenuLists;
import com.betterofficelife.constants.CoffeeOrderedRowItem;
import com.betterofficelife.constants.Constants;
import com.betterofficelife.ninetosix.R;

public class Order extends Activity {

	public static final String[] ordered_menu_list = new String[] {} ;
	public int i = 0 ;

	ListView listView;
	List<CoffeeOrderedRowItem> rowItems;

	TextView tv_total_count;
	TextView tv_emptyResults;
	Button btn_sharedapp;
	Button btn_initiate;

	String order_total = ""; // 총주문
	String espreso_total; // 에스프레소 총주문
	String cafu_total; // 카푸치노 총주문
	String ame_total; // 아메리카노 총주문
	String moca_total; // 카페모카 총주문
	String vali_total; // 바닐라라떼 총주문
	String latte_total; // 라떼 총주문
	String caramel_total; // 카라멜마끼야또 총주문
	String green_total; // 녹차라떼 총주문
	String sweetpotato_total;//고구마 라떼 총주

	String ulmu_total; // 율무차 총주문
	String uja_total; // 유자차 총주문
	String camo_total; // 카모마일 총주문
	String pepo_total; // 페퍼민트 총주문
	String ssanghwa_total; //쌍화차 총 주문
	String coke_total; // 체리콕 총주문
	String lemon_total; // 레몬에이드 총주문
	String orenge_total; // 오렌지쥬스 총주문
	String grape_total; // 체리콕 총주문
	String milk_total; // 레몬에이드 총주문
	String icetea_total; 

	String waple_total; // 와플 총주문
	String prezle_total; // 크림치즈 프레즐 총주문
	String muffin_total; // 초코칩머핀 총주문
	String bluebery_muffin_total; //블루베리 머핀 총주문
	String crowa_total; // 크로와상 총주문
	String prechel_total; // 프레첼 총주문
	String browni_total;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//단말별 해상도 맞추기
		DisplayMetrics displayMatrics = new DisplayMetrics();
		Constants.width= getWindowManager().getDefaultDisplay().getWidth();
		Constants.height = getWindowManager().getDefaultDisplay().getHeight();

		if(Constants.height == 800) { //Galaxy S2
			Log.d("[Order]DeviceResolution", "480 x 800 : 갤럭시S, 갤럭시S2, 옵티머스 2X, Nexus S, Nexus One, HTC Desire HD, HTC Desire HD2");
			setContentView(R.layout.coffee_ordered_info_480x800);
		}
		else if (Constants.width == 720 && Constants.height== 1184) { //Galaxy Nexus
			setContentView(R.layout.coffee_ordered_info_720x1280_galaxy_nexus);
		}
		//2 - 800 x 1280
		else if ( Constants.width == 800 && Constants.height == 1280) { 
			Log.d("DeviceResolution", "800 x 1280 : 800 x 1280 : 갤럭시탭 10.1, 갤럭시노트1, 넥서스 7");
			setContentView(R.layout.coffee_ordered_info_800x1280);
		}
		else if ( Constants.width == 720 && Constants.height == 1280) {
			Log.d("[Order]DeviceResolution", "720 x 1280 : 갤럭시S3, 갤럭시S2 HD, 갤럭시노트2, 옵티머스G");
			setContentView(R.layout.coffee_ordered_info_720x1280);
		}
		//5 - 1080 x 1920
		else if (Constants.width == 1080 && Constants.height == 1920) {
			Log.d("DeviceResolution", "1080 x 1920 : G2, 갤럭시S4, 갤럭시노트3");
			setContentView(R.layout.coffee_ordered_info_1080x1920);
		}
		else {
			setContentView(R.layout.coffee_ordered_info);
		}


		String activityName = getClass().getSimpleName();	
		rowItems = new ArrayList<CoffeeOrderedRowItem>();
		listView = (ListView) findViewById(R.id.orderedList);

		//tv_emptyResults = (TextView) findViewById(R.id.emptyResults);
		listView.setEmptyView(tv_emptyResults);

		tv_total_count = (TextView)findViewById(R.id.tv_total_count);
		btn_sharedapp = (Button)findViewById(R.id.sharedButton);
		//btn_initiate = (Button)findViewById(R.id.initiateButton);

		CheckCoffeeOrderList();


		CoffeeOrderedCustomListViewAdapter adapter = new CoffeeOrderedCustomListViewAdapter(this,
				R.layout.coffee_ordered_info_item, rowItems);
		listView.setAdapter(adapter);

		tv_total_count.setText(Integer.toString(CoffeeMenuCounts.total_count));
		/*	btn_initiate.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});*/


		btn_sharedapp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {		
				Intent sharingIntent = new Intent(
						android.content.Intent.ACTION_SEND);

				//esspresso
				if (CoffeeMenuCounts.espresso_hot_count!= 0) {
					espreso_total = CoffeeMenuLists.ESPRESSO_HOT
							+ (Integer.toString(CoffeeMenuCounts.espresso_hot_count)) + "잔\n";
					order_total += espreso_total;
				}

				//cappucino
				if (CoffeeMenuCounts.cappuccino_hot_count != 0) {
					cafu_total = CoffeeMenuLists.CAPPUCCINO_HOT
							+ (Integer.toString(CoffeeMenuCounts.cappuccino_hot_count)) + "잔\n";
					order_total += cafu_total;
				}
				if (CoffeeMenuCounts.cappuccino_ice_count != 0) {
					cafu_total = CoffeeMenuLists.CAPPUCCINO_ICE
							+ (Integer.toString(CoffeeMenuCounts.cappuccino_ice_count))
							+ "잔\n";
					order_total += cafu_total;
				}

				//americono
				if (CoffeeMenuCounts.americano_hot_count != 0) {
					ame_total = CoffeeMenuLists.AMERICANO_HOT
							+ (Integer.toString(CoffeeMenuCounts.americano_hot_count)) + "잔\n";
					order_total += ame_total;
				}
				if (CoffeeMenuCounts.americano_ice_count != 0) {
					ame_total = CoffeeMenuLists.AMERICANO_ICE
							+ (Integer.toString(CoffeeMenuCounts.americano_ice_count))
							+ "잔\n";
					order_total += ame_total;
				}

				//caramel
				if (CoffeeMenuCounts.caramelMacchiato_hot_count != 0) {
					caramel_total = CoffeeMenuLists.CARAMELMACCHIATO_HOT
							+ (Integer.toString(CoffeeMenuCounts.caramelMacchiato_hot_count)) + "잔\n";
					order_total += caramel_total;
				}
				if (CoffeeMenuCounts.caramelMacchiato_ice_count != 0) {
					caramel_total = CoffeeMenuLists.CARAMELMACCHIATO_ICE
							+ (Integer.toString(CoffeeMenuCounts.caramelMacchiato_ice_count))
							+ "잔\n";
					order_total += caramel_total;
				}

				//cafemoca
				if (CoffeeMenuCounts.cafeMoca_hot_count != 0) {
					moca_total = CoffeeMenuLists.CAFEMOCA_HOT
							+ (Integer.toString(CoffeeMenuCounts.cafeMoca_hot_count)) + "잔\n";
					order_total += moca_total;
				}
				if (CoffeeMenuCounts.cafeMoca_ice_count != 0) {
					moca_total = CoffeeMenuLists.CAFEMOCA_ICE
							+ (Integer.toString(CoffeeMenuCounts.cafeMoca_ice_count))
							+ "잔\n";
					order_total += moca_total;
				}
				//banilla latte
				if (CoffeeMenuCounts.banillaLatte_hot_count != 0) {
					vali_total = CoffeeMenuLists.BANILLALATEE_HOT
							+ (Integer.toString(CoffeeMenuCounts.banillaLatte_hot_count)) + "잔\n";
					order_total += vali_total;
				}
				if (CoffeeMenuCounts.banillaLatee_ice_count != 0) {
					vali_total = CoffeeMenuLists.BANILLALATEE_ICE
							+ (Integer.toString(CoffeeMenuCounts.banillaLatee_ice_count))
							+ "잔\n";
					order_total += vali_total;
				}
				//latte
				if (CoffeeMenuCounts.cafeLatte_hot_count != 0) {
					latte_total = CoffeeMenuLists.CAFELATTE_HOT
							+ (Integer.toString(CoffeeMenuCounts.cafeLatte_hot_count)) + "잔\n";
					order_total += latte_total;
				}
				if (CoffeeMenuCounts.cafeLatte_ice_count != 0) {
					latte_total = CoffeeMenuLists.CAFELATTE_ICE
							+ (Integer.toString(CoffeeMenuCounts.cafeLatte_ice_count))
							+ "잔\n";
					order_total += latte_total;
				}
				//greentea latte

				if (CoffeeMenuCounts.greenteaLatte_hot_count != 0) {
					green_total = CoffeeMenuLists.GREENTEALATTE_HOT
							+ (Integer.toString(CoffeeMenuCounts.greenteaLatte_hot_count)) + "잔\n";
					order_total += green_total;
				}
				if (CoffeeMenuCounts.greenteaLatte_ice_count != 0) {
					green_total = CoffeeMenuLists.GREENTEALATTE_ICE
							+ (Integer.toString(CoffeeMenuCounts.greenteaLatte_ice_count)) + "잔\n";
					order_total += green_total;
				}


				//sweetpotato latte

				if (CoffeeMenuCounts.sweetpoatatoLatte_hot_count != 0) {
					sweetpotato_total = CoffeeMenuLists.SWEETPOTATOLATTE_HOT
							+ (Integer.toString(CoffeeMenuCounts.sweetpoatatoLatte_hot_count)) + "잔\n";
					order_total += sweetpotato_total;
				}


				if (CoffeeMenuCounts.sweetpoatatoLatte_ice_count != 0) {
					sweetpotato_total = CoffeeMenuLists.SWEETPOTATOLATTE_ICE
							+ (Integer.toString(CoffeeMenuCounts.sweetpoatatoLatte_ice_count)) + "잔\n";
					order_total += sweetpotato_total;
				}


				//jobs
				if (CoffeeMenuCounts.jobstearsTea_hot_count != 0) {
					ulmu_total = CoffeeMenuLists.JOBSTEARSTEA_HOT
							+ (Integer.toString(CoffeeMenuCounts.jobstearsTea_hot_count)) + "잔\n";
					order_total += ulmu_total;
				}

				//citron
				if (CoffeeMenuCounts.citronTea_hot_count != 0) {
					uja_total = CoffeeMenuLists.CITRONTEA_HOT
							+ (Integer.toString(CoffeeMenuCounts.citronTea_hot_count)) + "잔\n";
					order_total += uja_total;
				}
				if (CoffeeMenuCounts.citronTea_ice_count != 0) {
					uja_total = CoffeeMenuLists.CITRONTEA_ICE
							+ (Integer.toString(CoffeeMenuCounts.citronTea_ice_count)) + "잔\n";
					order_total += uja_total;
				}


				//ssanghwa_total
				if (CoffeeMenuCounts.ssangwhaTea_hot_count != 0) {
					ssanghwa_total = CoffeeMenuLists.SSANGWHATEA_HOT
							+ (Integer.toString(CoffeeMenuCounts.ssangwhaTea_hot_count)) + "잔\n";
					order_total += ssanghwa_total;
				}

				if (CoffeeMenuCounts.ssangwhaTea_ice_count != 0) {
					ssanghwa_total = CoffeeMenuLists.SSANGWHATEA_ICE
							+ (Integer.toString(CoffeeMenuCounts.ssangwhaTea_ice_count)) + "잔\n";
					order_total += ssanghwa_total;
				}

				//chamomile
				if (CoffeeMenuCounts.chamomileTea_hot_count != 0) {
					camo_total = CoffeeMenuLists.CHAMOMILETEA_HOT
							+ (Integer.toString(CoffeeMenuCounts.chamomileTea_hot_count)) + "잔\n";
					order_total += camo_total;
				}
				if (CoffeeMenuCounts.chamomileTea_ice_count != 0) {
					camo_total = CoffeeMenuLists.CHAMOMILETEA_ICE
							+ (Integer.toString(CoffeeMenuCounts.chamomileTea_ice_count)) + "잔\n";
					order_total += camo_total;
				}

				//peppermint
				if (CoffeeMenuCounts.peppermintTea_hot_count != 0) {
					pepo_total = CoffeeMenuLists.PEPPERMINTTEA_HOT
							+ (Integer.toString(CoffeeMenuCounts.peppermintTea_hot_count)) + "잔\n";
					order_total += pepo_total;
				}
				if (CoffeeMenuCounts.peppermintTea_ice_count != 0) {
					camo_total = CoffeeMenuLists.PEPPERMINTTEA_ICE
							+ (Integer.toString(CoffeeMenuCounts.peppermintTea_ice_count)) + "잔\n";
					order_total += camo_total;
				}
				//cherrycock
				if (CoffeeMenuCounts.cherrycok_ice_count != 0) {
					coke_total = CoffeeMenuLists.CHERRYCOCK_ICE
							+ (Integer.toString(CoffeeMenuCounts.cherrycok_ice_count)) + "잔\n";
					order_total += coke_total;
				}
				//icetea
				if (CoffeeMenuCounts.icetea_ice_count != 0) {
					icetea_total = CoffeeMenuLists.ICETEA_ICE
							+ (Integer.toString(CoffeeMenuCounts.icetea_ice_count)) + "잔\n";
					order_total += icetea_total;
				}

				//lemonade
				if (CoffeeMenuCounts.remonace_ice_count != 0) {
					lemon_total = CoffeeMenuLists.REMONADE_ICE
							+ (Integer.toString(CoffeeMenuCounts.remonace_ice_count)) + "잔\n";
					order_total += lemon_total;
				}
				//orangejuice
				if (CoffeeMenuCounts.orangejuice_ice_count != 0) {
					orenge_total = CoffeeMenuLists.ORANGEJUICE_ICE
							+ (Integer.toString(CoffeeMenuCounts.orangejuice_ice_count)) + "잔\n";
					order_total += orenge_total;
				}
				//grape
				if (CoffeeMenuCounts.grapejuice_ice_count != 0) {
					grape_total = CoffeeMenuLists.GRAPEJUICE_ICE
							+ (Integer.toString(CoffeeMenuCounts.grapejuice_ice_count)) + "잔\n";
					order_total += grape_total;
				}
				//milk
				if (CoffeeMenuCounts.milk_hot_count != 0) {
					milk_total = CoffeeMenuLists.MILK_HOT
							+ (Integer.toString(CoffeeMenuCounts.milk_hot_count)) + "잔\n";
					order_total += milk_total;
				}
				if (CoffeeMenuCounts.milk_ice_count != 0) {
					milk_total = CoffeeMenuLists.MILK_ICE
							+ (Integer.toString(CoffeeMenuCounts.milk_ice_count)) + "잔\n";
					order_total += milk_total;
				}


				//waffle
				if (CoffeeMenuCounts.belgianWaffles != 0) {
					waple_total = CoffeeMenuLists.BELGIALWAFFLES
							+ (Integer.toString(CoffeeMenuCounts.belgianWaffles)) + "개\n";
					order_total += waple_total;
				}

				//prezels
				if (CoffeeMenuCounts.creamCheesePretzels != 0) {
					prechel_total = CoffeeMenuLists.CREAMCHEESEPRETZELS
							+ (Integer.toString(CoffeeMenuCounts.creamCheesePretzels)) + "개\n";
					order_total += prechel_total;
				}
				//chocochip muffins
				if (CoffeeMenuCounts.chocolateChipMuffins != 0) {
					muffin_total = CoffeeMenuLists.CHOCOLATECHIPMUFFINS
							+ (Integer.toString(CoffeeMenuCounts.chocolateChipMuffins)) + "개\n";
					order_total += muffin_total;
				}
				//blueberry muffins
				if (CoffeeMenuCounts.blueberryMuffins != 0) {
					bluebery_muffin_total = CoffeeMenuLists.BLUBERRYMUFFINS
							+ (Integer.toString(CoffeeMenuCounts.blueberryMuffins)) + "개\n";
					order_total += bluebery_muffin_total;
				}
				//croissant
				if (CoffeeMenuCounts.croissant != 0) {
					crowa_total = CoffeeMenuLists.CROISSANT
							+ (Integer.toString(CoffeeMenuCounts.croissant)) + "개\n";
					order_total += crowa_total;
				}

				//brownies
				if (CoffeeMenuCounts.walnutBrownies != 0) {
					browni_total = CoffeeMenuLists.WALNUTBROWNIES
							+ (Integer.toString(CoffeeMenuCounts.walnutBrownies)) + "개\n";
					order_total += browni_total;
				}

				if (CoffeeMenuCounts.total_count != 0) {
					sharingIntent.addCategory(Intent.CATEGORY_DEFAULT);
					sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
							"카페 주문 목록\n"+"총 주문 개수: "+CoffeeMenuCounts.total_count+" \n");// 제목.
					sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
							order_total);// 본문전송.

					sharingIntent.setType("text/plain");
					startActivity(Intent.createChooser(sharingIntent, "주문 전송 하기"));
					// Log.e("확인", order_total.toString());
					order_total = "";
				} else {
					Toast toast = Toast.makeText(getApplicationContext(), "메뉴를 선택 해주세요.", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK ){
			CoffeeMenuCounts.InitiateCoffeeMenuCounts();
			finish();
		}  return super.onKeyDown(keyCode, event);
	}


	public void CheckCoffeeOrderList() {
		//if ordered espresso exists
		if(CoffeeMenuCounts.espresso_hot_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.ESPRESSO_HOT);
			rowItems.add(item);
			i++;
		}

		//if ordered cappucino(hot) exists
		if(CoffeeMenuCounts.cappuccino_hot_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.CAPPUCCINO_HOT);
			rowItems.add(item);
			i++;
		}
		//if ordered cappucino(ice) exists
		if(CoffeeMenuCounts.cappuccino_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.CAPPUCCINO_ICE);
			rowItems.add(item);
			i++;
		}

		//if ordered americano(hot) exists
		if(CoffeeMenuCounts.americano_hot_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.AMERICANO_HOT);
			rowItems.add(item);
			i++;
		}
		//if ordered americano(ice) exists
		if(CoffeeMenuCounts.americano_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.AMERICANO_ICE);
			rowItems.add(item);
			i++;
		}

		//if ordered caramel(hot) exists
		if(CoffeeMenuCounts.caramelMacchiato_hot_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.CARAMELMACCHIATO_HOT);
			rowItems.add(item);
			i++;
		}
		//if ordered caramel(ice) exists
		if(CoffeeMenuCounts.caramelMacchiato_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.CARAMELMACCHIATO_ICE);
			rowItems.add(item);
			i++;
		}

		//if ordered cafemoca(hot) exists
		if(CoffeeMenuCounts.cafeMoca_hot_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.CAFEMOCA_HOT);
			rowItems.add(item);
			i++;
		}
		//if ordered cafemoca(ice) exists
		if(CoffeeMenuCounts.cafeMoca_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.CAFEMOCA_ICE);
			rowItems.add(item);
			i++;
		}


		//if ordered banilla(hot) exists
		if(CoffeeMenuCounts.banillaLatte_hot_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.BANILLALATEE_HOT);
			rowItems.add(item);
			i++;
		}
		//if ordered banilla(ice) exists
		if(CoffeeMenuCounts.banillaLatee_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.BANILLALATEE_ICE);
			rowItems.add(item);
			i++;
		}

		//if ordered cafelatte(hot) exists
		if(CoffeeMenuCounts.cafeLatte_hot_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.CAFELATTE_HOT);
			rowItems.add(item);
			i++;
		}
		//if ordered cafelatte(ice) exists
		if(CoffeeMenuCounts.cafeLatte_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.CAFELATTE_ICE);
			rowItems.add(item);
			i++;
		}


		//if ordered greentealatte(hot) exists
		if(CoffeeMenuCounts.greenteaLatte_hot_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.GREENTEALATTE_HOT);
			rowItems.add(item);
			i++;
		}
		//if ordered greamtealatte(ice) exists
		if(CoffeeMenuCounts.greenteaLatte_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.GREENTEALATTE_ICE);
			rowItems.add(item);
			i++;
		}

		//if ordered sweetpotatolatte(hot) exists
		if(CoffeeMenuCounts.sweetpoatatoLatte_hot_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.SWEETPOTATOLATTE_HOT);
			rowItems.add(item);
			i++;
		}
		//if ordered sweetpotatolatte(ice) exists
		if(CoffeeMenuCounts.sweetpoatatoLatte_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.SWEETPOTATOLATTE_ICE);
			rowItems.add(item);
			i++;
		}


		//if ordered jobs(hot) exists
		if(CoffeeMenuCounts.jobstearsTea_hot_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.JOBSTEARSTEA_HOT);
			rowItems.add(item);
			i++;
		}

		//if ordered citron(hot) exists
		if(CoffeeMenuCounts.citronTea_hot_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.CITRONTEA_HOT);
			rowItems.add(item);
			i++;
		}
		//if ordered citron(ice) exists
		if(CoffeeMenuCounts.citronTea_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.CITRONTEA_ICE);
			rowItems.add(item);
			i++;
		}
		//if ordered ssangwhatea(hot) exists
		if(CoffeeMenuCounts.ssangwhaTea_hot_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.SSANGWHATEA_HOT);
			rowItems.add(item);
			i++;
		}
		//if ordered ssangwhatea(ice) exists
		if(CoffeeMenuCounts.ssangwhaTea_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.SSANGWHATEA_ICE);
			rowItems.add(item);
			i++;
		}



		//if ordered chamomile(hot) exists
		if(CoffeeMenuCounts.chamomileTea_hot_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.CHAMOMILETEA_HOT);
			rowItems.add(item);
			i++;
		}
		//if ordered chemomile(ice) exists
		if(CoffeeMenuCounts.chamomileTea_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.CHAMOMILETEA_ICE);
			rowItems.add(item);
			i++;
		}

		//if ordered peppermint(hot) exists
		if(CoffeeMenuCounts.peppermintTea_hot_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.PEPPERMINTTEA_HOT);
			rowItems.add(item);
			i++;
		}
		//if ordered peppermint(ice) exists
		if(CoffeeMenuCounts.peppermintTea_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.PEPPERMINTTEA_ICE);
			rowItems.add(item);
			i++;
		}

		//if ordered cherrycok exists
		if(CoffeeMenuCounts.cherrycok_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.CHERRYCOCK_ICE);
			rowItems.add(item);
			i++;
		}
		//if ordered icetea(ice) exists
		if(CoffeeMenuCounts.icetea_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.ICETEA_ICE);
			rowItems.add(item);
			i++;
		}
		//if ordered remonade(ice) exists
		if(CoffeeMenuCounts.remonace_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.REMONADE_ICE);
			rowItems.add(item);
			i++;
		}
		//if ordered orangejuide(ice) exists
		if(CoffeeMenuCounts.orangejuice_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.ORANGEJUICE_ICE);
			rowItems.add(item);
			i++;
		}

		//if ordered grapejuice exists
		if(CoffeeMenuCounts.grapejuice_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.GRAPEJUICE_ICE);
			rowItems.add(item);
			i++;
		}
		//if ordered milk(hot) exists
		if(CoffeeMenuCounts.milk_hot_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.MILK_HOT);
			rowItems.add(item);
			i++;
		}
		//if ordered milk(ice) exists
		if(CoffeeMenuCounts.milk_ice_count != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.MILK_ICE);
			rowItems.add(item);
			i++;
		}


		//if ordered waffles exists
		if(CoffeeMenuCounts.belgianWaffles != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.BELGIALWAFFLES);
			rowItems.add(item);
			i++;
		}
		//if ordered prezels exists
		if(CoffeeMenuCounts.creamCheesePretzels != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.CREAMCHEESEPRETZELS);
			rowItems.add(item);
			i++;
		}

		//if ordered chocochip muffins exists
		if(CoffeeMenuCounts.chocolateChipMuffins != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.CHOCOLATECHIPMUFFINS);
			rowItems.add(item);
			i++;
		}
		//if ordered bluberry muffins exists
		if(CoffeeMenuCounts.blueberryMuffins != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.BLUBERRYMUFFINS);
			rowItems.add(item);
			i++;
		}
		//if ordered croissant exists
		if(CoffeeMenuCounts.croissant != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.CROISSANT);
			rowItems.add(item);
			i++;
		}

		//if ordered brownies exists
		if(CoffeeMenuCounts.walnutBrownies != 0) {
			CoffeeOrderedRowItem item = new CoffeeOrderedRowItem(CoffeeMenuLists.WALNUTBROWNIES);
			rowItems.add(item);
			i++;
		}
	}

}


