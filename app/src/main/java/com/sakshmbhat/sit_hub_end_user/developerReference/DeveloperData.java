package com.sakshmbhat.sit_hub_end_user.developerReference;

public class DeveloperData {

    String name,imageUrl,role,linkedin,gmail,instagram;

    public DeveloperData() {
    }

    public DeveloperData(String name, String imageUrl, String role, String linkedin, String gmail, String instagram) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.role = role;
        this.linkedin = linkedin;
        this.gmail = gmail;
        this.instagram = instagram;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
}
