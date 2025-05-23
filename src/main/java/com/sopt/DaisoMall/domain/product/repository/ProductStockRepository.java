package com.sopt.DaisoMall.domain.product.repository;

import com.sopt.DaisoMall.domain.product.entity.Product;
import com.sopt.DaisoMall.domain.store.entity.StoreProductStock;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockRepository extends JpaRepository<StoreProductStock, Long> {

    @Query("""
    SELECT s.product
      FROM StoreProductStock s
      JOIN s.product p
      LEFT JOIN p.brand b
     WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))
        OR LOWER(p.productCode) LIKE LOWER(CONCAT('%', :keyword, '%'))
        OR LOWER(TRIM(b.name)) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    Slice<Product> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("""
    SELECT s.product
      FROM StoreProductStock s
      JOIN s.product p
      LEFT JOIN p.brand b
     WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))
        OR LOWER(p.productCode) LIKE LOWER(CONCAT('%', :keyword, '%'))
        OR LOWER(TRIM(b.name)) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    Slice<Product> findNewest(@Param("keyword") String keyword, Pageable pageable);
}
