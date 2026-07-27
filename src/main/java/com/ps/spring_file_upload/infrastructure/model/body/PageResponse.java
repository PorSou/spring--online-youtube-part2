package com.ps.spring_file_upload.infrastructure.model.body;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResponse {

    private Long totalPage;

    private Long page;

    private Long totalCount;

    private Long pageSize;
}
