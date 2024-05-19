package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.decorators.converters.IntConverter;
import pl.put.poznan.transformer.logic.decorators.converters.FloatConverter;
import pl.put.poznan.transformer.logic.TextTransformation;

public class NumberConversionDecorator extends TransformationDecorator {

    public NumberConversionDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    @Override
    public String transform(String text) {
        IntConverter IC = new IntConverter();
        FloatConverter FC = new FloatConverter();
        String result = IC.convert(text).getResult();
        logger.debug("IntConversion applied: " + result);
        result = FC.convert(result);
        logger.debug("FloatConversion applied: " + result);
        
        return super.transform(result);
    }
}