package pl.marcinkowalczyk.ocr.examples.tess;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.marcinkowalczyk.ocr.examples.tess.parameters.TessParameters;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class TessService {

    public String ocrByAbsolutePath(String absolutePath, TessParameters parameters) {
        log.debug("Starting OCR...");
        File imageFile = new File(absolutePath);
        Tesseract tesseract = TessFactory.getTesseractInstance(parameters);
        try {
            String text = tesseract.doOCR(imageFile);
            log.info("Text: {}", text);
            return text;
        } catch (TesseractException e) {
            throw new TessException(String.format("Can't do OCR with Tesseract at path %s", absolutePath), e);
        }
    }

    public String ocrByImage(MultipartFile imageFile, TessParameters parameters) {
        log.debug("Starting OCR...");
        Tesseract tesseract = TessFactory.getTesseractInstance(parameters);
        try {
            BufferedImage image = ImageIO.read(imageFile.getInputStream());
            String text = tesseract.doOCR(image);
            log.info("Text: {}", text);
            return text;
        } catch (TesseractException | IOException e) {
            throw new TessException(String.format("Can't do OCR with Tesseract with file name %s",
                    imageFile.getName()), e);
        }
    }
}
