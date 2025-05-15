package com.sopt.DaisoMall.domain.store.repository;

import com.sopt.DaisoMall.domain.store.entity.StoreProductStock;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface StoreStockRepository extends JpaRepository<StoreProductStock, Long> {
    
    Slice<StoreProductStock> findByProductId(Long productId, Pageable pageable);
}
