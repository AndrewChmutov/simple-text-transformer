package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

public class AcronymCompressionDecorator extends TransformationDecorator {
    public AcronymCompressionDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    @Override
    public String transform(String text) {
        String result = text;

        result = result.replaceAll("\\bF(?i)or\\s+(?i)example\\b", "E.g.");
        result = result.replaceAll("\\b(?i)for\\s+example\\b", "e.g.");
        result = result.replaceAll("\\bA(?i)mong\\s+(?i)others\\b", "I.a.");
        result = result.replaceAll("\\b(?i)among\\s+others\\b", "i.a.");
        result = result.replaceAll("\\bA(?i)nd\\s+(?i)so\\s+(?i)on\\b", "Aso");
        result = result.replaceAll("\\b(?i)and\\s+so\\s+on\\b", "aso");

        logger.debug("AcronymCompression applied: " + result);

        return super.transform(result);
    }
}
