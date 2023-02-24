package org.example.s3;

import org.example.Application;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.*;
import java.nio.file.Files;

public class AwsS3Client {
    final S3Client s3Client;

    public AwsS3Client(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void put(String bucketName, String fileName) {
        try (s3Client) {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            File file = new File(Application.class.getResource("/" + fileName).getFile());
            byte[] data = Files.readAllBytes(file.toPath());

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void get(String bucketName, String fileName) {
        try (s3Client) {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            try (InputStream inputStream = s3Client.getObject(getObjectRequest);
                 OutputStream outputStream = new FileOutputStream("download.txt");
            ) {
                byte[] data = new byte[1024];
                int bytes;
                while((bytes = inputStream.read(data)) != -1) {
                    outputStream.write(data, 0, bytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
