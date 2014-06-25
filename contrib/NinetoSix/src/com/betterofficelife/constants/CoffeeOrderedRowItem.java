package com.betterofficelife.constants;

public class CoffeeOrderedRowItem {
	private String orderedMenuName;
	public CoffeeOrderedRowItem(String menuName) {
		this.orderedMenuName = menuName;
	}
	public String getName() {
		return orderedMenuName;
	}
	public void setName(String menuName) {
		this.orderedMenuName = menuName;
	}
}