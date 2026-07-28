package com.ps.spring_file_upload.infrastructure.model.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BaseBodyResponse implements Serializable {

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Boolean success;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private BodyResponse body;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private StatusResponse status;

    // i want like
/*{
    "success": true,
    "body": {
        "data": [],
        "page": {
            "totalPage": 1,
            "page": 0,
            "totalCount": 5,
            "pageSize": 3
        }
    },
    "status": {
        "code": 200,
        "message": "ok"
    }
 }
*/

}
