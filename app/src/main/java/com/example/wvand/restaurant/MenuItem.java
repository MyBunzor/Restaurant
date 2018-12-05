package com.example.wvand.restaurant;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String name;
    private String description;
    private String imageUrl;
    private long price;
    private String category;

    public MenuItem(String name, String description, String imageUrl, long price, String category) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public long getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
