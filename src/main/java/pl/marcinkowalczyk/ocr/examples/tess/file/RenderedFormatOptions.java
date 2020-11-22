package pl.marcinkowalczyk.ocr.examples.tess.file;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.ITesseract;

import java.util.List;

/**
 * @see ITesseract.RenderedFormat
 */
@RequiredArgsConstructor
@Getter
public enum RenderedFormatOptions {

    PDF(List.of(ITesseract.RenderedFormat.PDF)),
    PDF_AND_TEXT(List.of(ITesseract.RenderedFormat.PDF, ITesseract.RenderedFormat.TEXT)),
    ALL(List.of(ITesseract.RenderedFormat.values()));

    private final List<ITesseract.RenderedFormat> value;
}
