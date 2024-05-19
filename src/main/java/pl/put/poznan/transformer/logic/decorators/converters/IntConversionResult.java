package pl.put.poznan.transformer.logic.decorators.converters;

public class IntConversionResult {
    private String result;
    private boolean isInt;

    public IntConversionResult(String result, boolean isInt) {
        this.result = result;
        this.isInt = isInt;
    }

    public String getResult() {
        return result;
    }

    public boolean isInt() {
        return isInt;
    }
}
