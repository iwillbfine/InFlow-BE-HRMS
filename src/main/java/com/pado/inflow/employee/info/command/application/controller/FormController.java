package com.pado.inflow.employee.info.command.application.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController("formController")
@RequestMapping("/api/forms")
public class FormController {

    private final AmazonS3Client s3Client;
    private final String inflowExcelFormBucket;

    public FormController(AmazonS3Client s3Client,
                          @Value("${cloud.aws.s3.buckets.inflow-excel-form}") String inflowExcelFormBucket) {
        this.s3Client = s3Client;
        this.inflowExcelFormBucket = inflowExcelFormBucket;
    }

    /**
     * 파일 타입에 따라 S3에서 엑셀 파일의 Pre-signed URL을 생성하여 반환
     *
     * @param fileType 요청 파일의 유형 (예: "family_form", "career_registration")
     * @return 파일 다운로드 URL
     */
    @GetMapping("/download")
    public ResponseDTO<?> getFormFile(@RequestParam("file_type") String fileType) {
        // 파일 이름 매핑
        String fileName = mapFileTypeToFileName(fileType);

        // 파일이 없을 경우 예외 처리
        if (fileName == null) {
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }

        // S3에서 Pre-signed URL 생성
        String downloadUrl= s3Client.generatePresignedUrl(inflowExcelFormBucket, fileName,
                        new Date(System.currentTimeMillis() + 1000 * 60 * 10)) // 10분 유효
                .toString();

        //다운 실패시 에러 코드
        if (downloadUrl == null) {
            throw new CommonException(ErrorCode.FILE_DOWNLOAD_ERROR);
        }

        // S3에서 Pre-signed URL 생성
        return ResponseDTO.ok(downloadUrl);
    }

    /**
     * 파일 타입과 실제 파일 이름을 매핑
     *
     * @param fileType 요청 파일 유형
     * @return S3에 저장된 파일 이름
     */
    private String mapFileTypeToFileName(String fileType) {
        return switch (fileType) {
            // 설명. 람다 형식으로 바로 return(break 필요x)
            case "family" -> "family_form.xlsx";
            case "career" -> "career_form.xlsx";
            case "rewards_penalties" -> "rewards_penalties_form.xlsx";
            case "new_employee" -> "new_employee_form.xlsx";
            case "language" -> "language_form.xlsx";
            case "appointment" -> "appointment_form.xlsx";
            case "qualification" -> "qualification_form.xlsx";
            case "academic" -> "academic_form.xlsx";
            default -> null; // 유효하지 않은 파일 타입
        };
    }

}