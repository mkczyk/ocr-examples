package pl.marcinkowalczyk.ocr.examples.tesseract;

import lombok.SneakyThrows;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PdfExampleTest {

    public static final int DPI = 300;
    public static final int PAGE_NUMBER = 0;

    @Test
    void pdfWithPdfBoxExample() {
        File pdfFile = FileProvider.getFile("test_pdf.pdf");
        BufferedImage bufferedImage = getImageFromPdf(pdfFile);
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(LoadLibs.extractTessResources("tessdata").getAbsolutePath());
        try {
            String result = tesseract.doOCR(bufferedImage);
            String expectedText = "Test pdf with text\n1234567890\n";
            assertEquals(expectedText, result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }

    @SneakyThrows
    private BufferedImage getImageFromPdf(File pdfFile) {
        PDDocument document = PDDocument.load(pdfFile);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(PAGE_NUMBER, DPI, ImageType.RGB);
        document.close();
        return bufferedImage;
    }
}
