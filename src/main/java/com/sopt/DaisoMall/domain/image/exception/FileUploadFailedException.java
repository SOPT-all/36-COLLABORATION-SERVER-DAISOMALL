package com.sopt.DaisoMall.domain.image.exception;

import com.sopt.DaisoMall.global.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class FileUploadFailedException extends BaseException {
  public FileUploadFailedException() {
    super(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.FILE_UPLOAD_FAIL.getMessage());
  }
}
