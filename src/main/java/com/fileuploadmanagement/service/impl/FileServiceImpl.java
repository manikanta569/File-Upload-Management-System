package com.fileuploadmanagement.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fileuploadmanagement.model.FileEntity;
import com.fileuploadmanagement.repository.FileRepository;
import com.fileuploadmanagement.service.FileService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void storeFile(MultipartFile file) {
        try {
            FileEntity fileEntity = FileEntity.builder()
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .fileData(file.getBytes())
                    .uploadTime(LocalDateTime.now())
                    .build();
            fileRepository.save(fileEntity);
        } catch (IOException e) {
            throw new RuntimeException("Error saving file", e);
        }
    }

    @Override
    public Optional<FileEntity> getFileById(Long fileId) {
        return fileRepository.findById(fileId);
    }

    @Override
    public boolean deleteFile(Long fileId) {
        if (fileRepository.existsById(fileId)) {
            fileRepository.deleteById(fileId);
            return true;
        }
        return false;
    }
}