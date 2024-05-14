package pl.put.poznan.transformer.logic.decorators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.transformer.logic.TextTransformation;

public class TransformationDecorator implements TextTransformation {

    private final TextTransformation textTransformation;
    protected final Logger logger = LoggerFactory.getLogger(TransformationDecorator.class);

    public TransformationDecorator(TextTransformation textTransformation) {
        this.textTransformation = textTransformation;
    }

    @Override
    public String transform(String text) {
        return textTransformation.transform(text);
    }
}
