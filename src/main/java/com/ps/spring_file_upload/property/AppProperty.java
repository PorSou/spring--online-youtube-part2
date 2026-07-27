package com.ps.spring_file_upload.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component // it created bean
@Lazy  // even use this class just @lazy create bean
public class AppProperty {

    @Value("${ps.app.api-url}")
    private String apiUrl;

}
