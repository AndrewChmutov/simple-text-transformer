package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

public class LowerDecorator extends TransformationDecorator {

    public LowerDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    @Override
    public String transform(String text) {
        return super.transform(text.toLowerCase());
    }
}
