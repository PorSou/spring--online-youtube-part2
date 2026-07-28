package com.ps.spring_file_upload.controller.frontend;

import com.ps.spring_file_upload.constant.RestURIConstant;
import com.ps.spring_file_upload.infrastructure.model.body.PageResponse;
import com.ps.spring_file_upload.model.response.file.ResponseUtilFullUI;
import com.ps.spring_file_upload.infrastructure.model.body.BaseBodyResponse;
import com.ps.spring_file_upload.model.entity.FileEntity;
import com.ps.spring_file_upload.model.response.file.FileResponse;
import com.ps.spring_file_upload.property.AppProperty;
import com.ps.spring_file_upload.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Frontend File Controller", description = "Controller for user manage file upload")
@RestController
@RequestMapping(value = RestURIConstant.FILE)
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final AppProperty property;

    @Operation(
            summary = "Endpoint that user can upload file", description = "this is description for user can upload file",
            responses = {
                    @ApiResponse
                            (description = "success", responseCode = "200",
                                    content = @Content(schema = @Schema(implementation = FileResponse.class),mediaType = "application/json")),

                    @ApiResponse
                            (description = "error", responseCode = "400-500",
                                    content = @Content(schema = @Schema(implementation = BaseBodyResponse.class),mediaType = "application/json"))
            }
    )
    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<BaseBodyResponse> upload
            (@RequestPart("file") MultipartFile file){
        FileEntity data = this.fileService.upload(file);

        return ResponseUtilFullUI.buildSuccessResponse(FileResponse.toResponse(data,this.property), "Uploading successfully!");
    }

    @Operation(
            summary = "Endpoint that user can upload file", description = "this is description for user can upload file",
            responses = {
                    @ApiResponse
                            (description = "success", responseCode = "200",
                                    content = @Content(schema = @Schema(implementation = BaseBodyResponse.class),mediaType = "application/json")),

                    @ApiResponse
                            (description = "error", responseCode = "400-500",
                                    content = @Content(schema = @Schema(implementation = BaseBodyResponse.class),mediaType = "application/json"))
            }
    )
    @PostMapping(value = "/batch-upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<BaseBodyResponse> batchUpload(@RequestPart("files") List<MultipartFile> files){
        List<FileEntity> data = this.fileService.batchUpload(files);

        return ResponseUtilFullUI.buildSuccessResponse(FileResponse.toResponse(data,this.property),"Uploading All Successfully");
    }

    @GetMapping("/load/{fileName}")
    public void loadFile(@PathVariable String fileName, HttpServletResponse response){
        fileService.loadFile(fileName, response);
//        ResponseEntity.ok("ok");
    }

    @Operation(
            summary = "List uploaded files (paginated)",
            description = "Returns a paginated list of uploaded files"
    )
    @GetMapping
    public ResponseEntity<BaseBodyResponse> getFiles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<FileEntity> filePage = fileService.getFiles(page, size);

        List<FileResponse> data = FileResponse.toResponse(filePage.getContent(), property);
        PageResponse pageResponse = PageResponse.fromPage(filePage);

        return ResponseUtilFullUI.buildSuccessResponse(data, pageResponse, "ok");
    }

}
