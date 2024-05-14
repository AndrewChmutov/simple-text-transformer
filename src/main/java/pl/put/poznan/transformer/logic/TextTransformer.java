package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.config.TransformerConfig;

import java.util.Arrays;
import java.util.Collections;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class TextTransformer implements TextTransformation {

    private final TextTransformation textTransformation;

    public TextTransformer(String[] transforms){
        TextTransformation transformation = new IdentityTransformation();

        Collections.reverse(Arrays.asList(transforms));
        for (String transformationName : transforms) {
            transformation = TransformerConfig.decorateTransformation(transformation, transformationName);
        }

        textTransformation = transformation;
    }

    @Override
    public String transform(String text) {
        // of course, normally it would do something based on the transforms
        return textTransformation.transform(text);
    }
}
