package pl.put.poznan.transformer.logic.decorators.converters;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to find and convert integers into their English text representation within the range [0, 1000]
 * that are separated by whitespaces on both sides in a given text.
 */
public class IntConverter {

    private static final String[] ONES = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] TENS = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    /**
     * IntConverter constructor
     */
    public IntConverter(){}

    /**
     * Converts an integer within the range [0, 999] into its English text representation.
     * @param number the integer that was identified in the text and converted from a string to an integer
     * @return the English text representation of the number
     */
    public static String convertHundreds(int number) {
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

    /**
     * Finds all integers within the range [0, 1000] and converts them using `convertHundreds` method (except 1000).
     * @param text the string input provided by the user
     * @return an IntConversionResult object wich contains the text with all eligible integers converted
     * to their English text representation and a boolean indicating whether the whole text is an integer
     * (used in FloatConverter).
     */
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

            else if (word.length() <= 3 & !word.isEmpty()) {

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