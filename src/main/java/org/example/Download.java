package org.example;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.*;
import java.net.URI;

public class Download {
    public void execute() throws IOException {
        try (S3Client s3Client = S3Client.builder()
                .credentialsProvider(() -> AwsBasicCredentials.create("sample", "sample"))
                .region(Region.AP_NORTHEAST_1)
                .forcePathStyle(true)
                .endpointOverride(URI.create("http://localhost:4566"))
                .build()) {

            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket("sample-bucket")
                    .key("upload.txt")
                    .build();

            try (InputStream inputStream = s3Client.getObject(getObjectRequest);
                 OutputStream outputStream = new FileOutputStream("download.txt");
                 ) {
                byte[] data = new byte[1024];
                int bytes;
                while((bytes = inputStream.read(data)) != -1) {
                    outputStream.write(data, 0, bytes);
                }
            }
        }
    }
}
