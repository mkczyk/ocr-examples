package pl.marcinkowalczyk.ocr.examples.tess;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class TessService {

    public String ocrByAbsolutePath(String absolutePath) {
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

    public String ocrByImage(MultipartFile imageFile) {
        Tesseract tesseract = getTesseractInstance();
        try {
            BufferedImage image = ImageIO.read(imageFile.getInputStream());
            String text = tesseract.doOCR(image);
            log.info(text);
            return text;
        } catch (TesseractException | IOException e) {
            throw new TessException(String.format("Can't do OCR with Tesseract with file name %s", imageFile.getName()), e);
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
