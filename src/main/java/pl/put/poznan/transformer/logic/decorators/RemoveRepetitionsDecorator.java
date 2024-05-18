package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;


public class RemoveRepetitionsDecorator extends TransformationDecorator {
    public RemoveRepetitionsDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    @Override
    public String transform(String text) {
        StringBuilder result = new StringBuilder();
        StringBuilder currentWord = new StringBuilder();
        String previousWord = null;

        for (char c : text.toCharArray()) {
            if (Character.isWhitespace(c)) {
                if (currentWord.length() > 0) {
                    String word = currentWord.toString();
                    if (!word.equals(previousWord)) {
                        result.append(word);
                        previousWord = word;
                    }
                    currentWord.setLength(0);
                }
                result.append(c);
            } else {
                currentWord.append(c);
            }
        }
        
        if (currentWord.length() > 0) {
            String word = currentWord.toString();
            if (!word.equals(previousWord)) {
                result.append(word);
            }
        }

        logger.debug("Removed repetitions applied: " + result.toString());

        return super.transform(result.toString());
    }
}
