package pl.put.poznan.transformer.logic.decorators.converters;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class FloatConverter {

    public FloatConverter(){}

    public String convert(String text) {
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
            boolean isFloat = true;
            char[] chars = word.toCharArray();

            int dot_nr = 0;
            for (char c : chars) {
                if (c == '.') {
                    dot_nr++;
                    if (dot_nr > 1) {
                        break;
                    }
                }
            }

            int dot_index = word.indexOf(".");
            if (dot_nr == 1 & dot_index != 0 & dot_index != word.length()-1 & dot_index <= 3 & word.length() - dot_index <= 3) {
                String integerPart = word.substring(0, dot_index);
                IntConverter IC = new IntConverter();
                isFloat  = IC.convert(integerPart).isInt();

                char[] fractionCh = Arrays.copyOfRange(chars, dot_index+1, word.length());
                for (char c : fractionCh) {
                    if (c < '0' || c > '9') {
                        isFloat = false;
                        break;
                    }
                }

                if (isFloat) {
                    String integerConverted = IC.convert(integerPart).getResult();
                    int fraction = 0;
                    String floatNr = null;
                    String fractionConverted = null;
                    if (fractionCh.length == 1) {
                        if (fractionCh[0] == '0') {
                            // Do nothing
                        }
                        else {
                            fractionConverted = IC.convert(new String(fractionCh)).getResult() + " tenths";
                        }
                    }
                    else if (fractionCh[0] == '0') {
                        if (fractionCh[1] == '0') {
                            // Do nothing
                        } 
                        else {
                            char[] hundredth = Arrays.copyOfRange(fractionCh, 1, 2);
                            fractionConverted = IC.convert(new String(hundredth)).getResult() + " hundredths";
                        }
                    }
                    else {
                        fractionConverted = IC.convert(new String(fractionCh)).getResult() + " hundredths";
                    }

                    String float_nr = null;
                    if (integerConverted.equals("zero")) {
                        if (fractionConverted == null) {
                            float_nr = integerConverted;
                        }
                        else {
                            float_nr = fractionConverted;
                        }
                    }
                    else {
                        if (fractionConverted == null) {
                            float_nr = integerConverted;
                        }
                        else {
                            float_nr = integerConverted + " and " + fractionConverted;
                        }
                    }
                    builder.append(float_nr);
                }
            }

            else {
                builder.append(word);
            }

            if (i<whitespaces.size()) {
                builder.append(whitespaces.get(i));
                ++i;
            }
        }
    String result = builder.toString();
    return result;
    }
}