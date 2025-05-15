package com.sopt.DaisoMall.domain.product.repository;

import com.sopt.DaisoMall.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findTop20ByOrderById();
}
