package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;
import pl.put.poznan.transformer.logic.IntConverter;
import pl.put.poznan.transformer.logic.FloatConverter;

public class FloatConversionDecorator extends TransformationDecorator {

    public FloatConversionDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    @Override
    public String transform(String text) {
        FloatConverter FC = new FloatConverter();
        String result = FC.convertToText(text);
        logger.debug("FloatConversion applied: " + result);
        
        return super.transform(result);
    }
}