package com.sopt.DaisoMall.domain.product.exception;

import com.sopt.DaisoMall.global.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends BaseException {
    public CategoryNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorMessage.NOT_FOUND_CATEGORY.getMessage());
    }
}
