package pl.marcinkowalczyk.ocr.examples.tesseract;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTesseractExampleTest {

    @Test
    void simplestExample() {
        File imageFile = FileProvider.getFile("test_image.png");
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(LoadLibs.extractTessResources("tessdata").getAbsolutePath());
        try {
            String result = tesseract.doOCR(imageFile);
            String expectedText = "Test image with text\n1234567890\n";
            assertEquals(expectedText, result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    void simpleHOcrExample() {
        File imageFile = FileProvider.getFile("test_image.png");
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(LoadLibs.extractTessResources("tessdata").getAbsolutePath());
        tesseract.setHocr(true);
        try {
            String result = tesseract.doOCR(imageFile);
            String expectedHOcr = FileProvider.getTextFile("test_image.hocr");
            assertEquals(expectedHOcr, result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
