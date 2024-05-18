package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

/**
 * This class is a concrete decorator used to transform the first letters of words
 * in provided text to capital letters
 */
public class CapitalizeDecorator extends TransformationDecorator {

    /**
     * CapitalizeDecorator constructor
     * @param textTransformation object to be wrapped
     */
    public CapitalizeDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    /**
     * This method is used to transorm each word's first letter to a capital one
     * @param text string input by the user
     * @return capitalized text
     */
    @Override
    public String transform(String text) {
        StringBuilder builder = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : text.toCharArray()) {
            if (Character.isWhitespace(c)) {
                builder.append(c);
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    builder.append(Character.toTitleCase(c));
                    capitalizeNext = false;
                } else {
                    builder.append(c);
                }
            }
        }

        String result = builder.toString().trim();
        logger.debug("Capitalize applied: "  + result);

        return super.transform(result);
    }
}
