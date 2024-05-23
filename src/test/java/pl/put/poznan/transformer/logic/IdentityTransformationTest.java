package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentityTransformationTest {
    private IdentityTransformation identityTransformation;

    @BeforeEach
    void setUp() {
        identityTransformation = new IdentityTransformation();
    }

    @Test
    void testTransformStandard() {
        String testString = "Hello World";
        assertEquals(testString, identityTransformation.transform(testString));
    }

    @Test
    void testTransformFormat() {
        String testString = " No     edge_cases/ missed& \n   nOne";
        assertEquals(testString, identityTransformation.transform(testString));
    }

    @Test
    void testTransformUTF8() {
        String testString = "поддержка ƱƮƑ-顾 γειά";
        assertEquals(testString, identityTransformation.transform(testString));
    }
}