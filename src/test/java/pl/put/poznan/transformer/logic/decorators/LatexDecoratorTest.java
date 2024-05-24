package pl.put.poznan.transformer.logic.decorators;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.TextTransformation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LatexDecoratorTest {
    @Test
    void testTransform() {
        TextTransformation textTransformation = mock(TextTransformation.class);
        LatexDecorator latexDecorator = new LatexDecorator(textTransformation);

        String inputText = "&$hello$&";
        String expectedOutput = "\\&\\$hello\\$\\&";

        // Mock the behavior of the superclass transform method
        when(textTransformation.transform(expectedOutput)).thenReturn(expectedOutput);

        String result = latexDecorator.transform(inputText);
        assertEquals(expectedOutput, result);
        verify(textTransformation).transform(expectedOutput);
    }

    @Test
    void testTransformNoLatex() {
        TextTransformation textTransformation = mock(TextTransformation.class);
        LatexDecorator latexDecorator = new LatexDecorator(textTransformation);

        String inputText = "text without latex";
        String expectedOutput = "text without latex";

        // Mock the behavior of the superclass transform method
        when(textTransformation.transform(expectedOutput)).thenReturn(expectedOutput);

        String result = latexDecorator.transform(inputText);
        assertEquals(expectedOutput, result);
        verify(textTransformation).transform(expectedOutput);
    }
}
