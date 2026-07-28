package com.ps.spring_file_upload.service;

import com.ps.spring_file_upload.exception.InternalServerErrorException;
import com.ps.spring_file_upload.model.entity.FileEntity;
import com.ps.spring_file_upload.repository.FileRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final StorageService storageService;
    private final FileRepository fileRepository;

    public FileEntity upload(MultipartFile file) {

        String fileName = storageService.upload(file);

        FileEntity entity = new FileEntity();
        entity.setName(fileName);
        entity.setOriginalName(file.getOriginalFilename());
        entity.setType(file.getContentType());
        entity.setSize(file.getSize());

        try {
            return fileRepository.save(entity);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    public List<FileEntity> batchUpload(List<MultipartFile> files) {

        List<String> fileNames = storageService.batchUpload(files);

        List<FileEntity> entities = new ArrayList<>();

        for (int i = 0; i < files.size(); i++) {

            MultipartFile file = files.get(i);

            FileEntity entity = new FileEntity();
            entity.setName(fileNames.get(i));
            entity.setOriginalName(file.getOriginalFilename());
            entity.setType(file.getContentType());
            entity.setSize(file.getSize());

            entities.add(entity);
        }

        try {
            return fileRepository.saveAll(entities);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    public void loadFile(String fileName, HttpServletResponse response) {
        storageService.loadFile(fileName, response);
    }

    public Page<FileEntity> getFiles(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt"));
        return fileRepository.findAll(pageable);
    }

}