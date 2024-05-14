package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

public class UpperDecorator extends TransformationDecorator {

    public UpperDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    @Override
    public String transform(String text) {
        return super.transform(text.toUpperCase());
    }
}
