package pl.put.poznan.transformer.logic.decorators.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class IntConverterTest {

    private IntConverter intConverter;

    @BeforeEach
    void setUp() {
        intConverter = new IntConverter();
    }

    @Test
    void testConvertHundreds() {
        int inputNumber = 679;
        String expectedOutput = "six hundred seventy nine";

        String result = intConverter.convertHundreds(inputNumber);
        assertEquals(expectedOutput, result);
    }

    @Test
    void testConvert() {
        String inputText = "1000 cars";
        String expectedOutput = "one thousand cars";

        String result = intConverter.convert(inputText).getResult();
        assertEquals(expectedOutput, result);
    }
}