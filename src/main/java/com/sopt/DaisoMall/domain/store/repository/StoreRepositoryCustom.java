package com.sopt.DaisoMall.domain.store.repository;

import com.sopt.DaisoMall.domain.store.dto.response.StoreSearchResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface StoreRepositoryCustom {
    Slice<StoreSearchResponse> searchStoreWithPriorityByName(
            Long productId,
            String keyword,
            Pageable pageable
    );
}
