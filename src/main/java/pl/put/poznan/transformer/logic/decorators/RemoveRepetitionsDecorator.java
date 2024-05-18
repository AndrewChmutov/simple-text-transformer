package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.TextTransformation;

public class RemoveRepetitionsDecorator extends TransformationDecorator {
    public RemoveRepetitionsDecorator(TextTransformation textTransformation) {
        super(textTransformation);
    }

    @Override
    public String transform(String text) {
        String[] words = text.split("\\s+");
        String previousWord=null;
        String result="";
        for (String word:words){
            if(word.equals(previousWord)){
                continue;
            }else{
                result+=word+" ";
                previousWord=word;
            }
        }


        logger.debug("Removed repetitions applied: " + result);

        return super.transform(result);
    }

}
