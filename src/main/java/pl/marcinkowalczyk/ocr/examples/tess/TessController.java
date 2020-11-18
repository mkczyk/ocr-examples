package pl.marcinkowalczyk.ocr.examples.tess;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tess")
public class TessController {

    private final TessService tessService;

    @GetMapping("/path")
    public String ocrByAbsolutePath(@RequestParam("absolute") String absolutePath) {
        return tessService.ocr(absolutePath);
    }
}
