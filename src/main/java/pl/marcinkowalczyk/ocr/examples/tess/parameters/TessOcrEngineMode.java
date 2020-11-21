package pl.marcinkowalczyk.ocr.examples.tess.parameters;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.ITessAPI;

/**
 * Copy from {@link ITessAPI.TessOcrEngineMode}.<br>
 * <br>
 * When Tesseract/Cube is initialized we can choose to instantiate/load/run
 * only the Tesseract part, only the Cube part or both along with the
 * combiner. The preference of which engine to use is stored in
 * <code>tessedit_ocr_engine_mode</code>.
 */
@RequiredArgsConstructor
@Getter
public enum TessOcrEngineMode {

    /**
     * Run Tesseract only - fastest
     */
    OEM_TESSERACT_ONLY(0),

    /**
     * Run just the LSTM line recognizer
     */
    OEM_LSTM_ONLY(1),

    /**
     * Run the LSTM recognizer, but allow fallback to Tesseract when things
     * get difficult
     */
    OEM_TESSERACT_LSTM_COMBINED(2),

    /**
     * Specify this mode when calling <code>init_*()</code>, to indicate
     * that any of the above modes should be automatically inferred from the
     * variables in the language-specific config, command-line configs, or
     * if not specified in any of the above should be set to the default
     * <code>OEM_TESSERACT_ONLY</code>.
     */
    OEM_DEFAULT(3);

    private final int value;
}
