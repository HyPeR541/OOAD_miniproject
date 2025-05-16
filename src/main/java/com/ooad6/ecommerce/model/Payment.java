package com.ooad6.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Payment")
public class Payment {
    @Id
    private String id;
    private int userID;
    private String paymentID;
    private double totalAmount;
    private String paymentType;
    private String paymentDate;

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", userID=" + userID +
                ", paymentID='" + paymentID + '\'' +
                ", totalAmount=" + totalAmount +
                ", paymentType='" + paymentType + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", discountCoupon='" + discountCoupon + '\'' +
                '}';
    }

    public Payment() {
    }

    private String discountCoupon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getDiscountCoupon() {
        return discountCoupon;
    }

    public void setDiscountCoupon(String discountCoupon) {
        this.discountCoupon = discountCoupon;
    }
}

