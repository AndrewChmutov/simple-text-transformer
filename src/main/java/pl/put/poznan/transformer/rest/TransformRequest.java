package pl.put.poznan.transformer.rest;

public class TransformRequest {
    private String input;
    private String[] transforms;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }


    public String[] getTransforms() {
        return transforms;
    }

    public void setTransforms(String[] transforms) {
        this.transforms = transforms;
    }
}
