package org.example.s3;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Component
@ConditionalOnProperty(name = "aws.s3", havingValue = "disable", matchIfMissing = true)
public class AwsS3ClientBuilderDev implements AwsS3ClientBuilder {
    @Override
    public AwsS3Client build() {
        S3Client s3Client = S3Client.builder()
                .credentialsProvider(() -> AwsBasicCredentials.create("sample", "sample"))
                .region(Region.AP_NORTHEAST_1)
                .forcePathStyle(true)
                .endpointOverride(URI.create("http://localhost:4566"))
                .build();

        return new AwsS3Client(s3Client);
    }
}
