package com.sopt.DaisoMall.domain.product.entity.enums;

import com.sopt.DaisoMall.domain.product.exception.SortOptionNotFoundException;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum SortOption {
    NEWEST("최신순", "id", Sort.Direction.DESC),
    PRICE_ASC("가격 낮은 순", "product.price", Sort.Direction.ASC),
    PRICE_DESC("가격 높은 순", "product.price", Sort.Direction.DESC);

    private final String displayName;
    private final String sortProperty;
    private final Sort.Direction direction;

    public static SortOption from(String value) {
        return Arrays.stream(values())
                .filter(option ->
                        option.name().equalsIgnoreCase(value) ||
                                option.getDisplayName().equals(value)
                )
                .findAny()
                .orElseThrow(SortOptionNotFoundException::new);
    }

    public Sort toSort() {
        return Sort.by(direction, sortProperty);
    }
}
