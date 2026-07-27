package com.ps.spring_file_upload.util;

import com.ps.spring_file_upload.exception.BadRequestException;
import com.ps.spring_file_upload.exception.InternalServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

public final class FileUtil {
    public static String saveMultiPartFile(MultipartFile file, String path){

        if(file.getSize() <= 0) throw new BadRequestException("No file provided!");

        String fileName = UUID.randomUUID().toString();
        String sourceFileName = !Objects.requireNonNull(file.getOriginalFilename()).isEmpty() ||
                                !Objects.requireNonNull(file.getOriginalFilename()).isBlank() ? file.getOriginalFilename() : file.getName();

        String extension =  sourceFileName.contains(".") ? sourceFileName.substring(sourceFileName.lastIndexOf(".")) : "";
        System.out.println(extension);

        Path pth = !path.isEmpty() || !path.isBlank() ? Paths.get(path) : Paths.get("./");

        try{

            if(Files.notExists(pth)) Files.createDirectory(pth);

            String fullName = fileName + extension;

            file.transferTo(pth.resolve(fullName));

            return fullName;

        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }

    }

}
