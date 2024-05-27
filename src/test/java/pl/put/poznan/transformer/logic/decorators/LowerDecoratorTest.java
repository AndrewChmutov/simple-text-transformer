package pl.put.poznan.transformer.logic.decorators;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.TextTransformation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LowerDecoratorTest {
    @Test
    void testTransformSimpleText() {
        TextTransformation textTransformation = mock(TextTransformation.class);
        LowerDecorator lowerDecorator = new LowerDecorator(textTransformation);

        String inputText = "JUNK CODE";
        String expectedOutput = "junk code";

        // Mock the behavior of the superclass transform method
        when(textTransformation.transform(expectedOutput)).thenReturn(expectedOutput);

        String result = lowerDecorator.transform(inputText);
        assertEquals(expectedOutput, result);
        verify(textTransformation).transform(expectedOutput);
    }

    @Test
    void testTransformComplexText() {
        TextTransformation textTransformation = mock(TextTransformation.class);
        LowerDecorator lowerDecorator = new LowerDecorator(textTransformation);

        String inputText = "JunK co.De iS ===alL i K@9oW";
        String expectedOutput = "junk co.de is ===all i k@9ow";

        // Mock the behavior of the superclass transform method
        when(textTransformation.transform(expectedOutput)).thenReturn(expectedOutput);

        String result = lowerDecorator.transform(inputText);
        assertEquals(expectedOutput, result);
        verify(textTransformation).transform(expectedOutput);
    }
}