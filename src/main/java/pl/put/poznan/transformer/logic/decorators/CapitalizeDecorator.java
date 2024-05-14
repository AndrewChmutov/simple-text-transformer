package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

public class CapitalizeDecorator extends TransformationDecorator {

    public CapitalizeDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    @Override
    public String transform(String text) {
        String[] words = text.split("\\s");

        StringBuilder result = new StringBuilder();

        for (String word : words) {
            // capitalize the first letter, append the rest of the word, and add a space
            result.append(Character.toTitleCase(word.charAt(0)))
                    .append(word.substring(1))
                    .append(" ");
        }

        return super.transform(result.toString().trim());
    }
}
