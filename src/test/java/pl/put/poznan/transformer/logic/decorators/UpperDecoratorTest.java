package pl.put.poznan.transformer.logic.decorators;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.TextTransformation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpperDecoratorTest {
    @Test
    public void testTransform() {
        TextTransformation textTransformation = mock(TextTransformation.class);
        UpperDecorator upperDecorator = new UpperDecorator(textTransformation);

        String inputText = "Hello World";
        String expectedOutput = "HELLO WORLD";

        // Mock the behavior of the superclass transform method
        when(textTransformation.transform(expectedOutput)).thenReturn(expectedOutput);

        String result = upperDecorator.transform(inputText);
        assertEquals(expectedOutput, result);
        verify(textTransformation).transform(expectedOutput);
    }
}