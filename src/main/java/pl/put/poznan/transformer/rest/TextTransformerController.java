package pl.put.poznan.transformer.rest;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;

import java.util.Arrays;


@RestController
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    private static String transform(String text, String[] transforms) {
        logger.debug("Parameters: " + Arrays.toString(transforms));
        logger.debug("Text: " + text);

        TextTransformer transformer = new TextTransformer(transforms);
        final String result = transformer.transform(text);

        logger.debug("Transformed text: " + result);
        return result;
    }

    private static String textToJsonFormat(String output) {
        return JsonPath
                .parse("{}")
                .put("$", "output", output)
                .jsonString();
    }

    @GetMapping(path = "/get", produces = "application/json")
    public String get(@RequestParam(value = "q", defaultValue = "") String text,
                              @RequestParam(value="transforms", defaultValue="upper,escape") String[] transforms) {

        logger.info("Serving GET request");

        final String output = transform(text, transforms);
        return textToJsonFormat(output);
    }

    @PostMapping(path = "/post", produces = "application/json")
    public @ResponseBody String post(@RequestBody TransformRequest request) {

        logger.info("Serving POST request");

        final String output = transform(request.getInput(), request.getTransforms());
        return textToJsonFormat(output);
    }
}


