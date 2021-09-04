package com.meru.ecommerce.productview.productviewservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ProductView")
public class ProductView implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productViewId;
    @Column(name = "pvProductId", unique = true)
    private int productId;

    @Embedded
    private Product product;

    @Embedded
    private Inventory inventory;

    @Embedded
    private Price price;

    @Embedded
    private Promotion promotion;

    public ProductView() {
    }

    public ProductView(int productId, Product product, Inventory inventory, Price price, Promotion promotion) {
        this.productId = productId;
        this.product = product;
        this.inventory = inventory;
        this.price = price;
        this.promotion = promotion;
    }

    public ProductView(int productViewId, int productId, Product product, Inventory inventory, Price price,
            Promotion promotion) {
        this.productViewId = productViewId;
        this.productId = productId;
        this.product = product;
        this.inventory = inventory;
        this.price = price;
        this.promotion = promotion;
    }

    public int getProductViewId() {
        return productViewId;
    }

    public void setProductViewId(int productViewId) {
        this.productViewId = productViewId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "ProductView [productViewId=" + productViewId + ", productId=" + productId + ", product=" + product
                + ", inventory=" + inventory + ", price=" + price + ", promotion=" + promotion + "]";
    }
}
