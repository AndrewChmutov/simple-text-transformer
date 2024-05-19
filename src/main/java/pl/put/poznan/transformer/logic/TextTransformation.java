package pl.put.poznan.transformer.logic;

/**
 * This is an interface acting as a base decorator
 */
public interface TextTransformation {
    /**
     * An abstract method to be implemented
     * @param text string input by the user
     */
    String transform(String text);
}
