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
    void testConvert1() {
        String inputText = "1000 cars";
        String expectedOutput = "one thousand cars";

        String result = intConverter.convert(inputText).getResult();
        assertEquals(expectedOutput, result);
    }

    @Test
    void testConvert2() {
        String inputText = "I am 21 years old";
        String expectedOutput = "I am twenty one years old";

        String result = intConverter.convert(inputText).getResult();
        assertEquals(expectedOutput, result);
    }
}