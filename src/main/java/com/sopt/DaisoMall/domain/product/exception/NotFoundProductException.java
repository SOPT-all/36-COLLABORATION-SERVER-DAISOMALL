package com.sopt.DaisoMall.domain.product.exception;

import com.sopt.DaisoMall.global.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class NotFoundProductException extends BaseException {
    public NotFoundProductException() {
        super(HttpStatus.NOT_FOUND, ErrorMessage.NOT_FOUND_PRODUCT.getMessage());
    }
}