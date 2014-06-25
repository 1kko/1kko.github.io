package com.betterofficelife.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.betterofficelife.constants.CoffeeMenuCounts;
import com.betterofficelife.constants.CoffeeMenuLists;
import com.betterofficelife.constants.CoffeeOrderedRowItem;
import com.betterofficelife.constants.Constants;
import com.betterofficelife.ninetosix.R;

public class CoffeeOrderedCustomListViewAdapter extends ArrayAdapter<CoffeeOrderedRowItem> {

	Context context;

	public CoffeeOrderedCustomListViewAdapter(Context context, int resourceId,
			List<CoffeeOrderedRowItem> items) {
		super(context, resourceId, items);
		this.context = context;
	}

	/*private view holder class*/
	private class ViewHolder {
		TextView tvOrderedName;
		TextView tvHotCount;
		TextView tvIceCount;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		final CoffeeOrderedRowItem rowItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			if(Constants.height == 800) { //Galaxy S2
				Log.d("[CoffeeMenuAdapter]DeviceResolution", "480 x 800 : 갤럭시S, 갤럭시S2, 옵티머스 2X, Nexus S, Nexus One, HTC Desire HD, HTC Desire HD2");
				convertView = mInflater.inflate(R.layout.coffee_ordered_info_item_480x800, null);		
			}
			else if (Constants.width == 800 && Constants.height == 1280) {
				Log.d("DeviceResolution", "800 x 1280 : 800 x 1280 : 갤럭시탭 10.1, 갤럭시노트1, 넥서스 7");
				convertView = mInflater.inflate(R.layout.coffee_ordered_info_item_800x1280, null);
			}
			else {
				convertView = mInflater.inflate(R.layout.coffee_ordered_info_item, null);
			}

			holder = new ViewHolder();

			holder.tvOrderedName = (TextView) convertView.findViewById(R.id.tvOrderedName);
			holder.tvHotCount = (TextView) convertView.findViewById(R.id.tv_hot_count2);
			holder.tvIceCount = (TextView) convertView.findViewById(R.id.tv_ice_count2);

			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}


		holder.tvOrderedName.setText(rowItem.getName());		

