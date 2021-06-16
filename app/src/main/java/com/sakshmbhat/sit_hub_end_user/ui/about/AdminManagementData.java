package com.sakshmbhat.sit_hub_end_user.ui.about;

public class AdminManagementData
{
    String Name,Post,ImageUrl,PageUrl;

    AdminManagementData(){

    }

    public AdminManagementData(String name, String post, String imageUrl, String PageUrl) {
        Name = name;
        Post = post;
        ImageUrl = imageUrl;
        this.PageUrl = PageUrl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPost() {
        return Post;
    }

    public void setPost(String post) {
        Post = post;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getPageUrl() {
        return PageUrl;
    }

    public void setPageUrl(String PageUrl) {
        this.PageUrl = PageUrl;
    }
}
