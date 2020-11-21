package pl.marcinkowalczyk.ocr.examples.tess.parameters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TessParameters {

    private TessOcrEngineMode engineMode = TessOcrEngineMode.OEM_DEFAULT;
    private TessPageSegmentationMode pageSegmentationMode = TessPageSegmentationMode.PSM_SINGLE_BLOCK;
}
