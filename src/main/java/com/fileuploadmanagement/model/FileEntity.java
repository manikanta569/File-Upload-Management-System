package com.fileuploadmanagement
.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileType;

    @Lob
    @Column(nullable = false)
    private byte[] fileData;  // Stores file content as BLOB

    @Column(nullable = false, updatable = false)
    private LocalDateTime uploadTime;

    @PrePersist
    protected void onCreate() {
        this.uploadTime = LocalDateTime.now();
    }
}
