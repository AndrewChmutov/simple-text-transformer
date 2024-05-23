package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

/**
 * This class is a concrete decorator used to transform the given text to a Latex format.
 * Namely, the tasks are to convert:
 *   $ -> \\$
 *   & -> \\&
 */
public class LatexDecorator extends TransformationDecorator {

    /**
     * LatexDecorator constructor
     * @param textTransformation object to be wrapped
     */
    public LatexDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    /**
     * Method is used to transform the string to a Latex format.
     * @param text string input by the user
     * @return text transformed according to class description
     */
    @Override
    public String transform(String text) {
        String result = text;

        result = result.replace("&", "\\&");
        result = result.replace("$", "\\$");

        logger.debug("Latex applied: " + result);

        return super.transform(result);
    }
}
