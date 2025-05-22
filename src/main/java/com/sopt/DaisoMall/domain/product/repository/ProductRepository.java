package com.sopt.DaisoMall.domain.product.repository;

import com.sopt.DaisoMall.domain.product.dto.response.PopularProductResponse;
import com.sopt.DaisoMall.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
      select new com.sopt.DaisoMall.domain.product.dto.response.PopularProductResponse(
        p.id,
        p.productName,
        p.productCode,
        p.price,
        img.imageUrl
      )
      from Product p
      left join p.images img
        on img.isMain = true
      order by p.id
    """)
    Page<PopularProductResponse> findPopularProducts(Pageable pageable);

    @Query("""
    SELECT DISTINCT p FROM Product p
    LEFT JOIN FETCH p.productTagMappings ptm
    LEFT JOIN FETCH ptm.productTag
    WHERE p.brand.id = :brandId
    """)
    Slice<Product> findByBrandId(@Param("brandId") Long brandId, Pageable pageable);
}
