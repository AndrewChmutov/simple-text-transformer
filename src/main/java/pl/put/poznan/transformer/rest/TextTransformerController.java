package pl.put.poznan.transformer.rest;

import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;

import java.util.Arrays;

/**
 * This is a REST controller handling GET and POST requests, transforming the text in a way
 * provided by the user
 */
@RestController
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    private static String transformationProcedure(String text, String[] transforms) {
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

    /**
     * This method handles GET requests at the /get endpoint
     * @param text string input by the user
     * @param transforms transformations requested by the user
     * @return transformed text in Json
     */
    @GetMapping(path = "/get", produces = "application/json")
    public String get(@RequestParam(value = "q", defaultValue = "") String text,
                              @RequestParam(value="transforms", defaultValue="") String[] transforms) {

        logger.info("Serving GET request");

        final String output = transformationProcedure(text, transforms);
        return textToJsonFormat(output);
    }

    /**
     * This method handles POST requests at the /post endpoint
     * @param request the request body containing the input text and transformations
     * @return transformed text in Json
     */

    @PostMapping(path = "/post", produces = "application/json")
    public @ResponseBody String post(@RequestBody TransformRequest request) {

        logger.info("Serving POST request");

        final String output = transformationProcedure(request.getInput(), request.getTransforms());
        return textToJsonFormat(output);
    }
}


