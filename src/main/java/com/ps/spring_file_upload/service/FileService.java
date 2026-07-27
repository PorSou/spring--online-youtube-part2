package com.ps.spring_file_upload.service;

import com.ps.spring_file_upload.exception.InternalServerErrorException;
import com.ps.spring_file_upload.model.entity.FileEntity;
import com.ps.spring_file_upload.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {

    private final StorageService storageService;
    private final FileRepository fileRepository;

    public FileEntity upload(MultipartFile file){
        String fileName =  this.storageService.upload(file);

        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(fileName);
        fileEntity.setOriginalName(file.getOriginalFilename());
        fileEntity.setType(file.getContentType());
        fileEntity.setSize(file.getSize());

        try{
            return this.fileRepository.save(fileEntity);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
