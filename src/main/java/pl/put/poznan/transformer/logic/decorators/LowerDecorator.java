package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

public class LowerDecorator extends TransformationDecorator {

    public LowerDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    @Override
    public String transform(String text) {
        String result = text.toLowerCase();
        logger.debug("Lower applied: " + result);

        return super.transform(result);
    }
}
