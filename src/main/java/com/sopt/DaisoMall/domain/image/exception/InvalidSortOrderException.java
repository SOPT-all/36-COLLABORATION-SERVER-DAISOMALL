package com.sopt.DaisoMall.domain.image.exception;

import com.sopt.DaisoMall.global.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidSortOrderException extends BaseException {
    public InvalidSortOrderException() {
        super(HttpStatus.BAD_REQUEST, ErrorMessage.INVALID_SORT_ORDER.getMessage());
    }
}
