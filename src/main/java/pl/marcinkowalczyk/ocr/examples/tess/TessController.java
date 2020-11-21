package pl.marcinkowalczyk.ocr.examples.tess;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tess")
public class TessController {

    private final TessService tessService;

    @GetMapping("/path")
    public String ocrByAbsolutePath(@RequestParam("absolute") String absolutePath) {
        return tessService.ocrByAbsolutePath(absolutePath);
    }

    @PostMapping("/image")
    public String ocrByImage(@RequestPart("file") MultipartFile image) {
        return tessService.ocrByImage(image);
    }
}
