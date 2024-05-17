package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

/**
 * This class is a concrete decorator used to reverse the input text
 */
public class ReverseDecorator extends TransformationDecorator {

    /**
     * ReverseDecorator constructor
     * @param textTransformation object to be wrapped
     */
    public ReverseDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    /**
     * This method is used to reverse the given text. Given an input string, it returns a
     * string in reverse
     * @param text string input by the user
     * @return reversed text
     */
    public String transform(String text) {
        StringBuilder builder = new StringBuilder(text);
        String result = builder.reverse().toString();
        logger.debug("Reverse applied: " + result);

        return super.transform(result);
    }
}
