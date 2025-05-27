package com.sopt.DaisoMall.domain.tag.repository;

import com.sopt.DaisoMall.domain.tag.entity.ProductTagMapping;
import com.sopt.DaisoMall.domain.tag.repository.custom.ProductTagMappingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductTagMappingRepository extends JpaRepository<ProductTagMapping, Long>, ProductTagMappingRepositoryCustom {

}
