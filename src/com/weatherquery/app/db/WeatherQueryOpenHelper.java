package com.weatherquery.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class WeatherQueryOpenHelper extends SQLiteOpenHelper {

	/*
	 *Province Table
	 */
	public static final String CREATE_PROVINCE="create table Province("
			+"id integer primary key autoinvcrement," 
			+"province_name_text,"
			+"province_code_text)";
	/*
	 *City Table
	 */
	public static final String CREATE_CITY="create table city("
			+"id integer primary key autoinvcrement," 
			+"city_name_text,"
			+"city_code_text,"
			+"province_id integer)";
	/*
	 *County Table
	 */
	
	public static final String CREATE_COUNTY="create table County("
			+"id integer primary key autoinvcrement," 
			+"county_name_text,"
			+"county_code_text,"
			+"city_id integer)";
	public WeatherQueryOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_PROVINCE);
		db.execSQL(CREATE_CITY);
		db.execSQL(CREATE_COUNTY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
