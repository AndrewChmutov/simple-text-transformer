package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.NumberConverter;

public class FloatConverter {

    private static final String[] ONES = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] TENS = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    public FloatConverter(){}

    private String convert(String text) {
        StringBuilder builder = new StringBuilder();
        String[] arrOfStr = text.split(" ", 0);

        for (String string : arrOfStr) {
            boolean isFloat = true;
            char[] chars = string.toCharArray();

            int dot_nr = 0;
            for (char c : chars) {
                if (c.equals(".")) {
                    dot_nr++;
                    if (dot_nr > 1) {
                        break;
                    }
                }
            }

            dot_index = chars.indexOf(".");
            if (dot_nr == 1 & dot_index != 0 & dot_index != string.length()-1 & dot_index <= 2 & string.length() - dot_index <= 2) {
                char[] integerCh = chars.subList(0, dot_index);
                char[] fractionCh = chars.subList(dot_index + 1, chars.size());
                IntConverter IC = new IntConverter();
                isFloat  = IC.convert(integerPart).isInt();

                for (char c : fractionCh) {
                    int asciiVal = c;
                    if (asciiVal < 48 || asciiVal > 57) {
                        isFloat == false;
                        break;
                    }
                }

                if (isFloat) {
                    int integer = Integer.parseInt(new String(integerCh));
                    String integerPart = IC.convert(integer).getResult();
                    
                    int fraction = 0;
                    String fractionPart = null;
                    if (fractionCh[0] == "0") {
                        if (fractionCh[1] == "0") {
                            fractionPart == ""
                        } 
                        else {
                            fraction = Integer.parseInt(new String(fractionCh[1]));
                            fractionPart = IC.convert(fraction).getResult() + "hunredths";
                        }
                    }
                    else if (fractionCh.size() == 2) {
                        fraction = Integer.parseInt(new String(fractionCh));
                        fractionPart = IC.convert(fraction).getResult() + "hunredths";
                    }
                    else {
                        fraction = Integer.parseInt(new String(fractionCh));
                        fractionPart = IC.convert(fraction).getResult() + "tenths";
                    }

                    String float_nr = integerPart + "and" + fractionPart;
                    builder.append(float_nr);
                }
            }

            else if (string.length() == 1 & string.charAt(0) == "") {
                builder.append(" ");
            }

            else {
                builder.append(string);
                builder.append(" ");
            }

        String result = builder.toString().trim();
        return result;
        }
    }
}