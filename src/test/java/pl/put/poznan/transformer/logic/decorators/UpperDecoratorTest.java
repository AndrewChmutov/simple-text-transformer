package pl.put.poznan.transformer.logic.decorators;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.TextTransformation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpperDecoratorTest {
    @Test
    void testTransformStandard() {
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


    @Test
    void testTransformFormat() {
        TextTransformation textTransformation = mock(TextTransformation.class);
        UpperDecorator upperDecorator = new UpperDecorator(textTransformation);

        String inputText = " No     edge_cases/ missed& \n   nOne";
        String expectedOutput = " NO     EDGE_CASES/ MISSED& \n   NONE";

        // Mock the behavior of the superclass transform method
        when(textTransformation.transform(expectedOutput)).thenReturn(expectedOutput);

        String result = upperDecorator.transform(inputText);
        assertEquals(expectedOutput, result);
        verify(textTransformation).transform(expectedOutput);
    }

    @Test
    void testTransformationUTF8() {
        TextTransformation textTransformation = mock(TextTransformation.class);
        UpperDecorator upperDecorator = new UpperDecorator(textTransformation);

        String inputText = "поддержка ƱƮƑ-顾 γειά";
        String expectedOutput = "ПОДДЕРЖКА ƱƮƑ-顾 ΓΕΙΆ";

        // Mock the behavior of the superclass transform method
        when(textTransformation.transform(expectedOutput)).thenReturn(expectedOutput);

        String result = upperDecorator.transform(inputText);
        assertEquals(expectedOutput, result);
        verify(textTransformation).transform(expectedOutput);
    }

}