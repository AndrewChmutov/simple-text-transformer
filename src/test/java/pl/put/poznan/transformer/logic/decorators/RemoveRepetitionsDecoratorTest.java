package pl.put.poznan.transformer.logic.decorators;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.TextTransformation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RemoveRepetitionsDecoratorTest {
    @Test
    void testTransformThreeRepetitions() {
        TextTransformation textTransformation = mock(TextTransformation.class);
        RemoveRepetitionsDecorator removeRepetitionsDecorator = new RemoveRepetitionsDecorator(textTransformation);

        String inputText = "JUNK JUNK JUNK code";
        String expectedOutput = "JUNK code";

        // Mock the behavior of the superclass transform method
        when(textTransformation.transform(expectedOutput)).thenReturn(expectedOutput);

        String result = removeRepetitionsDecorator.transform(inputText);
        assertEquals(expectedOutput, result);
        verify(textTransformation).transform(expectedOutput);
    }

    @Test
    void testTransformTabNoChange() {
        TextTransformation textTransformation = mock(TextTransformation.class);
        RemoveRepetitionsDecorator removeRepetitionsDecorator = new RemoveRepetitionsDecorator(textTransformation);

        String inputText = "JUNK    JUNK    JUNK";
        String expectedOutput = "JUNK    JUNK    JUNK";

        // Mock the behavior of the superclass transform method
        when(textTransformation.transform(expectedOutput)).thenReturn(expectedOutput);

        String result = removeRepetitionsDecorator.transform(inputText);
        assertEquals(expectedOutput, result);
        verify(textTransformation).transform(expectedOutput);
    }

}