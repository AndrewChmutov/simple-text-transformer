package pl.put.poznan.transformer.logic.decorators.converters;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntConverter {

    private static final String[] ONES = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] TENS = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    public IntConverter(){}

    private static String convertHundreds(int number) {
        String text = "";

        if (number >= 100) {
            text += ONES[number / 100] + " hundred ";
            number %= 100;
        }

        if (number >= 20) {
            text += TENS[number / 10] + " ";
            number %= 10;
        }

        if (number > 0) {
            text += ONES[number];
        }

        return text.trim();
    }

    public IntConversionResult convert(String text) {
        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(text);
        List<String> whitespaces = new ArrayList<>();
        while (matcher.find()) {
            whitespaces.add(matcher.group());
        }
        boolean isInt = true;


        String[] words = text.split("(?<=\\s)(?=\\s)|\\s+");
        int i = 0;
        for (String word : words) {
            isInt = true;

            if (word.equals("1000")) {
                builder.append("one thousand");
            }

            if (word.length() <= 3 & !word.isEmpty()) {

                if (word.charAt(0) == '0') {
                    if (word.length() == 1) {
                        builder.append(ONES[0]);
                    }
                    else {
                        builder.append(word);
                        isInt = false;
                    }
                }
                else {
                    for (char c : word.toCharArray()) {
                        if (c < '0' || c > '9') {
                            isInt = false;
                            break;
                        }
                    }
                    if (isInt) {
                        int number = Integer.parseInt(word);
                        String convertNumber = convertHundreds(number);
                        builder.append(convertNumber);
                    }
                    else {
                        builder.append(word);
                    }
                }
            }

            else {
                builder.append(word);
                isInt = false;
            }

            if (i<whitespaces.size()) {
                builder.append(whitespaces.get(i));
                ++i;
            }
        }

        String result = builder.toString();
        return new IntConversionResult(result, isInt);
    }
}