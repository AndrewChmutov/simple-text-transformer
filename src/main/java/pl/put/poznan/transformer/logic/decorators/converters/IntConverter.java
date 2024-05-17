package pl.put.poznan.transformer.logic;


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

    private ConversionResult convert(String text) {
        StringBuilder builder = new StringBuilder();
        String[] arrOfStr = text.split(" ", 0);
        boolean isInt = true;

        for (String string : arrOfStr) {
            isInt = true;
            if (string.length() <= 3 ) {

                if (string.charAt(0) == '0') {
                    if (string.length() == 1) {
                        builder.append(ONES[0]);
                        builder.append(" ");
                    }
                    else {
                        builder.append(string);
                        builder.append(" ");
                        isInt = false;
                    }
                }
                else if (string.isEmpty()) {
                    builder.append(" ");
                    isInt = false;
                }
                else {
                    for (char c : string.toCharArray()) {
                        if (c < '0' || c > '9') {
                            isInt = false;
                            break;
                        }
                    }
                    if (isInt) {
                        int number = Integer.parseInt(string);
                        String convertedText = convertHundreds(number % 1000);
                        builder.append(convertedText.trim());
                        builder.append(" ");
                    }
                    else {
                        builder.append(string);
                        builder.append(" ");
                    }
                }
            }

            else {
                builder.append(string);
                builder.append(" ");
                isInt = false;
            }
        }
        String result = builder.toString().trim();
        return new ConversionResult(result, isInt);
    }

    public ConversionResult convertToText(String text) {
        ConversionResult result = convert(text);
        return result;
    }
}