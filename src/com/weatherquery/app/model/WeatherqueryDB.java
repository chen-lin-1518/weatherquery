package com.weatherquery.app.model;

import java.util.ArrayList;
import java.util.List;

import com.weatherquery.app.db.WeatherQueryOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.NoCopySpan.Concrete;

public class WeatherqueryDB {
	/*
	 *Database name
	 */
	public static final String DB_NAME = "weahter_query";
	
	/*
	 *Database version 
	 */
	public static final int VERSION = 1;
	private static WeatherqueryDB weatherQueryDB;
	private SQLiteDatabase db;
	/*
	 *To privatise a constructor 
	 */
	private WeatherqueryDB(Context context)
	{
		WeatherQueryOpenHelper dbHelper = new WeatherQueryOpenHelper(context,DB_NAME,null,VERSION);
		db = dbHelper.getWritableDatabase();
	}
	/*
	 *Get WeatherqueryDB instance
	 */
	public synchronized static WeatherqueryDB getInstance(Context context){
		if(weatherQueryDB == null){
			weatherQueryDB = new WeatherqueryDB(context);
		}
		return weatherQueryDB;
	}
	/*
	 *The Province example is stored in the database
	 */
	public void saveProvince(Province province){
		if(province !=null){
			ContentValues values = new ContentValues();
			values.put("province_name",province.getProvinceName());
			values.put("province_code",province.getProvinceCode());
			db.insert("province", null, values);
		}
	}
	/*
	 *Read from the database information in all provinces
	 */
	public List<Province> loadProvince(){
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db
				.query("Province", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				list.add(province);
			}while(cursor.moveToNext());
		}
		if(cursor != null)
		{
			cursor.close();
		}
		return list;
	}
	/*
	 *The city example is stored in the database
	 */
	public void saveCity(City city){
		if(city !=null){
			ContentValues values = new ContentValues();
			values.put("city_name",city.getCityName());
			values.put("city_code",city.getCityCode());
			values.put("provinceId", city.getProvinceId());
			db.insert("city", null, values);
		}
	}
	/*
	 *Read from the database information in all city
	 */
	public List<City> loadCity(int provinceId){
		List<City> list = new ArrayList<City>();
		Cursor cursor = db
				.query("City", null, "province_id = ?",new String[]{String.valueOf(provinceId)}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setProvinceID(provinceId);
				list.add(city);
			}while(cursor.moveToNext());
		}
		if(cursor != null)
		{
			cursor.close();
		}
		return list;
	}
	/*
	 *The county example is stored in the database
	 */
	public void saveCounty(County county){
		if(county !=null){
			ContentValues values = new ContentValues();
			values.put("city_name",county.getCountyName());
			values.put("city_code",county.getCountyCode());
			values.put("cityId", county.getCityId());
			db.insert("county", null, values);
		}
	}
	/*
	 *Read from the database information in all county
	 */
	public List<County> loadCounty(int cityId){
		List<County> list = new ArrayList<County>();
		Cursor cursor = db
				.query("County", null, "city_id = ?",new String[]{String.valueOf(cityId)}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				County county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
				county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
				county.setCityID(cityId);
				list.add(county);
			}while(cursor.moveToNext());
		}
		if(cursor != null)
		{
			cursor.close();
		}
		return list;
	}
}
