package com.betterofficelife.constants;


public class DinnerRoomRowItem {
	private int imageId;
	private String name;
	private String main_dishes;
	private String price;
	private String ref;
	private String location;
	private double longitude;
	private double latitude;
	private String calling;

	public DinnerRoomRowItem(int imageId, String name, String main_dishes, String price, String ref, 
								String location,double longitude,double latitude, String calling) {
		this.imageId = imageId;
		this.name = name;
		this.main_dishes = main_dishes;
		this.price = price;
		this.ref = ref;
		this.location = location;
		this.longitude = longitude;
		this.latitude = latitude;
		this.calling = calling;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMainDishes() {
		return main_dishes;
	}
	public void setMainDishes(String main_dishes) {
		this.main_dishes = main_dishes;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}	
	public String getCalling() {
		return calling;
	}
	public void setCalling(String calling) {
		this.calling = calling;
	}	
	
	@Override
	public String toString() {
		return name + "\n" + main_dishes;
	}
}