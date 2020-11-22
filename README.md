# OCR examples

OCR examples with Tesseract

<!--ts-->
   * [OCR examples](#ocr-examples)
   * [Features](#features)
      * [API for testing Tesseract](#api-for-testing-tesseract)
      * [Examples of usage](#examples-of-usage)
   * [Running](#running)
   * [Usage](#usage)
      * [Simple usage](#simple-usage)
      * [Swagger](#swagger)

<!-- Added by: user, at: Sun Nov 22 19:26:27 CET 2020 -->

<!--te-->
<!-- ghtoc --insert --no-backup README.md -->
<!-- https://github.com/ekalinin/github-markdown-toc -->

# Features

Application that uses [Tesseract](https://github.com/tesseract-ocr/tesseract) 
and [Tess4J](https://github.com/nguyenq/tess4j) to provide REST API for testing various options. 
Additionally, some snippets (very simple examples) in tests. 

## API for testing Tesseract

* OCR image by providing absolute path to file
* OCR image by sending file
* Selecting Tesseract engine mode and page segmentation mode
* Return result in text or HOcr
* Specifying languages (missing dictionaries will be automatically downloaded)
* Saving a file after OCR (text file, PDF with text layer)

Look at Swagger for details: http://localhost:8080/swagger-ui/

<sup>You have to have running application locally - see below.</sup>

![Swagger endpoints](images/swagger-endpoints.png)

![Swagger tess-ocr-text ocrByImage](images/swagger-tess-ocr-text-by-image.png)

## Examples of usage

* The simplest usage of Tesseract
* Generating HOcr
* OCR from PDF file using PDFBox

Look at test folder for details: [pl.marcinkowalczyk.ocr.examples.tesseract](src/test/java/pl/marcinkowalczyk/ocr/examples/tesseract).

# Running

Prerequisites: installed JDK 11 (you can use [AdoptOpenJDK](https://adoptopenjdk.net/)).

1. Build application:
    - Linux: `./mvnw clean package` 
    - Windows: `mvnw.cmd clean package`  
2. Run application: `java -jar target/ocr-examples-0.0.1-SNAPSHOT.jar`
3. Open browser with URL: http://localhost:8080/

# Usage

## Simple usage

Send HTTP request. Provide an absolute path to an image file for OCR as a parameter.
```
http://localhost:8080/api/tess/path?absolute=<absolute_path_to_image_file>
```

Example:
```
http://localhost:8080/api/tess/path?absolute=C:/dev/ocr/ocr-examples/src/test/resources/test_image.png
```

## Swagger

For more endpoints and parameters explore Swagger: http://localhost:8080/swagger-ui/