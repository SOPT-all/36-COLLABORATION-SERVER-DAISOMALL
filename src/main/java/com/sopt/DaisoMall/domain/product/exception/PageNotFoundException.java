package com.sopt.DaisoMall.domain.product.exception;

import com.sopt.DaisoMall.global.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class PageNotFoundException extends BaseException {
  public PageNotFoundException() {
    super(HttpStatus.NOT_FOUND, ErrorMessage.NOT_FOUND_PAGE.getMessage());
  }
}
