package com.sopt.DaisoMall.domain.image.service;

import com.sopt.DaisoMall.domain.image.dto.request.ProductImageUploadDto;
import com.sopt.DaisoMall.domain.image.exception.InvalidSortOrderException;
import com.sopt.DaisoMall.domain.product.entity.Product;
import com.sopt.DaisoMall.domain.product.entity.ProductImage;
import com.sopt.DaisoMall.domain.product.exception.NotFoundProductException;
import com.sopt.DaisoMall.domain.product.repository.ProductImageRepository;
import com.sopt.DaisoMall.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final S3Service s3Service;

    @Transactional
    public String uploadProductImage(MultipartFile file, ProductImageUploadDto dto) {
        if (dto.sortOrder() < 0) throw new InvalidSortOrderException();

        String category = Boolean.TRUE.equals(dto.isMain()) ? "main" : "detail";

        Product product = productRepository.findById(dto.productId())
                .orElseThrow(NotFoundProductException::new);

        String imageUrl = s3Service.uploadToS3(file, dto.productId(), category);

        ProductImage image = ProductImage.builder()
                .product(product)
                .imageUrl(imageUrl)
                .isMain(Boolean.TRUE.equals(dto.isMain()))
                .sortOrder(dto.sortOrder())
                .build();

        productImageRepository.save(image);
        return imageUrl;
    }
}
