package com.meru.ecommerce.productview.productviewservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Price implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column
    private int priceId;
    @Column(name = "priceProductId")
    private int productId;
    @Column(name = "priceProductName")
    private String productName;
    @Column
    private double price;

    public Price() {
    }

    public Price(int priceId, String productName, int productId, double price) {
        this.priceId = priceId;
        this.productName = productName;
        this.productId = productId;
        this.price = price;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price [priceId=" + priceId + ", productId=" + productId + ", productName=" + productName + ", price="
                + price + "]";
    }
}
