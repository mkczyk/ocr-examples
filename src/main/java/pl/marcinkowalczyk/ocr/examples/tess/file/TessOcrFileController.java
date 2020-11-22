package pl.marcinkowalczyk.ocr.examples.tess.file;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.marcinkowalczyk.ocr.examples.tess.parameters.TessParameters;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tess/file")
public class TessOcrFileController {

    private final TessOcrFileService tessOcrFileService;

    @PostMapping("/path")
    public String ocrToFileByAbsolutePath(@RequestParam("absolute") String absolutePath, TessParameters parameters,
                                    @RequestParam(defaultValue = "PDF") RenderedFormatOptions renderedFormatOptions) {
        return tessOcrFileService.ocrToFileByAbsolutePath(absolutePath, parameters, renderedFormatOptions);
    }
}
