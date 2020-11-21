package pl.marcinkowalczyk.ocr.examples.tess;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;
import pl.marcinkowalczyk.ocr.examples.tess.parameters.TessParameters;

import java.io.File;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TessFactory {

    public static Tesseract getTesseractInstance(TessParameters parameters) {
        log.debug("Tesseract parameters {}", parameters);
        Tesseract tesseract = new Tesseract();
        setDefaultDataPath(tesseract);
        tesseract.setOcrEngineMode(parameters.getEngineMode().getValue());
        tesseract.setPageSegMode(parameters.getPageSegmentationMode().getValue());
        return tesseract;
    }

    private static void setDefaultDataPath(Tesseract tesseract) {
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        tesseract.setDatapath(tessDataFolder.getAbsolutePath());
    }
}
