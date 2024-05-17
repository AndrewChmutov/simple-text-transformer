package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;
import pl.put.poznan.transformer.logic.IntConverter;

public class IntConversionDecorator extends TransformationDecorator {

    public IntConversionDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }
    
    @Override
    public String transform(String text) {
        IntConverter IC = new IntConverter();
        String result = IC.convertToText(text).getResult();
        logger.debug("IntConversion applied: " + result);
        
        return super.transform(result);
    }
}
