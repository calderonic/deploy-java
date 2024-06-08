package com.childcaresolutions.childcare_app.controller;

import com.childcaresolutions.childcare_app.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.util.Map;

@RestController
@RequestMapping("/api/media")
@Tag(name = "Media Management", description = "Operations related to media management")
public class MediaController {

    @Autowired
    private StorageService storageService;

    @Operation(summary = "Upload a file")
    @PostMapping("/upload")
    Map<String, String> upload(@RequestParam("file")MultipartFile multipartFile) {
        String path = storageService.store(multipartFile);
        return Map.of("path", path);
    }

    @GetMapping("/{filename}")
    @Operation(summary = "Get a file")
    ResponseEntity<Resource> getResource(@PathVariable String filename) throws Exception {

        Resource resource =storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(resource.getFile().toPath());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(resource);
    }

}
