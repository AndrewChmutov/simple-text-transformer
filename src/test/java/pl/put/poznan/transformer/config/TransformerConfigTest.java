package pl.put.poznan.transformer.config;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.IdentityTransformation;
import pl.put.poznan.transformer.logic.TextTransformation;
import pl.put.poznan.transformer.logic.decorators.*;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class TransformerConfigTest {
    @Test
    void testDecorateTransformationValid() throws NoSuchFieldException, IllegalAccessException {
        String[] transformationNames = {
            "upper",
            "lower",
            "capitalize",
            "reverse",
            "number_conversion",
            "acronym_expand",
            "acronym_compress",
            "trim_repetitions",
            "latex"
        };

        Class<?>[] classes = {
            UpperDecorator.class,
            LowerDecorator.class,
            CapitalizeDecorator.class,
            ReverseDecorator.class,
            NumberConversionDecorator.class,
            AcronymExpansionDecorator.class,
            AcronymCompressionDecorator.class,
            RemoveRepetitionsDecorator.class,
            LatexDecorator.class
        };

        for (int i = 0; i < transformationNames.length; i++) {
            TextTransformation textTransformation = new IdentityTransformation();
            TextTransformation newTextTransformation = TransformerConfig
                    .decorateTransformation(textTransformation, transformationNames[i]);

            // check if decorator was selected correctly
            assertInstanceOf(classes[i], newTextTransformation);

            // check if the previous textTransformation is inside the decorator
            Field textTransformationField = TransformationDecorator.class.getDeclaredField("textTransformation");
            textTransformationField.setAccessible(true);
            assertEquals(textTransformation, textTransformationField.get(newTextTransformation));
        }
    }

    @Test
    void testDecorateTransformationInvalid() {
        String[] transformationNames = {
            "to_upper",
            "to_lower",
            "reverse_sequence"
        };


        for (String transformationName : transformationNames) {
            TextTransformation textTransformation = new IdentityTransformation();
            TextTransformation newTextTransformation = TransformerConfig
                    .decorateTransformation(textTransformation, transformationName);

            assertEquals(textTransformation, newTextTransformation);
        }
    }
}