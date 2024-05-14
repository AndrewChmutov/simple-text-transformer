package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

public class CapitalizeDecorator extends TransformationDecorator {

    public CapitalizeDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    @Override
    public String transform(String text) {
        StringBuilder builder = new StringBuilder();

        for (String word : text.split("\\s")) {
            if (!word.isEmpty()) {
                builder.append(Character.toTitleCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }

        String result = builder.toString().trim();
        logger.debug("Capitalize applied: "  + result);

        return super.transform(result);
    }
}
