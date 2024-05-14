package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

public class CapitalizeDecorator extends TransformationDecorator {

    public CapitalizeDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    @Override
    public String transform(String text) {
        StringBuilder builder = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : text.toCharArray()) {
            if (Character.isWhitespace(c)) {
                builder.append(c);
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    builder.append(Character.toTitleCase(c));
                    capitalizeNext = false;
                } else {
                    builder.append(c);
                }
            }
        }

        String result = builder.toString().trim();
        logger.debug("Capitalize applied: "  + result);

        return super.transform(result);
    }
}
