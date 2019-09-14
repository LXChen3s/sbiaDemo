package com.sbia.sbiademo.services.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileServices {
    String saveFile(MultipartFile multipartFile);
}
