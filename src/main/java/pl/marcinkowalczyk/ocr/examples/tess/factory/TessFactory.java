package pl.marcinkowalczyk.ocr.examples.tess.factory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.marcinkowalczyk.ocr.examples.tess.parameters.TessParameters;

import java.io.File;

@Slf4j
@Component
@RequiredArgsConstructor
public class TessFactory {

    private final FactoryProperties factoryProperties;

    public Tesseract getTesseractInstance(TessParameters parameters) {
        log.debug("Tesseract parameters {}", parameters);
        Tesseract tesseract = new Tesseract();
        File tessDataFolder = getDataFolder(factoryProperties.getDataPath());
        tesseract.setDatapath(tessDataFolder.getAbsolutePath());
        tesseract.setOcrEngineMode(parameters.getEngineMode().getValue());
        tesseract.setPageSegMode(parameters.getPageSegmentationMode().getValue());
        tesseract.setHocr(parameters.isHOcr());
        tesseract.setLanguage(parameters.getLanguages());
        DictionaryDownloader.downloadDictIfNeeded(parameters.getLanguages(), tessDataFolder);
        tesseract.setTessVariable("user_defined_dpi", "300");
        return tesseract;
    }

    private File getDataFolder(String dataPath) {
        if (StringUtils.hasText(dataPath)) {
            File dataPathFile = new File(dataPath);
            if (dataPathFile.exists()) {
                log.debug("Tesseract data path: {}", dataPathFile.getAbsolutePath());
                return dataPathFile;
            }
        }
        return getDefaultDataFolder();
    }

    private File getDefaultDataFolder() {
        File extractFolder = LoadLibs.extractTessResources("tessdata");
        log.debug("Tesseract data path set to default extract folder: {}", extractFolder.getAbsolutePath());
        return extractFolder;
    }
}
