package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

public class IntConversionDecorator extends TransformationDecorator {

    private static final String[] ONES = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] TENS = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    private static final String[] SCALES = {"", "thousand", "million", "billion", "trillion", "quadrillion", "quintillion"};


    public IntConversionDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

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
    
    @Override
    public String transform(String text) {
        StringBuilder builder = new StringBuilder();
        String[] arrOfStr = str.split(" ", 0);

        for (String string : arrOfStr) {
            boolean isNumber = true;
            if (str.charAt(0) == "0") {
                if (str.length() == 1) {
                    builder.append(ONES[0]);
                    builder.append(' ');
                }
                else {
                    builder.append(string);
                    builder.append(' ');
                }
            }
            else if (str.length() == 1 & str.charAt(0) == "") {
                builder.append(' ');
            }

            else {
                for (char c : string.toCharArray()) {
                    int asciiVal = c;
                    if (asciiVal < 48 || asciiVal > 57) {
                        isNumber == false;
                        break;
                    }
                }

                if (isNumber) {
                    String text = "";

                    for (int i = 0; number > 0; i++, number /= 1000) {
                        if (number % 1000 != 0) {
                            text = convertHundreds(number % 1000) + " " + SCALES[i] + " " + text;
                        }
                    }

                    builder.append(text.trim());
                    builder.append(' ');
                }
                else {
                    builder.append(string);
                    builder.append(' ');
                }
            }
        }

        String result = builder.toString().trim();
        logger.debug("IntConversion applied: "  + result);
        
        return super.transform(result);
    }
}

