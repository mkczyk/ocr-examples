package pl.marcinkowalczyk.ocr.examples.tess.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;
import org.springframework.stereotype.Component;
import pl.marcinkowalczyk.ocr.examples.tess.parameters.TessParameters;

import java.io.File;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TessFactory {

    public static Tesseract getTesseractInstance(TessParameters parameters) {
        log.debug("Tesseract parameters {}", parameters);
        Tesseract tesseract = new Tesseract();
        File tessDataFolder = getDataFolder();
        tesseract.setDatapath(tessDataFolder.getAbsolutePath());
        tesseract.setOcrEngineMode(parameters.getEngineMode().getValue());
        tesseract.setPageSegMode(parameters.getPageSegmentationMode().getValue());
        tesseract.setHocr(parameters.isHOcr());
        tesseract.setLanguage(parameters.getLanguages());
        DictionaryDownloader.downloadDictIfNeeded(parameters.getLanguages(), tessDataFolder);
        tesseract.setTessVariable("user_defined_dpi", "300");
        return tesseract;
    }

    private static File getDataFolder() {
        return LoadLibs.extractTessResources("tessdata");
    }
}
