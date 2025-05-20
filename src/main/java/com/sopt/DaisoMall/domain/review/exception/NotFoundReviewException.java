package com.sopt.DaisoMall.domain.review.exception;

import com.sopt.DaisoMall.domain.product.exception.ErrorMessage;
import com.sopt.DaisoMall.global.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class NotFoundReviewException extends BaseException {
    public NotFoundReviewException() {
        super(HttpStatus.NOT_FOUND, ErrorMessage.NOT_FOUND_PRODUCT.getMessage());
    }
}

