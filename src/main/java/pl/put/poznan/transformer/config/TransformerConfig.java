package pl.put.poznan.transformer.config;

import org.springframework.context.annotation.Configuration;
import pl.put.poznan.transformer.logic.TextTransformation;
import pl.put.poznan.transformer.logic.decorators.*;

@Configuration
public class TransformerConfig {

    public static TextTransformation decorateTransformation(TextTransformation textTransformation, String transformationName) {
        return switch (transformationName) {
            case "upper" -> new UpperDecorator(textTransformation);
            case "lower" -> new LowerDecorator(textTransformation);
            case "capitalize" -> new CapitalizeDecorator(textTransformation);
            default -> textTransformation;
        };
    }
}
