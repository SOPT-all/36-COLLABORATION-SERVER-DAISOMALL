package com.sopt.DaisoMall.domain.image.controller;

import com.sopt.DaisoMall.domain.image.service.ProductImageService;
import com.sopt.DaisoMall.domain.image.service.S3Service;
import com.sopt.DaisoMall.domain.image.dto.request.ProductImageUploadDto;
import com.sopt.DaisoMall.global.common.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/s3")
public class S3Controller {

    private final S3Service s3Service;
    private final ProductImageService productImageService;

    @Operation(summary = "(서버 테스트용) Swagger로 사진을 S3에 상품 이미지를 업로드하는 API입니다.")
    @PostMapping(value = "/upload/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> uploadProductImageToS3(@RequestPart("data") @Valid final ProductImageUploadDto productImageUploadDto, @RequestPart("file") MultipartFile file) throws IOException {
        String imageUrl = productImageService.uploadProductImage(file, productImageUploadDto);
        return ApiResponse.response(HttpStatus.OK.value(), ResponseMessage.S3_UPLOAD_SUCCESS.getMessage(), imageUrl);
    }

    @Operation(summary = "(서버 테스트용) productId로 S3에 업로드되어 있는 상품 이미지(메인, 디테일) 모두 삭제")
    @DeleteMapping("/{productId}")
    public ApiResponse<String> deleteProductImageFromS3(@PathVariable("productId") long productId){
        s3Service.deleteProductImageFromS3(productId);
        return ApiResponse.response(HttpStatus.OK.value(), ResponseMessage.S3_FILE_DELETE_SUCCESS.getMessage());
    }
}
