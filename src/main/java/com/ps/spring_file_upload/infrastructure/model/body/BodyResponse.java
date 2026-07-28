package com.ps.spring_file_upload.infrastructure.model.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BodyResponse {

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Object data;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonProperty("page")
    private PageResponse pageResponse;
}
