package pl.marcinkowalczyk.ocr.examples.tess.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import pl.marcinkowalczyk.ocr.examples.tess.exception.TessException;
import pl.marcinkowalczyk.ocr.examples.tess.factory.TessFactory;
import pl.marcinkowalczyk.ocr.examples.tess.parameters.TessParameters;

import java.io.File;

@Service
@Slf4j
@RequiredArgsConstructor
public class TessOcrFileService {

    private final TessFactory tessFactory;

    public String ocrToFileByAbsolutePath(String absolutePath, TessParameters parameters,
                                          RenderedFormatOptions renderedFormatOptions) {
        log.debug("Starting OCR to file...");
        File sourceFile = new File(absolutePath);
        Tesseract tesseract = tessFactory.getTesseractInstance(parameters);
        try {
            File destinationFile = getPathToNewFile(sourceFile, "_new");
            tesseract.createDocumentsWithResults(sourceFile.getAbsolutePath(), destinationFile.getAbsolutePath(),
                    renderedFormatOptions.getValue(), ITessAPI.TessPageIteratorLevel.RIL_PARA);
            log.info("Files {} saved: {}", renderedFormatOptions.getValue(), destinationFile.getAbsolutePath());
            return destinationFile.getAbsolutePath();
        } catch (TesseractException e) {
            throw new TessException(String.format("Can't do OCR with Tesseract at path %s", absolutePath), e);
        }
    }

    private File getPathToNewFile(File file, String suffix) {
        String folder = file.getParent();
        String fileName = file.getName();
        String newFileName = addSuffixBeforeExtension(fileName, suffix);
        return new File(folder, newFileName);
    }

    private String addSuffixBeforeExtension(String fileName, String suffix) {
        String extensionRegex = "(\\.[a-z]{3,4}$)";
        String suffixWithExtension = String.format("%s$1", suffix);
        return fileName.replaceAll(extensionRegex, suffixWithExtension);
    }

}
