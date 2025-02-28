package com.sakshmbhat.sit_hub_end_user.ui.faculty;

public class FacultyAttributes {

    private String Name,Email,Post,ImageUrl,Key,profileUrl;

    //Constructor
    public FacultyAttributes(){
    }

    //Constructor;
    public FacultyAttributes(String name,String email,String post,String imageUrl, String key, String profileUrl) {

        this.Name = name;
        this.Email = email;
        this.Post = post;
        this.ImageUrl = imageUrl;
        this.Key = key;
        this.profileUrl=profileUrl;


    }


    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}
