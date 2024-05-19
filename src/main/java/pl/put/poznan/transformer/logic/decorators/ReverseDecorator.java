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
        int n = text.length();
        StringBuilder builder = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            char current = text.charAt(i);
            char currentRev = text.charAt(n - 1 - i);

            char preservedCase = Character.isUpperCase(current)
                ? Character.toUpperCase(currentRev)
                : Character.toLowerCase(currentRev);

            builder.append(preservedCase);
        }

        String result = builder.toString();
        logger.debug("Reverse applied: " + result);

        return super.transform(result);
    }
}
