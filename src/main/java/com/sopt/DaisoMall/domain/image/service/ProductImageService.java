package com.sopt.DaisoMall.domain.image.service;

import com.sopt.DaisoMall.domain.image.dto.request.ProductImageUploadDto;
import com.sopt.DaisoMall.domain.product.entity.Product;
import com.sopt.DaisoMall.domain.product.entity.ProductImage;
import com.sopt.DaisoMall.domain.product.exception.NotFoundProductException;
import com.sopt.DaisoMall.domain.product.repository.ProductImageRepository;
import com.sopt.DaisoMall.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    @Transactional
    public void saveProductImage(ProductImageUploadDto dto, String imageUrl){
        Product product = productRepository.findById(dto.productId())
                .orElseThrow(NotFoundProductException::new);

        ProductImage image = ProductImage.builder()
                .product(product)
                .imageUrl(imageUrl)
                .isMain(Boolean.TRUE.equals(dto.isMain()))
                .sortOrder(dto.sortOrder())
                .build();

        productImageRepository.save(image);
    }

    @Transactional
    public void deleteProductImage(long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::new);

        productImageRepository.deleteByProduct(product);
    }

    public String getProductImageKey(MultipartFile file, long productId, boolean isMain){
        String category = Boolean.TRUE.equals(isMain) ? "main" : "detail";
        String fileName = generateFileName(file);

        return String.format("products/%d/%s/%s", productId, category, fileName);
    }

    // 파일 이름 -> UUID로 설정
    private String generateFileName(MultipartFile file) {
        String extension = "";
        String original = file.getOriginalFilename();
        if (original != null && original.contains(".")) {
            extension = original.substring(original.lastIndexOf("."));
        }
        return UUID.randomUUID() + extension;
    }
}
