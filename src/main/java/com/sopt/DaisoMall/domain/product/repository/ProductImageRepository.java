package com.sopt.DaisoMall.domain.product.repository;

import com.sopt.DaisoMall.domain.product.entity.Product;
import com.sopt.DaisoMall.domain.product.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    void deleteByProduct(Product product);
    int countByProductAndIsMain(Product product, Boolean isMain);
}
