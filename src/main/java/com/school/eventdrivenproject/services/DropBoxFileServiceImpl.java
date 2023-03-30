package com.school.eventdrivenproject.services;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.*;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.school.eventdrivenproject.services.interfaces.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;
import java.util.UUID;

@Service
public class DropBoxFileServiceImpl implements IFileService {

    public static final String ACCESS_TOKEN = "aa";
    @Override
    public String upload(ByteArrayOutputStream byteArrayOutputStream) {
        String fileUrl = "";

        try {
            byte[] pdfBytes = byteArrayOutputStream.toByteArray();
            String filename = UUID.randomUUID().toString() + ".pdf";
            fileUrl = uploadFileToDropbox(filename, pdfBytes);
        } catch (IOException | DbxException e) {
            e.printStackTrace();
        }

        return fileUrl;
    }

    private String uploadFileToDropbox(String filename, byte[] pdfBytes) throws IOException, DbxException {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/spring-boot").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        try (InputStream in = new ByteArrayInputStream(pdfBytes)) {
            FileMetadata uploader = client.files().uploadBuilder("/postmen/" + filename)
                    .withMode(WriteMode.ADD)
                    .withClientModified(new Date())
                    .withAutorename(true)
                    .uploadAndFinish(in);

            SharedLinkMetadata metadata = client.sharing().createSharedLinkWithSettings("/postmen/" + filename);

            return metadata.getUrl();
        }
    }

}