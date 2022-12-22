package example.server.controller;

import example.server.service.main.AttachmentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.UUID;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {
    private final
    AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/upload")
    public HttpEntity<?> uploadFile(MultipartHttpServletRequest request) {
        return ResponseEntity.ok(attachmentService.uploadFile(request));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getFile(@PathVariable UUID id) {
        return attachmentService.getFile(id);
    }

    @GetMapping("getFile/{id}")
    public HttpEntity<?> getFileCode(@PathVariable UUID id) {
        String attachmentContent = attachmentService.getAttachmentContent(id);
                return ResponseEntity.ok(attachmentContent);
    }
}
