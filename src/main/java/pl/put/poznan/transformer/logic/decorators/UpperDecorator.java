package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

public class UpperDecorator extends TransformationDecorator {

    public UpperDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    @Override
    public String transform(String text) {
        String result = text.toUpperCase();
        logger.debug("Upper applied: " + result);

        return super.transform(text.toUpperCase());
    }
}
