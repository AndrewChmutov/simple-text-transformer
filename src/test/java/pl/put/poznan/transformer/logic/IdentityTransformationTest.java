package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentityTransformationTest {
    @Test
    public void testTransform() {
        IdentityTransformation identityTransformation = new IdentityTransformation();

        String[] toTest = {
            "Hello world",
            "IdentityTransformation",
            " No     edge_cases/ missed&",
            "поддержка ƱƮƑ-顾"
        };

        for (String testString : toTest) {
            assertEquals(identityTransformation.transform(testString), testString);
        }
    }
}