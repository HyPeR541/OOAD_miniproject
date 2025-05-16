package com.ooad6.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Cart")
public class Cart {
    @Id
    private String id;
    private int userid;
    private String name;
    private int cost;
    private int qty;
    private String description;

    public Cart() {}

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", userid=" + userid +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", qty=" + qty +
                ", description='" + description + '\'' +
                '}';
    }

    public Cart(String id, int userid, String name, int cost, int qty, String description) {
        this.id = id;
        this.userid = userid;
        this.name = name;
        this.cost = cost;
        this.qty = qty;
        this.description = description;
    }

    public int getUserID() { return userid; }
    public void setUserID(int userid) { this.userid = userid; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
