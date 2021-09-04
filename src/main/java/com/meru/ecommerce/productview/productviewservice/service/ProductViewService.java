package com.meru.ecommerce.productview.productviewservice.service;

import java.util.List;

import com.meru.ecommerce.productview.productviewservice.entity.Inventory;
import com.meru.ecommerce.productview.productviewservice.entity.Price;
import com.meru.ecommerce.productview.productviewservice.entity.Product;
import com.meru.ecommerce.productview.productviewservice.entity.ProductView;
import com.meru.ecommerce.productview.productviewservice.entity.Promotion;

public interface ProductViewService {
    public boolean removeProductView(int productViewId);

    public List<ProductView> getAllProductViews();

    public ProductView getProductViewById(int productViewId);

    public boolean createOrUpdateProductView(ProductView productView);

    public boolean createOrUpdateProductInProductView(int productId, Product product);

    public boolean createOrUpdateInventoryInProductView(int productId, Inventory inventory);

    public boolean createOrUpdatePriceInProductView(int productId, Price price);

    public boolean createOrUpdatePromotionInProductView(int productId, Promotion promotion);
}