package com.sopt.DaisoMall.domain.image.controller;

import com.sopt.DaisoMall.domain.image.dto.request.ReviewImageUploadDto;
import com.sopt.DaisoMall.domain.image.entity.enums.ImageType;
import com.sopt.DaisoMall.domain.image.service.ProductImageService;
import com.sopt.DaisoMall.domain.image.service.ReviewImageService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/s3")
public class S3Controller {

    private final S3Service s3Service;
    private final ProductImageService productImageService;
    private final ReviewImageService reviewImageService;

    @Operation(summary = "(서버 테스트용) Swagger로 S3에 상품 이미지를 업로드하는 API입니다.")
    @PostMapping(value = "/upload/product-images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> uploadProductImageToS3(@RequestPart("data") @Valid final ProductImageUploadDto dto, @RequestPart("file") MultipartFile file) {
        String key = productImageService.getProductImageKey(file, dto.productId(), dto.isMain());
        String imageUrl = s3Service.uploadToS3(file, key);
        productImageService.saveProductImage(dto, imageUrl);
        return ApiResponse.response(HttpStatus.OK.value(), ResponseMessage.S3_UPLOAD_SUCCESS.getMessage(), imageUrl);
    }

    @Operation(summary = "(서버 테스트용) Swagger로 S3에 리뷰 이미지를 업로드하는 API입니다.")
    @PostMapping(value = "/upload/review-images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> uploadReviewImageToS3(@RequestPart("data") @Valid final ReviewImageUploadDto dto, @RequestPart("file") MultipartFile file){
        String key = reviewImageService.getReviewImageKey(file, dto.reviewId());
        String imageUrl = s3Service.uploadToS3(file, key);
        reviewImageService.saveReviewImage(dto, imageUrl);
        return ApiResponse.response(HttpStatus.OK.value(), ResponseMessage.S3_UPLOAD_SUCCESS.getMessage(), imageUrl);
    }

    @Operation(summary = "(서버 테스트용) productId로 S3에 업로드되어 있는 상품과 리뷰 이미지 모두 삭제")
    @DeleteMapping("/{productId}")
    public ApiResponse<String> deleteImagesFromS3(@PathVariable("productId") long productId, @RequestParam("type") ImageType type){
        String prefix = s3Service.getPrefix(type.getDisplayName(), productId);
        s3Service.deleteFromS3(prefix);
        productImageService.deleteProductImage(productId);
        reviewImageService.deleteReviewImages(productId);
        return ApiResponse.response(HttpStatus.OK.value(), ResponseMessage.S3_FILE_DELETE_SUCCESS.getMessage());
    }
}
