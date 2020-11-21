package pl.marcinkowalczyk.ocr.examples.tess;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.marcinkowalczyk.ocr.examples.tess.parameters.TessParameters;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tess")
public class TessController {

    private final TessService tessService;

    @GetMapping("/path")
    public String ocrByAbsolutePath(@RequestParam("absolute") String absolutePath) {
        TessParameters parameters = new TessParameters();
        return tessService.ocrByAbsolutePath(absolutePath, parameters);
    }

    @PostMapping("/path")
    public String ocrByAbsolutePath(@RequestParam("absolute") String absolutePath, TessParameters parameters) {
        return tessService.ocrByAbsolutePath(absolutePath, parameters);
    }

    @PostMapping("/image")
    public String ocrByImage(@RequestPart("file") MultipartFile image, TessParameters parameters) {
        return tessService.ocrByImage(image, parameters);
    }
}
