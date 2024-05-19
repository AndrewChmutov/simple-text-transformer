package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class AcronymCompressionDecorator extends TransformationDecorator{
    public AcronymCompressionDecorator(TextTransformation textTransformation) {super(textTransformation);}

    private static final Map<String, String> ABBREVIATION_MAP;

    static {
        ABBREVIATION_MAP = new HashMap<>();
        ABBREVIATION_MAP.put("for example", "e.g.");
        ABBREVIATION_MAP.put("among others", "i.a.");
        ABBREVIATION_MAP.put("and so on", "aso");
    }

    @Override
    public String transform(String text) {
        StringBuilder builder = new StringBuilder();
        List<Character> whitespaces = new ArrayList<>();
        for (char c : text.toCharArray()) {
            if (Character.isWhitespace(c)) {
                whitespaces.add(c);
            }
        }

        String[] words = text.split("(\\s)");
        int i = 0;
        int omitWords = 0;
        for (int j = 0; j < words.length; j++) {
            if (omitWords > 0) {
                --omitWords;
                continue;
            }

            String Word1 = words[j];
            String Word2 = (j + 1 < words.length) ? words[j + 1] : "";
            String Word3 = (j + 2 < words.length) ? words[j + 2] : "";
            char whitespace1 = 'n';
            char whitespace2 = 'n';
            char whitespace3 = 'n';

            if (i < whitespaces.size()) {
                whitespace1 = whitespaces.get(i);
            }
            if (i < whitespaces.size()-1) {
                whitespace2 = whitespaces.get(i+1);
            }
            if (i < whitespaces.size()-2) {
                whitespace3 = whitespaces.get(i+2);
            }

            String Words12 = Word1 + whitespace1 + Word2;
            String Words23 = Word2 + whitespace2 + Word3;
            String Words123 = Words12 + whitespace2 + Word3;
            String lowerWords12 = Words12.toLowerCase();
            String lowerWords23 = Words23.toLowerCase();
            String lowerWords123 = Words123.toLowerCase();

            if (ABBREVIATION_MAP.containsKey(lowerWords12)) {
                String shortForm = ABBREVIATION_MAP.get(lowerWords12);
                builder.append(shortForm);
                if (whitespace2 != 'n') {
                    builder.append(whitespace2);
                }
                omitWords = 1;
                i+=2;
            } else if (ABBREVIATION_MAP.containsKey(lowerWords23)) {
                builder.append(Word1);
                builder.append(whitespace1);
                String shortForm = ABBREVIATION_MAP.get(lowerWords23);
                builder.append(shortForm);
                if (whitespace3 != 'n') {
                    builder.append(whitespace3);
                }
                omitWords = 2;
                i+=3;
            } else if (ABBREVIATION_MAP.containsKey(lowerWords123)) {
                String shortForm = ABBREVIATION_MAP.get(lowerWords123);
                builder.append(shortForm);
                omitWords = 2;
                if (whitespace3 != 'n') {
                    builder.append(whitespace3);
                }
                i+=3;
            } else {
                builder.append(Word1);
                if (whitespace1 != 'n') {
                    builder.append(whitespace1);
                    ++i;
                }
            }
        }

        String result = builder.toString();
        logger.debug("AcronymCompression applied: "  + result);

        return super.transform(result);
    }
}