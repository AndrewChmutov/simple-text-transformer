package pl.put.poznan.transformer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import pl.put.poznan.transformer.logic.TextTransformation;
import pl.put.poznan.transformer.logic.decorators.*;

@Configuration
public class TransformerConfig {
    private static final Logger logger = LoggerFactory.getLogger(TransformerConfig.class);

    public static TextTransformation decorateTransformation(TextTransformation textTransformation, String transformationName) {
        return switch (transformationName) {
            case "upper" -> new UpperDecorator(textTransformation);
            case "lower" -> new LowerDecorator(textTransformation);
            case "capitalize" -> new CapitalizeDecorator(textTransformation);
            case "reverse" -> new ReverseDecorator(textTransformation);
            case "number_conversion" -> new NumberConversionDecorator(textTransformation);
            case "acronym_expand" -> new AcronymExpansionDecorator(textTransformation);
            case "acronym_compress" -> new AcronymCompressionDecorator(textTransformation);
            default -> {
                logger.warn("Invalid transformation: " + transformationName);
                yield textTransformation;
            }
        };
    }
}
