package com.betterofficelife.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.betterofficelife.constants.CoffeeMenuCounts;
import com.betterofficelife.constants.CoffeeMenuRowItem;
import com.betterofficelife.constants.Constants;
import com.betterofficelife.ninetosix.R;

public class CoffeeMenuCustomListViewAdapter extends ArrayAdapter<CoffeeMenuRowItem> {

	Context context;

	public CoffeeMenuCustomListViewAdapter(Context context, int resourceId,
			List<CoffeeMenuRowItem> items) {
		super(context, resourceId, items);
		this.context = context;
	}

	/*private view holder class*/
	private class ViewHolder {
		TextView tvMenuName;

		RadioGroup radioGroup;
		RadioButton btnHot;
		RadioButton btnIce;
		Button btnSubstract;
		Button btnAdd;
		TextView tvHotCount;
		TextView tvIceCount;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		final CoffeeMenuRowItem rowItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			
			if(Constants.height == 800) { //Galaxy S2
				Log.d("[CoffeeMenuAdapter]DeviceResolution", "480 x 800 : 갤럭시S, 갤럭시S2, 옵티머스 2X, Nexus S, Nexus One, HTC Desire HD, HTC Desire HD2");
				convertView = mInflater.inflate(R.layout.coffee_menu_info_item_480x800, null);		
			}
			else if (Constants.width == 720 && Constants.height == 1184) { //Galaxy Nexus
				Log.d("[CoffeeMenuAdapter]DeviceResolution", "720 x 1184 : 갤럭시 넥서스");
				convertView = mInflater.inflate(R.layout.coffee_menu_info_item, null);
			}
			else if (Constants.width == 800 && Constants.height == 1280) {
				Log.d("DeviceResolution", "800 x 1280 : 800 x 1280 : 갤럭시탭 10.1, 갤럭시노트1, 넥서스 7");
				convertView = mInflater.inflate(R.layout.coffee_menu_info_item_800x1280, null);
			}
			else if (Constants.width == 720 && Constants.height ==1280) {
				Log.d("[CoffeeMenuAdapter]DeviceResolution", "720 x 1280 : 갤럭시S3, 갤럭시S2 HD, 갤럭시노트2, 옵티머스G");
				convertView = mInflater.inflate(R.layout.coffee_menu_info_item_720x1280, null);
			}
			else {
				convertView = mInflater.inflate(R.layout.coffee_menu_info_item, null);
			}

			
			holder = new ViewHolder();
			holder.tvMenuName = (TextView) convertView.findViewById(R.id.tvMenuName);
			holder.radioGroup = (RadioGroup)convertView.findViewById(R.id.radioGroup);
			holder.btnHot =  (RadioButton) convertView.findViewById(R.id.btnHot);
			holder.btnIce = (RadioButton)convertView.findViewById(R.id.btnIce);
			holder.btnSubstract = (Button)convertView.findViewById(R.id.substract);
			holder.btnAdd = (Button)convertView.findViewById(R.id.add);
			holder.tvHotCount = (TextView) convertView.findViewById(R.id.tv_hot_count);
			holder.tvIceCount = (TextView) convertView.findViewById(R.id.tv_ice_count);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		holder.tvMenuName.setText(rowItem.getName());		
		holder.btnSubstract.setBackgroundResource(R.drawable.coffee_minus_color);
		holder.btnAdd.setBackgroundResource(R.drawable.coffee_plus_color);

		//hot or ice만 존재하는 메뉴들 처리
		String getMenuName = (String) holder.tvMenuName.getText();
		if (getMenuName.equals("고구마라떼") || getMenuName.equals("녹차라떼") || getMenuName.equals("카페라떼")) {
			convertView.findViewById(R.id.btnHot).setVisibility(View.VISIBLE);
			convertView.findViewById(R.id.btnIce).setVisibility(View.VISIBLE);
		}
		
		//Hot
		if(getMenuName.equals("에스프레소") ) {
			convertView.findViewById(R.id.btnIce).setVisibility(View.GONE);
			holder.btnHot.setChecked(true);
		} 	
		if(getMenuName.equals("율무차")) {
			convertView.findViewById(R.id.btnIce).setVisibility(View.GONE);
			holder.btnHot.setChecked(true);
		} 
		
