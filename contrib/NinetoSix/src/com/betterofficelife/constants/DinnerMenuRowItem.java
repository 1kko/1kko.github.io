package com.betterofficelife.constants;

public class DinnerMenuRowItem {
    private String dinner_menu_name;
    
    public DinnerMenuRowItem(){
        super();
    }
    
    public DinnerMenuRowItem(String dinner_menu_name) {
        super();
        this.dinner_menu_name = dinner_menu_name;
    }

    @Override
    public String toString() {
     return this.dinner_menu_name;
    }
}