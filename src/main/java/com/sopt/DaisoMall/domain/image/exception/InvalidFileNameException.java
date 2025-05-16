package com.sopt.DaisoMall.domain.image.exception;

import com.sopt.DaisoMall.global.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidFileNameException extends BaseException {
  public InvalidFileNameException() {
    super(HttpStatus.BAD_REQUEST, ErrorMessage.INVALID_FILE_NAME.getMessage());
  }
}
