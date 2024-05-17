package pl.put.poznan.transformer.logic.decorators.converters;

import java.util.Arrays;

public class NumberConverter {

    private static final String[] ONES = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] TENS = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    public NumberConverter(){}

    private String convert(String text) {
        StringBuilder builder = new StringBuilder();
        String[] arrOfStr = text.split(" ", 0);

        for (String string : arrOfStr) {
            boolean isFloat = true;
            char[] chars = string.toCharArray();

            int dot_nr = 0;
            for (char c : chars) {
                if (c == '.') {
                    dot_nr++;
                    if (dot_nr > 1) {
                        break;
                    }
                }
            }

            int dot_index = string.indexOf(".");
            if (dot_nr == 1 & dot_index != 0 & dot_index != string.length()-1 & dot_index <= 3 & string.length() - dot_index <= 3) {
                String integerPart = string.substring(0, dot_index);
                IntConverter IC = new IntConverter();
                isFloat  = IC.convertToText(integerPart).isInt();

                char[] fractionCh = Arrays.copyOfRange(chars, dot_index+1, string.length());
                for (char c : fractionCh) {
                    if (c < '0' || c > '9') {
                        isFloat = false;
                        break;
                    }
                }

                if (isFloat) {
                    String integerConverted = IC.convertToText(integerPart).getResult();
                    int fraction = 0;
                    String fractionConverted = null;
                    if (fractionCh[0] == '0') {
                        if (fractionCh[1] == '0') {
                            fractionConverted = "";
                        } 
                        else {
                            char[] hundredth = Arrays.copyOfRange(fractionCh, 1, 2);
                            fractionConverted = IC.convertToText(new String(hundredth)).getResult() + "hundredths ";
                        }
                    }
                    else if (fractionCh.length == 2) {
                        fractionConverted = IC.convertToText(new String(fractionCh)).getResult() + "hundredths ";
                    }
                    else {
                        fractionConverted = IC.convertToText(new String(fractionCh)).getResult() + "tenths ";
                    }

                    if (integerConverted.equals("zero ")) {
                        String float_nr = fractionConverted;
                        builder.append(float_nr);
                    }
                    else {
                        String float_nr = integerConverted + "and " + fractionConverted;
                        builder.append(float_nr);
                    }
                }
            }

            else if (string.isEmpty()) {
                builder.append(" ");
            }

            else {
                builder.append(string);
                builder.append(" ");
            }
        }
    String result = builder.toString().trim();
    return result;
    }

    public String convertToText(String text) {
        String result = convert(text);
        return result;
    }
}