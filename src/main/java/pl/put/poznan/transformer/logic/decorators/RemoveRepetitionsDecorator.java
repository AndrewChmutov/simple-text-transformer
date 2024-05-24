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
        boolean omitted = false;
        char previousChar='.';
        for (char c : text.toCharArray()) {
            if(previousChar==' '&&c==' '){
                omitted=false;
                previousWord=null;
            }
            if (c==' ') {
                if (currentWord.length() > 0) {
                    String word = currentWord.toString();
                    if (!word.equals(previousWord)) {
                        result.append(word);
                        previousWord = word;
                        omitted = false;
                    } else {
                        omitted = true;
                    }
                    currentWord.setLength(0);
                }
                if (!omitted) {
                    result.append(c);
                }
            }else if(Character.isWhitespace(c)){
                if (!omitted) {
                    result.append(c);
                }
                previousWord = null;
                omitted = false;
                currentWord.setLength(0);
            } else {
                currentWord.append(c);
            }
            previousChar=c;
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
