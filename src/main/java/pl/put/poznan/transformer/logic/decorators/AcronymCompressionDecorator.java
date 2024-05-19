package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

public class AcronymCompressionDecorator extends TransformationDecorator {
    public AcronymCompressionDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    @Override
    public String transform(String text) {
        String result = text;

        result = result.replaceAll("\\bF(?i)or\\b \\b(?i)example\\b", "E.g.");
        result = result.replaceAll("\\b(?i)for\\b \\b(?i)example\\b", "e.g.");
        result = result.replaceAll("\\bA(?i)mong\\b \\b(?i)others\\b", "I.a.");
        result = result.replaceAll("\\b(?i)among\\b \\b(?i)others\\b", "i.a.");
        result = result.replaceAll("\\bA(?i)nd\\b \\b(?i)so\\b \\b(?i)on\\b", "Aso");
        result = result.replaceAll("\\b(?i)and\\b \\b(?i)so\\b \\b(?i)on\\b", "aso");

        logger.debug("AcronymCompression applied: " + result);

        return super.transform(result);
    }
}
