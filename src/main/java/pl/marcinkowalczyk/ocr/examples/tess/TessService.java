package pl.marcinkowalczyk.ocr.examples.tess;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class TessService {

    public String ocr(String absolutePath) {
        File imageFile = new File(absolutePath);
        Tesseract tesseract = getTesseractInstance();
        try {
            String text = tesseract.doOCR(imageFile);
            log.info(text);
            return text;
        } catch (TesseractException e) {
            throw new TessException(String.format("Can't do OCR with Tesseract at path %s", absolutePath), e);
        }
    }

    private Tesseract getTesseractInstance() {
        Tesseract tesseract = new Tesseract();
        setDefaultDatapath(tesseract);
        return tesseract;
    }

    private void setDefaultDatapath(Tesseract tesseract) {
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        tesseract.setDatapath(tessDataFolder.getAbsolutePath());
    }
}
