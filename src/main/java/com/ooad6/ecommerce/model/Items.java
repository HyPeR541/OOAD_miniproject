package com.ooad6.ecommerce.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Items")
public class Items {
    private String id;
    private String Name;
    private int Cost;
    private int Stock;
    private String Description;
    private String Category;
    private String Review;

    public Items() {
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", Cost=" + Cost +
                ", Stock=" + Stock +
                ", Description='" + Description + '\'' +
                ", Category='" + Category + '\'' +
                ", Review='" + Review + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }
}