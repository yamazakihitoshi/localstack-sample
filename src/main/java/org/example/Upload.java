package org.example;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;

public class Upload {
    public void execute() throws IOException {
        try (S3Client s3Client = S3Client.builder()
                .credentialsProvider(() -> AwsBasicCredentials.create("sample", "sample"))
                .region(Region.AP_NORTHEAST_1)
                .forcePathStyle(true)
                .endpointOverride(URI.create("http://localhost:4566"))
                .build()) {

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket("sample-bucket")
                    .key("upload.txt")
                    .build();

            File file = new File(Main.class.getResource("/upload.txt").getFile());
            byte[] data = Files.readAllBytes(file.toPath());

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(data));
        }
    }
}