		/*HOT MENU 따로*/
		if(rowItem.getName().equals(CoffeeMenuLists.ESPRESSO_HOT)) {
			holder.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.espresso_hot_count));	
			holder.tvHotCount.setVisibility(View.VISIBLE);
			holder.tvIceCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.CAPPUCCINO_HOT)) {
			holder.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.cappuccino_hot_count));	
			holder.tvHotCount.setVisibility(View.VISIBLE);
			holder.tvIceCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.AMERICANO_HOT)) {
			holder.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.americano_hot_count));	
			holder.tvHotCount.setVisibility(View.VISIBLE);
			holder.tvIceCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.CARAMELMACCHIATO_HOT)) {
			holder.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.caramelMacchiato_hot_count));	
			holder.tvHotCount.setVisibility(View.VISIBLE);
			holder.tvIceCount.setVisibility(View.GONE);
		}

		if(rowItem.getName().equals(CoffeeMenuLists.CAFEMOCA_HOT)) {
			holder.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.cafeMoca_hot_count));	
			holder.tvHotCount.setVisibility(View.VISIBLE);
			holder.tvIceCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.BANILLALATEE_HOT)) {
			holder.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.banillaLatte_hot_count));	
			holder.tvHotCount.setVisibility(View.VISIBLE);
			holder.tvIceCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.CAFELATTE_HOT)) {
			holder.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.cafeLatte_hot_count));	
			holder.tvHotCount.setVisibility(View.VISIBLE);
			holder.tvIceCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.GREENTEALATTE_HOT)) {
			holder.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.greenteaLatte_hot_count));	
			holder.tvHotCount.setVisibility(View.VISIBLE);
			holder.tvIceCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.SWEETPOTATOLATTE_HOT)) {
			holder.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.sweetpoatatoLatte_hot_count));	
			holder.tvHotCount.setVisibility(View.VISIBLE);
			holder.tvIceCount.setVisibility(View.GONE);
		}

		if(rowItem.getName().equals(CoffeeMenuLists.JOBSTEARSTEA_HOT)) {
			holder.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.jobstearsTea_hot_count));	
			holder.tvHotCount.setVisibility(View.VISIBLE);
			holder.tvIceCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.CITRONTEA_HOT)) {
			holder.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.citronTea_hot_count));	
			holder.tvHotCount.setVisibility(View.VISIBLE);
			holder.tvIceCount.setVisibility(View.GONE);
		}


		if(rowItem.getName().equals(CoffeeMenuLists.SSANGWHATEA_HOT)) {
			holder.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.ssangwhaTea_hot_count));	
			holder.tvHotCount.setVisibility(View.VISIBLE);
			holder.tvIceCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.CHAMOMILETEA_HOT)) {
			holder.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.chamomileTea_hot_count));	
			holder.tvHotCount.setVisibility(View.VISIBLE);
			holder.tvIceCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.PEPPERMINTTEA_HOT)) {
			holder.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.peppermintTea_hot_count));	
			holder.tvHotCount.setVisibility(View.VISIBLE);
			holder.tvIceCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.MILK_HOT)) {
			holder.tvHotCount.setText(Integer.toString(CoffeeMenuCounts.milk_hot_count));	
			holder.tvHotCount.setVisibility(View.VISIBLE);
			holder.tvIceCount.setVisibility(View.GONE);
		}



		/*ICE MENU 따로 */
		if(rowItem.getName().equals(CoffeeMenuLists.CAPPUCCINO_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.cappuccino_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.AMERICANO_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.americano_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.CARAMELMACCHIATO_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.caramelMacchiato_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.CAFEMOCA_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.cafeMoca_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}		
		if(rowItem.getName().equals(CoffeeMenuLists.BANILLALATEE_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.banillaLatee_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}		
		if(rowItem.getName().equals(CoffeeMenuLists.CAFELATTE_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.cafeLatte_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}		
		if(rowItem.getName().equals(CoffeeMenuLists.GREENTEALATTE_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.greenteaLatte_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.SWEETPOTATOLATTE_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.sweetpoatatoLatte_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.CITRONTEA_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.citronTea_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}


		if(rowItem.getName().equals(CoffeeMenuLists.SSANGWHATEA_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.ssangwhaTea_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.CHAMOMILETEA_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.chamomileTea_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}		
		if(rowItem.getName().equals(CoffeeMenuLists.PEPPERMINTTEA_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.peppermintTea_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}		
		if(rowItem.getName().equals(CoffeeMenuLists.CHERRYCOCK_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.cherrycok_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}		
		if(rowItem.getName().equals(CoffeeMenuLists.ICETEA_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.icetea_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.REMONADE_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.remonace_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}

		if(rowItem.getName().equals(CoffeeMenuLists.ORANGEJUICE_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.orangejuice_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.GRAPEJUICE_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.grapejuice_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.MILK_ICE)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.milk_ice_count));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}



		if(rowItem.getName().equals(CoffeeMenuLists.BELGIALWAFFLES)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.belgianWaffles));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}		
		if(rowItem.getName().equals(CoffeeMenuLists.CREAMCHEESEPRETZELS)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.creamCheesePretzels));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}		
		if(rowItem.getName().equals(CoffeeMenuLists.CHOCOLATECHIPMUFFINS)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.chocolateChipMuffins));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}	
		if(rowItem.getName().equals(CoffeeMenuLists.BLUBERRYMUFFINS)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.blueberryMuffins));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}	
		if(rowItem.getName().equals(CoffeeMenuLists.CROISSANT)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.croissant));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}
		if(rowItem.getName().equals(CoffeeMenuLists.WALNUTBROWNIES)) {
			holder.tvIceCount.setText(Integer.toString(CoffeeMenuCounts.walnutBrownies));	
			holder.tvIceCount.setVisibility(View.VISIBLE);
			holder.tvHotCount.setVisibility(View.GONE);
		}


		return convertView;
	}
}