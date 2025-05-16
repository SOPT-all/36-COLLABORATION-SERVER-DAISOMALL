package com.sopt.DaisoMall.global.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sopt.DaisoMall.domain.image.exception.FileUploadFailedException;
import com.sopt.DaisoMall.domain.image.exception.InvalidFileNameException;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class S3Utils {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile file, String dirName) {
        String originalName = Optional.ofNullable(file.getOriginalFilename())
                .orElseThrow(InvalidFileNameException::new);

        String fileName = generateFileName(dirName, originalName);

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            amazonS3.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new FileUploadFailedException();
        }

        return amazonS3.getUrl(bucket, fileName).toString();
    }

    private String generateFileName(String dirName, String originalName) {
        String ext = originalName.substring(originalName.lastIndexOf("."));
        return dirName + "/" + UUID.randomUUID() + ext;
    }
}
