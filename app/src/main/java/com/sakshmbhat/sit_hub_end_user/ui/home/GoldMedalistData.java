package com.sakshmbhat.sit_hub_end_user.ui.home;

public class GoldMedalistData
{
    String Name,USN,ImageUrl;

    GoldMedalistData(){

    }

    public GoldMedalistData(String name, String USN, String imageUrl) {
        Name = name;
        this.USN = USN;
        ImageUrl = imageUrl;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUSN() {
        return USN;
    }

    public void setUSN(String USN) {
        this.USN = USN;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }


}
