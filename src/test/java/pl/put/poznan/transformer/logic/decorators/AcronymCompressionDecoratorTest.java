package pl.put.poznan.transformer.logic.decorators;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.TextTransformation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AcronymCompressionDecoratorTest {
    @Test
    void testTransformAndSoOn() {
        TextTransformation textTransformation = mock(TextTransformation.class);
        AcronymCompressionDecorator acronymCompressionDecorator = new AcronymCompressionDecorator(textTransformation);

        String inputText = "JUNK junk   and so on";
        String expectedOutput = "JUNK junk   aso";

        // Mock the behavior of the superclass transform method
        when(textTransformation.transform(expectedOutput)).thenReturn(expectedOutput);

        String result = acronymCompressionDecorator.transform(inputText);
        assertEquals(expectedOutput, result);
        verify(textTransformation).transform(expectedOutput);
    }
    @Test
    void testTransformForExample() {
        TextTransformation textTransformation = mock(TextTransformation.class);
        AcronymCompressionDecorator acronymCompressionDecorator = new AcronymCompressionDecorator(textTransformation);

        String inputText = "I have many abilities, for example writing junk code";
        String expectedOutput = "I have many abilities, e.g. writing junk code";

        // Mock the behavior of the superclass transform method
        when(textTransformation.transform(expectedOutput)).thenReturn(expectedOutput);

        String result = acronymCompressionDecorator.transform(inputText);
        assertEquals(expectedOutput, result);
        verify(textTransformation).transform(expectedOutput);
    }

}
