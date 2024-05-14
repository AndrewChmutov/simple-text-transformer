package pl.put.poznan.transformer.logic;

public class IdentityTransformation implements TextTransformation {
    @Override
    public String transform(String text) {
        return text;
    }
}
