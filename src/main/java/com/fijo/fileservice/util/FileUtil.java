package com.fijo.fileservice.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static String uploadFile(MultipartFile multipartFile, String dirPath) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String localFileName = System.currentTimeMillis() + fileSuffix;
        String filePath = dirPath + File.separator + localFileName;
        File localFile = new File(filePath);
        File imagePath = new File(dirPath);
        if (!imagePath.exists()) {
            imagePath.mkdirs();
        }
        multipartFile.transferTo(localFile);
        return localFileName;

    }
}
