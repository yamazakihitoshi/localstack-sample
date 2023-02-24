package org.example;

import lombok.RequiredArgsConstructor;
import org.example.s3.AwsS3Client;
import org.example.s3.AwsS3ClientBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class AwsS3ClientController {
    final AwsS3ClientBuilder awsS3ClientBuilder;

    @GetMapping
    public void get() {
        AwsS3Client awsS3Client = awsS3ClientBuilder.build();
        awsS3Client.put("sample-bucket", "upload.txt");

        awsS3Client = awsS3ClientBuilder.build();
        awsS3Client.get("sample-bucket", "upload.txt");
    }
}
