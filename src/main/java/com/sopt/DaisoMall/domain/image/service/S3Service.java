package com.sopt.DaisoMall.domain.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.sopt.DaisoMall.domain.image.exception.FileUploadFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // S3 업로드
    public String uploadToS3(MultipartFile file, String key){
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket, key, inputStream, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new FileUploadFailedException();
        }
        return amazonS3.getUrl(bucket, key).toString();
    }

    // productId와 관련된 디렉토리
    public String getPrefix(String type, long productId){
        return String.format("%s/%d/", type, productId);
    }

    // S3에 있는 파일 삭제
    public void deleteFromS3(String prefix){
        ListObjectsV2Request listRequest = new ListObjectsV2Request()
                .withBucketName(bucket)
                .withPrefix(prefix);

        ListObjectsV2Result result;
        List<DeleteObjectsRequest.KeyVersion> keysToDelete = new ArrayList<>();

        do {
            result = amazonS3.listObjectsV2(listRequest);
            for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                keysToDelete.add(new DeleteObjectsRequest.KeyVersion(objectSummary.getKey()));
            }
            listRequest.setContinuationToken(result.getNextContinuationToken());
        } while (result.isTruncated());

        if (!keysToDelete.isEmpty()) {
            DeleteObjectsRequest deleteRequest = new DeleteObjectsRequest(bucket)
                    .withKeys(keysToDelete)
                    .withQuiet(true);
            amazonS3.deleteObjects(deleteRequest);
        }
    }
}
