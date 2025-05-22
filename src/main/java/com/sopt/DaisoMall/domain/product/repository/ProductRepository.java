package com.sopt.DaisoMall.domain.product.repository;

import com.sopt.DaisoMall.domain.product.entity.Product;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = "productImages")
    List<Product> findTop20ByOrderById();

    @Query("""
    SELECT DISTINCT p FROM Product p
    LEFT JOIN FETCH p.productTagMappings ptm
    LEFT JOIN FETCH ptm.productTag
    WHERE p.brand.id = :brandId
    """)
    Slice<Product> findByBrandId(@Param("brandId") Long brandId, Pageable pageable);
}
