package pl.put.poznan.transformer.logic.decorators;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.TextTransformation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AcronymExpansionDecoratorTest {


    @Test
    void testPreserveCase() {
        TextTransformation textTransformation = mock(TextTransformation.class);
        AcronymExpansionDecorator acronymExpansionDecorator = new AcronymExpansionDecorator(textTransformation);

        String originalText = "Prof.";
        String fullFormText = "professor";
        String expectedOutput = "Professor";

        String result = acronymExpansionDecorator.preserveCase(originalText, fullFormText);
        assertEquals(expectedOutput, result);
    }

    @Test
    void testTransform1() {
        TextTransformation textTransformation = mock(TextTransformation.class);
        AcronymExpansionDecorator acronymExpansionDecorator = new AcronymExpansionDecorator(textTransformation);

        String inputText = "Prof. Smith and Dr Johnson attended the conference. " +
                "They discussed various topics, e.g. climate change and renewable energy.";
        String expectedOutput = "Professor Smith and Doctor Johnson attended the conference. " +
                "They discussed various topics, for example climate change and renewable energy.";

        when(textTransformation.transform(expectedOutput)).thenReturn(expectedOutput);

        String result = acronymExpansionDecorator.transform(inputText);
        assertEquals(expectedOutput, result);
        verify(textTransformation).transform(expectedOutput);
    }

    @Test
    void testTransform2() {
        TextTransformation textTransformation = mock(TextTransformation.class);
        AcronymExpansionDecorator acronymExpansionDecorator = new AcronymExpansionDecorator(textTransformation);

        String inputText = "prof. Prof. PROF. pROF. dr Dr aso ASO Aso";
        String expectedOutput = "professor Professor PROFESSOR professor doctor Doctor and so on AND SO ON And so on";

        when(textTransformation.transform(expectedOutput)).thenReturn(expectedOutput);

        String result = acronymExpansionDecorator.transform(inputText);
        assertEquals(expectedOutput, result);
        verify(textTransformation).transform(expectedOutput);
    }

    @Test
    void testTransform3() {
        TextTransformation textTransformation = mock(TextTransformation.class);
        AcronymExpansionDecorator acronymExpansionDecorator = new AcronymExpansionDecorator(textTransformation);

        String inputText = "DR";
        String expectedOutput = "DOCTOR";

        when(textTransformation.transform(expectedOutput)).thenReturn(expectedOutput);

        String result = acronymExpansionDecorator.transform(inputText);
        assertEquals(expectedOutput, result);
        verify(textTransformation).transform(expectedOutput);
    }
}