package pl.marcinkowalczyk.ocr.examples.tess.factory;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("tess.factory")
@Data
public class FactoryProperties {

    private String dataPath;
}
