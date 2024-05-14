package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

public class TransformationDecorator implements TextTransformation {

    private final TextTransformation textTransformation;

    public TransformationDecorator(TextTransformation textTransformation) {
        this.textTransformation = textTransformation;
    }

    @Override
    public String transform(String text) {
        return textTransformation.transform(text);
    }
}
