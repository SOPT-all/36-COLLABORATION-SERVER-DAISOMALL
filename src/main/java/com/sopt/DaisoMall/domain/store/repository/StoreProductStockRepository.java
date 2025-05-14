package com.sopt.DaisoMall.domain.store.repository;

import com.sopt.DaisoMall.domain.store.entity.StoreProductStock;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreProductStockRepository extends JpaRepository<StoreProductStock, Long> {

    @Query("""
    SELECT s
      FROM StoreProductStock s
      JOIN s.product        p
      LEFT JOIN p.brand     b
     WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))
        OR LOWER(p.productCode) LIKE LOWER(CONCAT('%', :keyword, '%'))
        OR LOWER(TRIM(b.name)) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    Slice<StoreProductStock> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
