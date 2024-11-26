package com.pado.inflow.vacation.command.application.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.pado.inflow.config.S3Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class VacationS3Service {

    private final AmazonS3Client s3Client;
    private final S3Config s3Config;

    @Autowired
    public VacationS3Service(AmazonS3Client s3Client, S3Config s3Config) {
        this.s3Client = s3Client;
        this.s3Config = s3Config;
    }

    public String uploadFile(MultipartFile file, Long employeeId) {
        String fileName =
                LocalDate.now() + "_" + employeeId + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        String bucketName = s3Config.getInflowVacationAttendanceBucket();
        try {
            s3Client.putObject(bucketName, fileName, file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return s3Client.getUrl(bucketName, fileName).toString();
    }

}
