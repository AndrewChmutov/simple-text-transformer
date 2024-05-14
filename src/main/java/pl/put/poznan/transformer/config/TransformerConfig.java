package pl.put.poznan.transformer.config;

import org.springframework.context.annotation.Configuration;
import pl.put.poznan.transformer.logic.TextTransformation;

@Configuration
public class TransformerConfig {

    public static TextTransformation decorateTransformation(TextTransformation textTransformation, String transformationName) {
        return switch (transformationName) {
            default -> textTransformation;
        };
    }
}
