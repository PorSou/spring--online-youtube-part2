package com.ps.spring_file_upload.model.response.file;

import com.ps.spring_file_upload.model.entity.FileEntity;
import com.ps.spring_file_upload.property.AppProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class FileResponse {

    private Long id;
    private Date createdAt;
    private String fileName;
    private String originalFileName;
    private String url;
    private FileSizeResponse size;
    private String type;

    public static FileResponse toResponse(FileEntity entity, AppProperty property){
        if(entity == null) return null;

        FileResponse response = new FileResponse();
        response.setId(entity.getId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setFileName(entity.getName());
        response.setOriginalFileName(entity.getOriginalName());
        response.setUrl(property.getApiUrl() + "/file/load/" + entity.getName());

        response.setSize(FileSizeResponse.fromBytes(entity.getSize()));
        response.setType(entity.getType());

        return response;
    }

    public static List<FileResponse> toResponse(List<FileEntity> entities, AppProperty property) {
        if (entities == null) return null;

        return entities.stream()
                .map(entity -> toResponse(entity, property))
                .toList();
    }

}
