package com.meru.ecommerce.productview.productviewservice.entity;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column
    private int productId;
    @Column
    private String productName;
    @Column
    private String category;
    @Column
    private String productStatus;
    @Column
    private Blob image;
    @Embedded
    private ProductDescription productDescription;

    public Product() {
    }

    public Product(int productId, String productName, String category, String productStatus, Blob image,
            ProductDescription productDescription) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.productStatus = productStatus;
        this.image = image;
        this.productDescription = productDescription;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", category=" + category
                + ", productStatus=" + productStatus + ", productDescription=" + productDescription + "]";
    }
}
