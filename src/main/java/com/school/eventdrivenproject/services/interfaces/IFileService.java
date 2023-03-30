package com.school.eventdrivenproject.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;

public interface IFileService {

    String upload(ByteArrayOutputStream byteArrayOutputStream);

}
