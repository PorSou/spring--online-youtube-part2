package com.ps.spring_file_upload.model.response.file;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileSizeResponse {

    private Long originalValue;
    private Double formatValue;
    private String formatType;
    private String normalized;

    public static FileSizeResponse fromBytes(Long bytes) {

        FileSizeResponse response = new FileSizeResponse();
        response.setOriginalValue(bytes);

        double value = bytes;
        String type = "B";

        if (value >= 1024) {
            value /= 1024;
            type = "KB";
        }

        if (value >= 1024) {
            value /= 1024;
            type = "MB";
        }

        if (value >= 1024) {
            value /= 1024;
            type = "GB";
        }

        value = Math.round(value * 100.0) / 100.0;

        response.setFormatValue(value);
        response.setFormatType(type);
        response.setNormalized(value + " " + type);

        return response;
    }
}


//"size":{
//        "originalValue":215266,
//        "formatValue":215.27
//        "formatType":"KB",        //GB , KG, MB
//        "normalized":"215.27 KB"
//   }
