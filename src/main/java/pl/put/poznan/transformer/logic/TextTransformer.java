package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.config.TransformerConfig;

import java.util.Arrays;
import java.util.Collections;

/**
 * This class provides a simple way to apply text transformations sequentially
 * from the array of strings. It uses <code>TransformerConfig</code>,
 * to interpret each element of the input.
 *
 * @see TransformerConfig
 */
public class TextTransformer implements TextTransformation {

    /**
     * Final transformation that bundles all the transformations specified in the constructor.
     */
    private final TextTransformation textTransformation;

    /**
     * Constructs a <code>TextTransformation</code> with the specified transformations in strings.
     *
     * @param transforms an array of strings that are used to stack transformations sequentially.
     *                   Each string is interpreted by <code>TransformerConfig</code>
     *
     * @see TransformerConfig
     */
    public TextTransformer(String[] transforms){
        TextTransformation transformation = new IdentityTransformation();

        Collections.reverse(Arrays.asList(transforms));
        for (String transformationName : transforms) {
            transformation = TransformerConfig.decorateTransformation(transformation, transformationName);
        }

        textTransformation = transformation;
    }

    /**
     * Transforms the given text using the transformations that were passed into the constructor
     *
     * @param text a string to be transformed
     * @return text after all transformations
     */
    @Override
    public String transform(String text) {
        // of course, normally it would do something based on the transforms
        return textTransformation.transform(text);
    }
}
