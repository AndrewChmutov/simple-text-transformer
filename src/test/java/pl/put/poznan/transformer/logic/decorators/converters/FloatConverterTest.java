package pl.put.poznan.transformer.logic.decorators.converters;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class FloatConverterTest {

    @Test
    void testConvert() {
        FloatConverter floatConverter = new FloatConverter();
        String inputText = "I should have bought 0.7 liters of water";
        String expectedOutput = "I should have bought seven tenths liters of water";

        String result = floatConverter.convert(inputText);
        assertEquals(expectedOutput, result);
    }
}