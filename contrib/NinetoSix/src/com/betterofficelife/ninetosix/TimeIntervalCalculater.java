package com.betterofficelife.ninetosix;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.util.Log;

import com.betterofficelife.constants.Constants;


public class TimeIntervalCalculater {
	Context context;

	long tempEndTime;
	static ParkingPreference pref;

	public TimeIntervalCalculater(Context context) {
		this.context = context;

		pref = new ParkingPreference(context);
	}

	public String getTimeIntervalCalculater(String oldTime) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA);
		Date startday = sf.parse(oldTime, new ParsePosition(0));

		long startTime = startday.getTime();

		//현재의 시간 설정	
		Calendar cal = Calendar.getInstance();
		Date endDate = cal.getTime();
		long endTime = endDate.getTime();

		pref.put(Constants.PREF_REGISTER_TIME, Long.toString(endTime));
		String temp = pref.getValue(Constants.PREF_REGISTER_TIME, "");

		long mills = endTime - startTime;

		//분으로 변환
		long min = mills / 60000;
		long hour = min / 60;
		long remainMin = min % 60;

		StringBuffer diffTime = new StringBuffer();

		if (1 > min) {
			diffTime.append("방금 전에 등록하였습니다.");	
		} else if (60 > min && min > 0) {
			diffTime.append(min).append("분 전에 등록하였습니다.");	
		} else if (24 > hour && hour > 0 && remainMin > 0) {
			diffTime.append(hour).append("시간 ").append(remainMin).append("분 전에 등록하였습니다.");
		} else if (hour > 24 && remainMin >= 0) {
			diffTime.append("24+ 시간 전에 등록하였습니다.");	
		}


		Log.d(Constants.TAG, "endTime : " + temp);
		Log.d(Constants.TAG, "startTime : " + startTime);

		Log.d(Constants.TAG, "diffTime.toString() : " + diffTime.toString());

		return  diffTime.toString();
	}

	public String getTimeIntervalCalculater(long startTime, int newRagisterTime) {
		//현재의 시간 설정	
		Calendar cal = Calendar.getInstance();
		Date endDate = cal.getTime();
		tempEndTime = endDate.getTime();

		if (newRagisterTime == 0) {
			
		} else if (newRagisterTime == 1) {
			pref.put(Constants.PREF_REGISTER_TIME, Long.toString(tempEndTime));
		}

		long mills = tempEndTime - startTime;

		//분으로 변환
		long min = mills / 60000;
		long hour = min / 60;
		long remainMin = min % 60;

		StringBuffer diffTime = new StringBuffer();

		if (1 > min) {
			diffTime.append("방금 전에 등록하였습니다.");	
		} else if (60 > min && min > 0) {
			diffTime.append(min).append("분 전에 등록하였습니다.");	
		} else if (24 > hour && hour > 0 && remainMin > 0) {
			diffTime.append(hour).append("시간 ").append(remainMin).append("분 전에 등록하였습니다.");
		} else if (hour > 24 && remainMin >= 0) {
			diffTime.append("24+ 시간 전에 등록하였습니다.");	
		}

		
		String debugEndTime = pref.getValue(Constants.PREF_REGISTER_TIME, "");
		
		Log.d(Constants.TAG, "endTime : " + debugEndTime);
		Log.d(Constants.TAG, "startTime : " + startTime);

		Log.d(Constants.TAG, "diffTime.toString() : " + diffTime.toString());

		return  diffTime.toString();
	}
}
