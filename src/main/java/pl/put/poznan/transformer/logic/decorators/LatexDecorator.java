package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

public class LatexDecorator extends TransformationDecorator {

    public LatexDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    @Override
    public String transform(String text) {
        String result = text;

        result = result.replace("&", "\\&");
        result = result.replace("$", "\\$");

        logger.debug("Latex applied: " + result);

        return super.transform(result);
    }
}
