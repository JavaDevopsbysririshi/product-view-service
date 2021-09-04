package com.meru.ecommerce.productview.productviewservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meru.ecommerce.productview.productviewservice.dao.ProductViewRepository;
import com.meru.ecommerce.productview.productviewservice.entity.Inventory;
import com.meru.ecommerce.productview.productviewservice.entity.Price;
import com.meru.ecommerce.productview.productviewservice.entity.Product;
import com.meru.ecommerce.productview.productviewservice.entity.ProductView;
import com.meru.ecommerce.productview.productviewservice.entity.Promotion;

@Service("ProductViewService")
public class ProductViewServiceImpl implements ProductViewService {

    @Autowired
    ProductViewRepository productViewRepository;

    @Override
    public boolean removeProductView(int productViewId) {
        productViewRepository.delete(productViewId);
        return true;
    }

    @Override
    public List<ProductView> getAllProductViews() {
        return productViewRepository.findAll();
    }

    @Override
    public ProductView getProductViewById(int productViewId) {
        return productViewRepository.findOne(productViewId);
    }

    @Override
    public boolean createOrUpdateProductView(ProductView productView) {
        ProductView pv = productViewRepository.save(productView);
        boolean status = false;
        if (null != pv) {
            status = true;
        }
        return status;
    }

    @Override
    public boolean createOrUpdateProductInProductView(int productId, Product product) {
        ProductView pv = productViewRepository.getByProductId(productId);
        if (null != pv) {
            pv.setProduct(product);
        } else {
            pv = new ProductView(productId, product, new Inventory(), new Price(), new Promotion());
        }
        return createOrUpdateProductView(pv);
    }

    @Override
    public boolean createOrUpdateInventoryInProductView(int productId, Inventory inventory) {
        ProductView pv = productViewRepository.getByProductId(productId);
        if (null != pv) {
            pv.setInventory(inventory);
        } else {
            pv = new ProductView(productId, new Product(), inventory, new Price(), new Promotion());
        }
        return createOrUpdateProductView(pv);
    }

    @Override
    public boolean createOrUpdatePriceInProductView(int productId, Price price) {
        ProductView pv = productViewRepository.getByProductId(productId);
        if (null != pv) {
            pv.setPrice(price);
        } else {
            pv = new ProductView(productId, new Product(), new Inventory(), price, new Promotion());
        }
        return createOrUpdateProductView(pv);
    }

    @Override
    public boolean createOrUpdatePromotionInProductView(int productId, Promotion promotion) {
        ProductView pv = productViewRepository.getByProductId(productId);
        if (null != pv) {
            pv.setPromotion(promotion);
        } else {
            pv = new ProductView(productId, new Product(), new Inventory(), new Price(), promotion);
        }
        return createOrUpdateProductView(pv);
    }
}
