package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;
import java.util.Map;
import java.util.HashMap;
//import java.util.ArrayList; import for format handling
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;


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
//        I Have comment lines that were used for format handling which unfortunately don't work
//        Pattern pattern = Pattern.compile("\\s");
//        Matcher matcher = pattern.matcher(text);
//        List<String> whitespaces = new ArrayList<>();
//        while (matcher.find()) {
//            whitespaces.add(matcher.group());
//        }

        String[] words = text.split("(?<=\\s)(?=\\s)|\\s+");

//        int i = 0;
        int omitWords = 0;

        for (int j = 0; j < words.length; j++) {
            if (omitWords > 0) {
                --omitWords;
                continue;
            }

            String Word1 = words[j];
            String Word2 = (j + 1 < words.length) ? words[j + 1] : "";
            String Word3 = (j + 2 < words.length) ? words[j + 2] : "";
//            String whitespace1 = "";
//            String whitespace2 = "";
//            if (i < whitespaces.size()) {
//                whitespace1 = whitespaces.get(i);
//            }
//            if (i < whitespaces.size()-1) {
//                whitespace2 = whitespaces.get(i+1);
//            }

            String Words12 = Word1 + " " + Word2;
            String Words23 = Word2 + " " + Word3;
            String Words123 = Words12 + " " + Word3;
            String lowerWords12 = Words12.toLowerCase();
            String lowerWords23 = Words23.toLowerCase();
            String lowerWords123 = Words123.toLowerCase();


            if (ABBREVIATION_MAP.containsKey(lowerWords12)) {
                String shortForm = ABBREVIATION_MAP.get(lowerWords12);
                builder.append(shortForm);
//                builder.append(whitespace2);
                builder.append(" "); // previous line instead of this one
                omitWords = 1;
//                ++i;
            } else if (ABBREVIATION_MAP.containsKey(lowerWords23)) {
                builder.append(Word1);
//                builder.append(whitespace1);
                builder.append(" "); // previous line instead of this one
                String shortForm = ABBREVIATION_MAP.get(lowerWords23);
                builder.append(shortForm);
                builder.append(" "); // redundant in formating version
                omitWords = 2;
//                ++i;
            } else if (ABBREVIATION_MAP.containsKey(lowerWords123)) {
                String shortForm = ABBREVIATION_MAP.get(lowerWords123);
                builder.append(shortForm);
                builder.append(" "); // redundant in formating version
                omitWords = 2;
//                i+=2;
            } else {
                builder.append(Word1);
//                builder.append(whitespace1);
                builder.append(" "); //previous line instead of this one
//                ++i;
            }
        }

        String result = builder.toString();
        logger.debug("AcronymCompression applied: "  + result);

        return super.transform(result);
    }
}