		//빵종류
		if (getMenuName.equals("체리콕")) {
			convertView.findViewById(R.id.btnHot).setVisibility(View.GONE);
			holder.btnIce.setChecked(true);
		} 	
		if(getMenuName.equals("아이스티")) {
			convertView.findViewById(R.id.btnHot).setVisibility(View.GONE);
			holder.btnIce.setChecked(true);
		} 
		if(getMenuName.equals("레몬에이드")) {
			convertView.findViewById(R.id.btnHot).setVisibility(View.GONE);
			holder.btnIce.setChecked(true);
		} 
		if(getMenuName.equals("오렌지쥬스")) {
			convertView.findViewById(R.id.btnHot).setVisibility(View.GONE);
			holder.btnIce.setChecked(true);
		} 
		if(getMenuName.equals("포도쥬스")) {
			convertView.findViewById(R.id.btnHot).setVisibility(View.GONE);
			holder.btnIce.setChecked(true);
		} 
		if (getMenuName.equals("벨기에와플")) {
			convertView.findViewById(R.id.btnHot).setVisibility(View.GONE);
			convertView.findViewById(R.id.btnIce).setVisibility(View.GONE);
		}
		
		if( getMenuName.equals("크림치즈프레즐")) {
			convertView.findViewById(R.id.btnHot).setVisibility(View.GONE);
			convertView.findViewById(R.id.btnIce).setVisibility(View.GONE);
		}

			
		if( getMenuName.equals("초코칩머핀")) {
			convertView.findViewById(R.id.btnHot).setVisibility(View.GONE);
			convertView.findViewById(R.id.btnIce).setVisibility(View.GONE);
		}
		
		if( getMenuName.equals("블루베리머핀")) {
			convertView.findViewById(R.id.btnHot).setVisibility(View.GONE);
			convertView.findViewById(R.id.btnIce).setVisibility(View.GONE);
		}
		if( getMenuName.equals("크로와상")) {
			convertView.findViewById(R.id.btnHot).setVisibility(View.GONE);
			convertView.findViewById(R.id.btnIce).setVisibility(View.GONE);
		}
		if( getMenuName.equals("호두브라우니")) {
			convertView.findViewById(R.id.btnHot).setVisibility(View.GONE);
			convertView.findViewById(R.id.btnIce).setVisibility(View.GONE);
		}


		final ViewHolder finalViewHoler =holder;
		final View finalConvertView = convertView;

