package pl.put.poznan.transformer.logic;


public class ConversionResult {
    private String result;
    private boolean isInt;

    public ConversionResult(String result, boolean isInt) {
        this.result = result;
        this.isInt = isInt;
    }

    public String getResult() {
        return result;
    }

    public boolean isInt() {
        return isInt;
    }
}


public class IntConverter {

    private static final String[] ONES = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] TENS = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    public NumberConverter(){}

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

    private String convert(String text) {
        StringBuilder builder = new StringBuilder();
        String[] arrOfStr = text.split(" ", 0);

        for (String string : arrOfStr) {
            boolean isInt = true;
            if (string.length() <= 3 ) {

                if (string.charAt(0) == "0") {
                    if (string.length() == 1) {
                        builder.append(ONES[0]);
                        builder.append(" ");
                    }
                    else {
                        builder.append(string);
                        builder.append(" ");
                    }
                }
                else if (string.length() == 1 & string.charAt(0) == "") {
                    builder.append(" ");
                }
                else {
                    for (char c : string.toCharArray()) {
                        int asciiVal = c;
                        if (asciiVal < 48 || asciiVal > 57) {
                            isNumber == false;
                            break;
                        }
                    }
                    if (isInt) {
                        int number = Integer.parseInt(string);
                        String text = convertHundreds(number % 1000);
                        builder.append(text.trim());
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
            }
        }

        String result = builder.toString().trim();
        return new ConversionResult(result, isInt);
    }
}