package pl.marcinkowalczyk.ocr.examples.tess.text;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.marcinkowalczyk.ocr.examples.tess.parameters.TessParameters;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tess")
public class TessOcrTextController {

    private final TessOcrTextService tessOcrTextService;

    @GetMapping("/path")
    public String ocrByAbsolutePath(@RequestParam("absolute") String absolutePath) {
        TessParameters parameters = new TessParameters();
        return tessOcrTextService.ocrByAbsolutePath(absolutePath, parameters);
    }

    @PostMapping("/path")
    public String ocrByAbsolutePath(@RequestParam("absolute") String absolutePath, TessParameters parameters) {
        return tessOcrTextService.ocrByAbsolutePath(absolutePath, parameters);
    }

    @PostMapping("/image")
    public String ocrByImage(@RequestPart("file") MultipartFile image, TessParameters parameters) {
        return tessOcrTextService.ocrByImage(image, parameters);
    }
}
