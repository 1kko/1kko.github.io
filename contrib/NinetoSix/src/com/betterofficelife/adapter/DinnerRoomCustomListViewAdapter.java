package com.betterofficelife.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.betterofficelife.constants.Constants;
import com.betterofficelife.constants.DinnerRoomRowItem;
import com.betterofficelife.ninetosix.DaumMapActivity;
import com.betterofficelife.ninetosix.DinnerMenuActivity;
import com.betterofficelife.ninetosix.R;

public class DinnerRoomCustomListViewAdapter extends ArrayAdapter<DinnerRoomRowItem> {

	Context context;

	public DinnerRoomCustomListViewAdapter(Context context, int resourceId,
			List<DinnerRoomRowItem> items) {
		super(context, resourceId, items);
		this.context = context;
	}

	/*private view holder class*/
	private class ViewHolder {
		/*		ImageView imageView;
		TextView txtTitle;
		TextView txtDesc;*/

		ImageView img_dinnerRoom_icon;
		TextView tv_dinnerRoom_name;
		TextView tv_dinnerRoom_mainDishes;
		TextView tv_dinnerRoom_price;
		TextView tv_dinnerRoom_ref;
		Button btn_dinnerRoom_location;
		Button btn_dinnerRoom_calling;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		final DinnerRoomRowItem rowItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			if(Constants.height == 800) {
				Log.d("DeviceResolution", "480 x 800 : 갤럭시S, 갤럭시S2, 옵티머스 2X, Nexus S, Nexus One, HTC Desire HD, HTC Desire HD2");
				convertView = mInflater.inflate(R.layout.dinner_room_info_item_480x800, null);
			}
			else if(Constants.width == 800 && Constants.height == 1280) { 
				Log.d("DeviceResolution", "800 x 1280 : 갤럭시탭 10.1, 갤럭시노트1, 넥서스 7");
				convertView = mInflater.inflate(R.layout.dinner_room_info_item_800x1280, null);
			} 
			else if (Constants.width == 720 && Constants.height == 1184) { //Galaxy Nexus
				Log.d("DeviceResolution", "720 x 1184 : 갤럭시 넥서스");
				convertView = mInflater.inflate(R.layout.dinner_room_info_item, null);
			}
			else if (Constants.width == 720 && Constants.height ==1280) {	
				Log.d("DeviceResolution", "720 x 1280 : 갤럭시S3, 갤럭시S2 HD, 갤럭시노트2, 옵티머스G");
				convertView = mInflater.inflate(R.layout.dinner_room_info_item, null);
			}
			else {
				convertView = mInflater.inflate(R.layout.dinner_room_info_item, null);
			}


			holder = new ViewHolder();
			holder.img_dinnerRoom_icon = (ImageView) convertView.findViewById(R.id.dinner_room_img);
			holder.tv_dinnerRoom_name = (TextView) convertView.findViewById(R.id.dinner_room_name);
			holder.tv_dinnerRoom_mainDishes = (TextView) convertView.findViewById(R.id.dinner_room_main_dishes);
			holder.tv_dinnerRoom_price = (TextView)convertView.findViewById(R.id.dinner_room_price);
			holder.tv_dinnerRoom_ref = (TextView)convertView.findViewById(R.id.dinner_room_ref);
			holder.btn_dinnerRoom_location =  (Button) convertView.findViewById(R.id.dinnner_room_location);
			holder.btn_dinnerRoom_calling = (Button)convertView.findViewById(R.id.dinner_room_calling);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();


		//img_dinnerRoom_icon을 Click했을 때, 색 변화주기
		if (rowItem.getName().equals("369부대찌개")) {
			holder.img_dinnerRoom_icon.setBackgroundResource(R.drawable.budae_button_color);
		}
		if(rowItem.getName().equals("병천순대")) {
			holder.img_dinnerRoom_icon.setBackgroundResource(R.drawable.sundae_button_color);
		}

		if(rowItem.getName().equals("진국")) {
			holder.img_dinnerRoom_icon.setBackgroundResource(R.drawable.jincook_button_color);
		}

		if(rowItem.getName().equals("포커리")) {
			holder.img_dinnerRoom_icon.setBackgroundResource(R.drawable.phocurry_button_color);

		}

		if(rowItem.getName().equals("로뚜뚜커피")) {
			holder.img_dinnerRoom_icon.setBackgroundResource(R.drawable.rotutu_button_color);
		}
		if(rowItem.getName().equals("신기소")) {
			holder.img_dinnerRoom_icon.setBackgroundResource(R.drawable.sinjiso_button_color);
		}

		if(rowItem.getName().equals("김밥천국")) {
			holder.img_dinnerRoom_icon.setBackgroundResource(R.drawable.kimbab_button_color);
		}
		/*else {
			holder.img_dinnerRoom_icon.setBackgroundResource(rowItem.getImageId());
		}*/

		holder.tv_dinnerRoom_name.setText(rowItem.getName());
		holder.tv_dinnerRoom_mainDishes.setText(rowItem.getMainDishes());
		holder.tv_dinnerRoom_price.setText(rowItem.getPrice());
		holder.tv_dinnerRoom_ref.setText(rowItem.getRef());
		holder.btn_dinnerRoom_location.setBackgroundResource(R.drawable.map_button_color);
		holder.btn_dinnerRoom_calling.setBackgroundResource(R.drawable.calling_button_color);

		holder.btn_dinnerRoom_location.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Toast.makeText(context,rowItem.getLocation() , Toast.LENGTH_SHORT).show();
				Context context = v.getContext();
				Intent i = new Intent(context, DaumMapActivity.class);
				i.putExtra("dinner_room_name", rowItem.getName());
				i.putExtra("longitude",rowItem.getLongitude());
				i.putExtra("latitude", rowItem.getLatitude());	
				i.putExtra("dinner_room_location", rowItem.getLocation());
				context.startActivity(i);	



				/*Context context = v.getContext();
				Intent i = new Intent(context, DaumMapActivity.class);
				context.startActivity(i);}*/
			}
		});

		holder.btn_dinnerRoom_calling.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Toast.makeText(context, rowItem.getCalling(), Toast.LENGTH_SHORT).show();
				Context context = v.getContext();
				String tell = "tel:";
				tell+=rowItem.getCalling();
				Intent callIntent = new Intent(Intent.ACTION_DIAL);
				callIntent.setData(Uri.parse(tell));
				context.startActivity(callIntent);    	
			}
		});

		//야근 식당 이미지 클릭시, 메뉴 화면으로 이동
		holder.img_dinnerRoom_icon.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Context context = v.getContext();
				Intent intent = new Intent(context, DinnerMenuActivity.class);
				intent.putExtra("dinner_room_name", rowItem.getName());
				intent.putExtra("dinner_room_img2", rowItem.getImageId());


				context.startActivity(intent);   
				/*Context context = v.getContext();
				Intent intent = new Intent(context, PopupActivity.class);
				context.startActivity(intent);   */


			}
		});

		return convertView;




	}
}