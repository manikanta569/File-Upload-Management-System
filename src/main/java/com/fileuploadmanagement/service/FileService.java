
package com.fileuploadmanagement.service;

import org.springframework.web.multipart.MultipartFile;
import com.fileuploadmanagement.model.FileEntity;
import java.util.Optional;

public interface FileService {
    void storeFile(MultipartFile file);
    Optional<FileEntity> getFileById(Long fileId);
    boolean deleteFile(Long fileId);
}