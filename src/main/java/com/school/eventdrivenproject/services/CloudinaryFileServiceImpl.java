package com.school.eventdrivenproject.services;

import com.cloudinary.Cloudinary;
import com.school.eventdrivenproject.services.interfaces.IFileService;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CloudinaryFileServiceImpl implements IFileService {


    private String cloudName = "dmwtuuczm";

    private String apiKey = "636124945342724";

    private String apiSecret = "JhCXb50sFMHvCdKNgg9fx5DfQkU";

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
