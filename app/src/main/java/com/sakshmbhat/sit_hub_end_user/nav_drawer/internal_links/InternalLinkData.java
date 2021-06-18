package com.sakshmbhat.sit_hub_end_user.nav_drawer.internal_links;

public class InternalLinkData {

    private String name, link;

    public InternalLinkData() {
    }

    public InternalLinkData(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
