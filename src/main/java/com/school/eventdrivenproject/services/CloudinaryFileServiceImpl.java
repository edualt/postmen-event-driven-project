package com.school.eventdrivenproject.services;

import com.cloudinary.Cloudinary;
import com.school.eventdrivenproject.services.interfaces.IFileService;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CloudinaryFileServiceImpl implements IFileService {

    @Value("${CLOUDINARY_NAME}")
    private String cloudName;

    @Value("${CLOUDINARY_API_KEY}")
    private String apiKey;

    @Value("${CLOUDINARY_API_SECRET}")
    private String apiSecret;

    private final Cloudinary cloudinary = new Cloudinary();

    private final Map<String, String> cloudinaryConfig = new HashMap<>();

    @Override
    public String upload(ByteArrayOutputStream byteArrayOutputStream) {
        String fileUrl = "";

        try {
            byte[] pdfBytes = byteArrayOutputStream.toByteArray();
            String filename = UUID.randomUUID().toString();
            fileUrl = uploadFileToCloudinary(filename, pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> message = new HashMap<>();
        message.put("url", fileUrl);

        return fileUrl;
    }

    private String uploadFileToCloudinary(String fileName, byte[] pdfBytes) throws IOException {
        File tempFile = File.createTempFile("temp", ".pdf");
        FileOutputStream fos = new FileOutputStream(tempFile);
        fos.write(pdfBytes);
        fos.close();

        cloudinaryConfig.put("public_id", fileName);

        Map response = cloudinary.uploader().upload(tempFile, cloudinaryConfig);

        tempFile.delete();
        return (String) response.get("url");
    }

    @PostConstruct
    private void initializeCloudinary() {
        cloudinaryConfig.put("cloud_name", cloudName);
        cloudinaryConfig.put("api_key", apiKey);
        cloudinaryConfig.put("api_secret", apiSecret);
        cloudinaryConfig.put("folder", "order-guide-pdf");
    }
}
