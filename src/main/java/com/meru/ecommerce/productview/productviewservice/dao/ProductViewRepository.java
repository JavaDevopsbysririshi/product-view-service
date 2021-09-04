package com.meru.ecommerce.productview.productviewservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meru.ecommerce.productview.productviewservice.entity.ProductView;

@Repository("ProductViewRepository")
public interface ProductViewRepository extends JpaRepository<ProductView, Integer> {
    @Query("select pv from ProductView pv where pv.productId=:productId")
    public ProductView getByProductId(@Param("productId") int productId);
}
