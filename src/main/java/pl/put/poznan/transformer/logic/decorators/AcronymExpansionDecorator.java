package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AcronymExpansionDecorator extends TransformationDecorator{
    public AcronymExpansionDecorator(TextTransformation textTransformation) {super(textTransformation);}

    private static final Map<String, String> ABBREVIATION_MAP;

    static {
        ABBREVIATION_MAP = new HashMap<>();
        ABBREVIATION_MAP.put("prof.", "professor");
        ABBREVIATION_MAP.put("dr", "doctor");
        ABBREVIATION_MAP.put("e.g.", "for example");
        ABBREVIATION_MAP.put("aso", "and so on");
    }

    public String preserveCase(String original, String fullForm) {
        if (original.equals(original.toUpperCase())) {
            return fullForm.toUpperCase();
        } else if (original.equals(original.toLowerCase())) {
            return fullForm.toLowerCase();
        } else if (Character.isUpperCase(original.charAt(0))) {
            return Character.toUpperCase(fullForm.charAt(0)) + fullForm.substring(1).toLowerCase();
        } else {
            return fullForm;
        }
    }

    @Override
    public String transform(String text) {
        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(text);
        List<String> whitespaces = new ArrayList<>();
        while (matcher.find()) {
            whitespaces.add(matcher.group());
        }

        String[] words = text.split("(?<=\\s)(?=\\s)|\\s+");
        int i = 0;
        for (String word : words) {
            String lowerWord = word.toLowerCase();
            if (ABBREVIATION_MAP.containsKey(lowerWord)) {
                String fullForm = ABBREVIATION_MAP.get(lowerWord);
                builder.append(preserveCase(word, fullForm));
            } else {
                builder.append(word);
            }
            if (i<whitespaces.size()) {
                builder.append(whitespaces.get(i));
                ++i;
            }
        }
        String result = builder.toString();
        logger.debug("AcronymExpansion applied: "  + result);

        return super.transform(result);
    }
}
