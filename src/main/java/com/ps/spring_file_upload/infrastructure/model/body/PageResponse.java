package com.ps.spring_file_upload.infrastructure.model.body;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class PageResponse {

    private Long totalPage;

    private Long page;

    private Long totalCount;

    private Long pageSize;

    public static PageResponse fromPage(Page<?> page) {
        if (page == null) return null;

        PageResponse response = new PageResponse();
        response.setTotalPage((long) page.getTotalPages());
        response.setPage((long) page.getNumber());
        response.setTotalCount(page.getTotalElements());
        response.setPageSize((long) page.getSize());

        return response;
    }

}
