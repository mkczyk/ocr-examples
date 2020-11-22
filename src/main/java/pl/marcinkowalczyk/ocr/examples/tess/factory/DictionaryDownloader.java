package pl.marcinkowalczyk.ocr.examples.tess.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import pl.marcinkowalczyk.ocr.examples.tess.exception.TessException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DictionaryDownloader {

    private static final String DICT_URL = "https://github.com/tesseract-ocr/tessdata/raw/master/";
    private static final String DICT_EXTENSION = ".traineddata";

    static void downloadDictIfNeeded(String languagesParameter, File tessDataFolder) {
        Set<String> languages = Set.of(languagesParameter.split("\\+"));
        Set<String> existingLanguages = getExistingLanguages(tessDataFolder);
        Set<String> missingLanguages = intersect(languages, existingLanguages);
        if (!missingLanguages.isEmpty()) {
            log.info("Existing languages: {}, languages in parameter: {}, missing languages: {}",
                    existingLanguages, languages, missingLanguages);
        }
        missingLanguages.forEach(language -> downloadLanguage(language, tessDataFolder));
    }

    private static void downloadLanguage(String language, File folder) {
        try {
            log.info("Downloading dictionary '{}'...", language);
            String dictFileName = String.format("%s%s", language, DICT_EXTENSION);
            URL dictUrl = new URL(String.format("%s%s", DICT_URL, dictFileName));
            File newFile = new File(folder, dictFileName);
            FileUtils.copyURLToFile(dictUrl, newFile);
            log.info("Dictionary '{}' downloaded from {} and saved to {}", language, dictUrl, newFile);
        } catch (IOException e) {
            throw new TessException(String.format("Can't download %s dictionary", language), e);
        }
    }

    private static Set<String> intersect(Set<String> first, Set<String> second) {
        return first.stream()
                .filter(element -> !second.contains(element))
                .collect(Collectors.toSet());
    }

    private static Set<String> getExistingLanguages(File tessDataFolder) {
        File[] files = tessDataFolder.listFiles();
        if (files == null) {
            throw new IllegalArgumentException(String.format("Error occurred at getting files from %s folder",
                    tessDataFolder.getAbsolutePath()));
        }
        return Stream.of(files)
                .map(File::getName)
                .filter(fileName -> fileName.contains(DICT_EXTENSION))
                .map(fileName -> fileName.replaceAll(DICT_EXTENSION, ""))
                .collect(Collectors.toSet());
    }
}
