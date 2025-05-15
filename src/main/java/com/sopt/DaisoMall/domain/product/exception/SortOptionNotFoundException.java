package com.sopt.DaisoMall.domain.product.exception;

import com.sopt.DaisoMall.global.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class SortOptionNotFoundException extends BaseException {
  public SortOptionNotFoundException() {
    super(HttpStatus.BAD_REQUEST, ErrorMessage.NOT_FOUND_SORT_OPTION.getMessage());
  }
}
