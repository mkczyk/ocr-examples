package pl.marcinkowalczyk.ocr.examples.tess.parameters;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.TessAPI1;


/**
 * Copy from {@link ITessAPI.TessPageSegMode}<br>
 * <br>
 * Possible modes for page layout analysis. These *must* be kept in order of
 * decreasing amount of layout analysis to be done, except for
 * <code>OSD_ONLY</code>, so that the inequality test macros below work.<br>
 *  <br>
 *  Copy from {@link TessAPI1#TessBaseAPISetPageSegMode}<br>
 *  <br>
 *  Defaults to <code>PSM_SINGLE_BLOCK</code>.
 */
@RequiredArgsConstructor
@Getter
public enum TessPageSegmentationMode {

    /**
     * Orientation and script detection only.
     */
    PSM_OSD_ONLY(0),

    /**
     * Automatic page segmentation with orientation and script detection.
     * (OSD)
     */
    PSM_AUTO_OSD(1),

    /**
     * Automatic page segmentation, but no OSD, or OCR.
     */
    PSM_AUTO_ONLY(2),

    /**
     * Fully automatic page segmentation, but no OSD.
     */
    PSM_AUTO(3),

    /**
     * Assume a single column of text of variable sizes.
     */
    PSM_SINGLE_COLUMN(4),

    /**
     * Assume a single uniform block of vertically aligned text.
     */
    PSM_SINGLE_BLOCK_VERT_TEXT(5),

    /**
     * Assume a single uniform block of text.
     */
    PSM_SINGLE_BLOCK(6),

    /**
     * Treat the image as a single text line.
     */
    PSM_SINGLE_LINE(7),

    /**
     * Treat the image as a single word.
     */
    PSM_SINGLE_WORD(8),

    /**
     * Treat the image as a single word in a circle.
     */
    PSM_CIRCLE_WORD(9),

    /**
     * Treat the image as a single character.
     */
    PSM_SINGLE_CHAR(10),

    /**
     * Find as much text as possible in no particular order.
     */
    PSM_SPARSE_TEXT(11),

    /**
     * Sparse text with orientation and script detection.
     */
    PSM_SPARSE_TEXT_OSD(12),

    /**
     * Number of enum entries.
     */
    PSM_COUNT(13);

    private final int value;
}