		//Hot RadioButton clicked
		finalViewHoler.btnHot.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				if  (finalViewHoler.btnHot.isChecked()) {
					if( rowItem.getName().equals("에스프레소")) {
						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.espresso_hot_count));
					}
					if( rowItem.getName().equals("카푸치노")) {
						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.cappuccino_hot_count));
					}
					if (rowItem.getName().equals("아메리카노")) {
						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.americano_hot_count));
					}
					if (rowItem.getName().equals("카라멜마끼야토")) {
						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.caramelMacchiato_hot_count));
					}
					if (rowItem.getName().equals("카페모카")) {
						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.cafeMoca_hot_count));
					}
					if (rowItem.getName().equals("바닐라라떼")) {
						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.banillaLatte_hot_count));
					}
					if (rowItem.getName().equals("카페라떼")) {
						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.cafeLatte_hot_count));
					}
					if (rowItem.getName().equals("녹차라떼")) {
						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.greenteaLatte_hot_count));
					}	
					if (rowItem.getName().equals("고구마라떼")) {
						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.sweetpoatatoLatte_hot_count));
					}
					if(rowItem.getName().equals("율무차")) {
						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.jobstearsTea_hot_count));
					}
					if(rowItem.getName().equals("유자차")) {
						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.citronTea_hot_count));
					}
					if(rowItem.getName().equals("쌍화차")) {
						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.ssangwhaTea_hot_count));
					}
					if(rowItem.getName().equals("카모마일")) {
						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.chamomileTea_hot_count));
					}
					if(rowItem.getName().equals("페퍼민트")) {
						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.peppermintTea_hot_count));
					}
					if(rowItem.getName().equals("우유")) {
						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.milk_hot_count));
					}
					finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
					finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);

				}
			}
		});

		//Ice RadioButton clicked
		finalViewHoler.btnIce.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				if  (finalViewHoler.btnIce.isChecked()) {
					if( rowItem.getName().equals("카푸치노")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.cappuccino_ice_count));
					}
					if (rowItem.getName().equals("아메리카노")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.americano_ice_count));
					}
					if (rowItem.getName().equals("카라멜마끼야토")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.caramelMacchiato_ice_count));
					}
					if (rowItem.getName().equals("카페모카")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.cafeMoca_ice_count));
					}
					if (rowItem.getName().equals("바닐라라떼")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.banillaLatee_ice_count));
					}
					if (rowItem.getName().equals("카페라떼")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.cafeLatte_ice_count));
					}
					if (rowItem.getName().equals("녹차라떼")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.greenteaLatte_ice_count));
					}	
					if (rowItem.getName().equals("고구마라떼")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.sweetpoatatoLatte_ice_count));
					}	
					if(rowItem.getName().equals("유자차")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.citronTea_ice_count));
					}
					
					if(rowItem.getName().equals("쌍화차")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.ssangwhaTea_ice_count));
					}
					if(rowItem.getName().equals("카모마일")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.chamomileTea_ice_count));
					}
					if(rowItem.getName().equals("페퍼민트")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.peppermintTea_ice_count));
					}
					if(rowItem.getName().equals("우유")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.milk_ice_count));
					}
					if(rowItem.getName().equals("체리콕")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.cherrycok_ice_count));
					}
					if(rowItem.getName().equals("아이스티")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.icetea_ice_count));
					}
					if(rowItem.getName().equals("레몬에이드")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.remonace_ice_count));
					}
					if(rowItem.getName().equals("오렌지쥬스")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.orangejuice_ice_count));
					}
					if(rowItem.getName().equals("포토쥬스")) {
						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.grapejuice_ice_count));
					}


					finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
					finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
				}
			}
		});





		/******************************************************************************************/
		/*********************************잔 추가 시*************************************************/
		/******************************************************************************************/
		finalViewHoler.btnAdd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				/*Coffee*/
				//에스프레소 hot선택
				if(rowItem.getName().equals("에스프레소") ) {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.espresso_hot_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.espresso_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}

				//카푸치노 hot선택
				if( rowItem.getName().equals("카푸치노")) {
					if  (finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.cappuccino_hot_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.cappuccino_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}

				//카푸치노 ice선택
				if( rowItem.getName().equals("카푸치노")) {
					if  (finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.cappuccino_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.cappuccino_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);

					}
				} 

				//아메리카노 hot선택
				if( rowItem.getName().equals("아메리카노")) {
					if  (finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.americano_hot_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.americano_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//아메리카노 ice선택
				if( rowItem.getName().equals("아메리카노")) {
					if (finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.americano_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.americano_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}


				//카라멜마끼아또 hot선택
				if( rowItem.getName().equals("카라멜마끼아또"))  {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.caramelMacchiato_hot_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.caramelMacchiato_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}

				//카라멜마끼아또 ice선택
				if( rowItem.getName().equals("카라멜마끼아또"))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.caramelMacchiato_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.caramelMacchiato_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}

				//카페모카 hot선택
				if( rowItem.getName().equals("카페모카"))  {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.cafeMoca_hot_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.cafeMoca_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}

				//카페모카 ice선택
				if( rowItem.getName().equals("카페모카"))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.cafeMoca_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.cafeMoca_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}

				//바닐라라떼 hot선택
				if( rowItem.getName().equals("바닐라라떼"))  {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.banillaLatte_hot_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.banillaLatte_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//바닐라라떼 ice선택
				if( rowItem.getName().equals("바닐라라떼"))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.banillaLatee_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.banillaLatee_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}
				//카페라떼 hot선택
				if( rowItem.getName().equals("카페라떼"))  {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.cafeLatte_hot_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.cafeLatte_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//카페라떼 ice선택
				if( rowItem.getName().equals("카페라떼"))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.cafeLatte_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.cafeLatte_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}

				//녹차라떼 hot선택
				if( rowItem.getName().equals("녹차라떼"))  {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.greenteaLatte_hot_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.greenteaLatte_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//녹차라떼 ice선택
				if( rowItem.getName().equals("녹차라떼"))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.greenteaLatte_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.greenteaLatte_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}
				
				
				//고구마라떼 hot선택
				if( rowItem.getName().equals("고구마라떼"))  {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.sweetpoatatoLatte_hot_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.sweetpoatatoLatte_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//고구마라떼 ice선택
				if( rowItem.getName().equals("고구마라떼"))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.sweetpoatatoLatte_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.sweetpoatatoLatte_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}



				/*Tea*/
				//율무차 hot선택
				if( rowItem.getName().equals("율무차"))  {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.jobstearsTea_hot_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.jobstearsTea_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//유자차 hot선택
				if( rowItem.getName().equals("유자차"))  {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.citronTea_hot_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.citronTea_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//유자차 ice선택
				if( rowItem.getName().equals("유자차"))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.citronTea_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.citronTea_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}

			

				
				//쌍화차 hot선택
				if( rowItem.getName().equals("쌍화차"))  {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.ssangwhaTea_hot_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.ssangwhaTea_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//쌍화차 ice선택
				if( rowItem.getName().equals("쌍화차"))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.ssangwhaTea_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.ssangwhaTea_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}	
				//카모마일 hot선택
				if( rowItem.getName().equals("카모마일"))  {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.chamomileTea_hot_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.chamomileTea_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//카모마일 ice선택
				if( rowItem.getName().equals("카모마일"))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.chamomileTea_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.chamomileTea_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}		
				//페퍼민트 hot선택
				if( rowItem.getName().equals("페퍼민트"))  {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.peppermintTea_hot_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.peppermintTea_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//페퍼민트 ice선택
				if( rowItem.getName().equals("페퍼민트"))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.peppermintTea_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.peppermintTea_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}	

				/*drink*/
				if( rowItem.getName().equals("체리콕"))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.cherrycok_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.cherrycok_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}		

				if( rowItem.getName().equals("아이스티"))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.icetea_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.icetea_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}	
				if( rowItem.getName().equals("레몬에이드"))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.remonace_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.remonace_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}	
				if( rowItem.getName().equals("오렌지쥬스"))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.orangejuice_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.orangejuice_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}	
				if( rowItem.getName().equals("포도쥬스"))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.grapejuice_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.grapejuice_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}	
				if( rowItem.getName().equals("우유"))  {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.milk_hot_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvHotCount
						.setText(Integer.toString(CoffeeMenuCounts.milk_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}	
				if( rowItem.getName().equals("우유"))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.milk_ice_count++;
						CoffeeMenuCounts.total_count++;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.milk_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}	

				/*bread*/
				if( rowItem.getName().equals("벨기에와플"))  {
					CoffeeMenuCounts.belgianWaffles++;
					CoffeeMenuCounts.total_count++;

					finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.belgianWaffles));
					finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
					finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
				}	
				if( rowItem.getName().equals("크림치즈프레즐"))  {
					CoffeeMenuCounts.creamCheesePretzels++;
					CoffeeMenuCounts.total_count++;

					finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.creamCheesePretzels));
					finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
					finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
				}	
				if( rowItem.getName().equals("초코칩머핀"))  {
					CoffeeMenuCounts.chocolateChipMuffins++;
					CoffeeMenuCounts.total_count++;

					finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.chocolateChipMuffins));
					finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
					finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
				}	
				
				if( rowItem.getName().equals("블루베리머핀"))  {
					CoffeeMenuCounts.blueberryMuffins++;
					CoffeeMenuCounts.total_count++;

					finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.blueberryMuffins));
					finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
					finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
				}	
				if( rowItem.getName().equals("크로와상"))  {
					CoffeeMenuCounts.croissant++;
					CoffeeMenuCounts.total_count++;

					finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.croissant));
					finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
					finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
				}	
				if( rowItem.getName().equals("호두브라우니"))  {
					CoffeeMenuCounts.walnutBrownies++;
					CoffeeMenuCounts.total_count++;

					finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.walnutBrownies));
					finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
					finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
				}	
			}
		});


		/******************************************************************************************/
		/*********************************잔 삭제 시*************************************************/
		/******************************************************************************************/
		holder.btnSubstract.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				/*Coffee*/
				//에스프레소 hot선택 
				if( (rowItem.getName().equals("에스프레소")) 
						&& (CoffeeMenuCounts.espresso_hot_count > 0)) {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.espresso_hot_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.espresso_hot_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}

				//카푸치노 hot선택
				if( rowItem.getName().equals("카푸치노")
						&& (CoffeeMenuCounts.cappuccino_hot_count > 0)) {
					if  (finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.cappuccino_hot_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.cappuccino_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}

				//카푸치노 ice선택
				if( rowItem.getName().equals("카푸치노") 
						&& (CoffeeMenuCounts.cappuccino_ice_count > 0)){
					if  (finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.cappuccino_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.cappuccino_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);

					}
				} 

				//아메리카노 hot선택
				if( rowItem.getName().equals("아메리카노") 
						&& (CoffeeMenuCounts.americano_hot_count > 0)){
					if (finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.americano_hot_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.americano_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//아메리카노 ice선택
				if( rowItem.getName().equals("아메리카노") 
						&& (CoffeeMenuCounts.americano_ice_count > 0)){
					if (finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.americano_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.americano_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}


				//카라멜마끼아또 hot선택
				if( rowItem.getName().equals("카라멜마끼아또") 
						&& (CoffeeMenuCounts.caramelMacchiato_hot_count > 0)){
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.caramelMacchiato_hot_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.caramelMacchiato_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}

				//카라멜마끼아또 ice선택
				if( rowItem.getName().equals("카라멜마끼아또")
						&& (CoffeeMenuCounts.caramelMacchiato_ice_count > 0)){
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.caramelMacchiato_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.caramelMacchiato_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}

				//카페모카 hot선택
				if( rowItem.getName().equals("카페모카")
						&& (CoffeeMenuCounts.cafeMoca_hot_count > 0)) {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.cafeMoca_hot_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.cafeMoca_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}

				//카페모카 ice선택
				if( rowItem.getName().equals("카페모카")
						&& (CoffeeMenuCounts.cafeMoca_ice_count > 0))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.cafeMoca_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.cafeMoca_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}

				//바닐라라떼 hot선택
				if( rowItem.getName().equals("바닐라라떼")
						&& (CoffeeMenuCounts.banillaLatte_hot_count > 0)){
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.banillaLatte_hot_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.banillaLatte_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//바닐라라떼 ice선택
				if( rowItem.getName().equals("바닐라라떼")
						&& (CoffeeMenuCounts.banillaLatee_ice_count > 0)){
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.banillaLatee_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.banillaLatee_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}
				//카페라떼 hot선택
				if( rowItem.getName().equals("카페라떼")
						&& (CoffeeMenuCounts.cafeLatte_hot_count > 0)){
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.cafeLatte_hot_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.cafeLatte_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//카페라떼 ice선택
				if( rowItem.getName().equals("카페라떼") 
						&& (CoffeeMenuCounts.cafeLatte_ice_count > 0)) {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.cafeLatte_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.cafeLatte_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}

				//녹차라떼 hot선택
				if( rowItem.getName().equals("녹차라떼")
						&& (CoffeeMenuCounts.greenteaLatte_hot_count > 0)){
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.greenteaLatte_hot_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.greenteaLatte_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//녹차라떼 ice선택
				if( rowItem.getName().equals("녹차라떼") 
						&& (CoffeeMenuCounts.greenteaLatte_ice_count > 0)) {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.greenteaLatte_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.greenteaLatte_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}
				
				
				//고구마라떼 hot선택
				if( rowItem.getName().equals("고구마라떼")
						&& (CoffeeMenuCounts.sweetpoatatoLatte_hot_count > 0)){
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.sweetpoatatoLatte_hot_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.sweetpoatatoLatte_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//고구마라떼 ice선택
				if( rowItem.getName().equals("고구마라떼") 
						&& (CoffeeMenuCounts.sweetpoatatoLatte_ice_count > 0)) {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.sweetpoatatoLatte_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.sweetpoatatoLatte_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}





				/*Tea*/
				//율무차 hot선택
				if( rowItem.getName().equals("율무차")
						&& (CoffeeMenuCounts.jobstearsTea_hot_count > 0)){
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.jobstearsTea_hot_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.jobstearsTea_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//유자차 hot선택
				if( rowItem.getName().equals("유자차")
						&& (CoffeeMenuCounts.citronTea_hot_count > 0)){
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.citronTea_hot_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.citronTea_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//유자차 ice선택
				if( rowItem.getName().equals("유자차")
						&& (CoffeeMenuCounts.citronTea_ice_count > 0)){
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.citronTea_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.citronTea_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}

			
				//쌍화차 hot선택
				if( rowItem.getName().equals("쌍화차")
						&& (CoffeeMenuCounts.ssangwhaTea_hot_count > 0))  {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.ssangwhaTea_hot_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.ssangwhaTea_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//쌍화차 ice선택
				if( rowItem.getName().equals("쌍화차")
						&& (CoffeeMenuCounts.ssangwhaTea_ice_count > 0)){
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.ssangwhaTea_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.ssangwhaTea_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}	
				//카모마일 hot선택
				if( rowItem.getName().equals("카모마일")
						&& (CoffeeMenuCounts.chamomileTea_hot_count > 0)){
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.chamomileTea_hot_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.chamomileTea_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//카모마일 ice선택
				if( rowItem.getName().equals("카모마일")
						&& (CoffeeMenuCounts.chamomileTea_ice_count > 0)){
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.chamomileTea_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.chamomileTea_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}		
				//페퍼민트 hot선택
				if( rowItem.getName().equals("페퍼민트")
						&& (CoffeeMenuCounts.peppermintTea_hot_count > 0)) {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.peppermintTea_hot_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.peppermintTea_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}
				//페퍼민트 ice선택
				if( rowItem.getName().equals("페퍼민트")
						&& (CoffeeMenuCounts.peppermintTea_ice_count > 0)){
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.peppermintTea_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.peppermintTea_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}	

				/*drink*/
				if( rowItem.getName().equals("체리콕")
						&& (CoffeeMenuCounts.cherrycok_ice_count > 0)){
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.cherrycok_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.cherrycok_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}		

				if( rowItem.getName().equals("아이스티")
						&& (CoffeeMenuCounts.icetea_ice_count > 0)){
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.icetea_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.icetea_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}	
				if( rowItem.getName().equals("레몬에이드")
						&& (CoffeeMenuCounts.remonace_ice_count > 0)){
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.remonace_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.remonace_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}	
				if( rowItem.getName().equals("오렌지쥬스")
						&& (CoffeeMenuCounts.orangejuice_ice_count > 0)){
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.orangejuice_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.orangejuice_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}	
				if( rowItem.getName().equals("포도쥬스")
						&& (CoffeeMenuCounts.grapejuice_ice_count > 0))  {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.grapejuice_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.grapejuice_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}	

				if( rowItem.getName().equals("우유")
						&& (CoffeeMenuCounts.milk_hot_count > 0)) {
					if(finalViewHoler.btnHot.isChecked()) {
						CoffeeMenuCounts.milk_hot_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.milk_hot_count));
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
					}
				}	
				if( rowItem.getName().equals("우유")
						&& (CoffeeMenuCounts.milk_ice_count > 0)) {
					if(finalViewHoler.btnIce.isChecked()) {
						CoffeeMenuCounts.milk_ice_count--;
						CoffeeMenuCounts.total_count--;

						finalViewHoler.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.milk_ice_count));
						finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.VISIBLE);
						finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.GONE);
					}
				}	

				/*bread*/
				if( rowItem.getName().equals("벨기에와플")
						&& (CoffeeMenuCounts.belgianWaffles > 0)) {
					CoffeeMenuCounts.belgianWaffles--;
					CoffeeMenuCounts.total_count--;

					finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.belgianWaffles));
					finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
					finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
				}	
				if( rowItem.getName().equals("크림치즈프레즐")
						&& (CoffeeMenuCounts.creamCheesePretzels > 0)) {
					CoffeeMenuCounts.creamCheesePretzels--;
					CoffeeMenuCounts.total_count--;

					finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.creamCheesePretzels));
					finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
					finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
				}	
				if( rowItem.getName().equals("초코칩머핀")
						&& (CoffeeMenuCounts.chocolateChipMuffins > 0)) {
					CoffeeMenuCounts.chocolateChipMuffins--;
					CoffeeMenuCounts.total_count--;

					finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.chocolateChipMuffins));
					finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
					finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
				}	
				
				if( rowItem.getName().equals("블루베리머핀")
						&& (CoffeeMenuCounts.blueberryMuffins > 0)) {
					CoffeeMenuCounts.blueberryMuffins--;
					CoffeeMenuCounts.total_count--;

					finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.blueberryMuffins));
					finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
					finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
				}	
				if( rowItem.getName().equals("크로와상")
						&& (CoffeeMenuCounts.croissant > 0)) {
					CoffeeMenuCounts.croissant--;
					CoffeeMenuCounts.total_count--;

					finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.croissant));
					finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
					finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
				}	
				if( rowItem.getName().equals("호두브라우니")
						&& (CoffeeMenuCounts.walnutBrownies > 0)) {
					CoffeeMenuCounts.walnutBrownies--;
					CoffeeMenuCounts.total_count--;

					finalViewHoler.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.walnutBrownies));
					finalConvertView.findViewById(R.id.tv_hot_count).setVisibility(View.VISIBLE);
					finalConvertView.findViewById(R.id.tv_ice_count).setVisibility(View.GONE);
				}
			}
		});

		return convertView;
	}
}