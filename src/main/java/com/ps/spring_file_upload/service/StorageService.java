package com.ps.spring_file_upload.service;

import com.ps.spring_file_upload.exception.InternalServerErrorException;
import com.ps.spring_file_upload.exception.NotFoundException;
import com.ps.spring_file_upload.util.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService {

    private static final String FILE_PATH = System.getProperty("user.dir") + "/files";

    public String upload(MultipartFile file) {
        return FileUtil.saveMultiPartFile(file, FILE_PATH);
    }

    public List<String> batchUpload(List<MultipartFile> files) {

        List<String> fileNames = new ArrayList<>();

        for (MultipartFile file : files) {
            fileNames.add(upload(file));
        }

        return fileNames;
    }

    public void loadFile(String fileName, HttpServletResponse response) {

        try {

            Path path = Paths.get(FILE_PATH).resolve(fileName);

            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new NotFoundException("File is not found!");
            }

            response.setContentType(Files.probeContentType(path));
            response.setContentLengthLong(Files.size(path));
            response.setHeader(
                    HttpHeaders.CONTENT_DISPOSITION,
                    "inline; filename=\"" + fileName + "\""
            );

            FileCopyUtils.copy(resource.getInputStream(), response.getOutputStream());
            response.flushBuffer();

        } catch (Exception e) {

            if (e instanceof NotFoundException) {
                throw (NotFoundException) e;
            }

            throw new InternalServerErrorException(e.getMessage());
        }
    }
}

//    public void loadFile(String fileName, HttpServletResponse response){
//        try{
//            Path pth = Paths.get(FILE_PATH).resolve(fileName);
////            System.out.println("Path:" + pth);
//            Resource resource = new UrlResource(pth.toUri());
////            System.out.println("Resource: " + resource);
//
//            if(!resource.exists() || !resource.isReadable()){
//                throw new NotFoundException("File is not found!");
//            }
//
//            response.setHeader(HttpHeaders.CONTENT_TYPE, Files.probeContentType(pth));
//            response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(Files.size(pth)));
//            // for this file download automatically
////            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "\"" + fileName + "\"");
//            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + "\"" + fileName + "\"");
//
//            // copy file or image to see on browser
//            FileCopyUtils.copy(resource.getInputStream(), response.getOutputStream());
//
//        } catch (Exception e) {
//            if(e instanceof NotFoundException) throw new NotFoundException(e.getMessage());
//            throw new InternalServerErrorException(e.getMessage());
//        }
//    }

