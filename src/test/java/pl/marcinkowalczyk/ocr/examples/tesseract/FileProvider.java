package pl.marcinkowalczyk.ocr.examples.tesseract;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileProvider {

    @SneakyThrows
    public static File getFile(String fileName) {
        ClassPathResource resource = new ClassPathResource(fileName);
        return resource.getFile();
    }

    @SneakyThrows
    public static String getTextFile(String fileName) {
        File file = getFile(fileName);
        return IOUtils.toString(new FileInputStream(file), StandardCharsets.UTF_8);
    }
}
