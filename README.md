# ocr-examples

OCR examples with Tesseract

## Running

Prerequisites: installed JDK 11.

1. Build application:
    - Linux: `./mvnw clean package` 
    - Windows: `mvnw.cmd clean package`  
2. Run application: `java -jar target/ocr-examples-0.0.1-SNAPSHOT.jar`
3. Open browser with URL: http://localhost:8080/

## Usage

Send HTTP request. Provide an absolute path to an image file for OCR as a parameter.
```
http://localhost:8080/api/tess/path?absolute=<absolute_path_to_image_file>
```

Example:
```
http://localhost:8080/api/tess/path?absolute=C:/dev/ocr/ocr-examples/src/test/resources/test_image.png
```