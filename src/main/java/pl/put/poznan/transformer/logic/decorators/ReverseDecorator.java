package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

public class ReverseDecorator extends TransformationDecorator {

    public ReverseDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    public String transform(String text) {
        StringBuilder builder = new StringBuilder(text);
        String result = builder.reverse().toString();
        logger.debug("Reverse applied: " + result);

        return super.transform(result);
    }
}
