package pl.put.poznan.transformer.logic;

public class ConversionResult {
    private String result;
    private boolean isInt;

    public ConversionResult(String result, boolean isInt) {
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
