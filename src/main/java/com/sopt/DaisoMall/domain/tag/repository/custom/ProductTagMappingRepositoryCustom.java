package com.sopt.DaisoMall.domain.tag.repository.custom;

import java.util.List;
import java.util.Map;

public interface ProductTagMappingRepositoryCustom {

    Map<Long, List<String>> fetchTagsGroupedByProduct(List<Long> productIds);
}
