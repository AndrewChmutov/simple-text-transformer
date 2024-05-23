package pl.put.poznan.transformer.rest;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.put.poznan.transformer.logic.TextTransformer;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TextTransformerControllerTest {

    @Test
    void testGetTransformation() throws Exception {
        TextTransformer textTransformer = mock(TextTransformer.class);
        TextTransformerController textTransformerController = new TextTransformerController();

        String inputText = "hello";
        String transformedText = "Olleh";

        // We don't test transform() function in transformationProcedure()
        when(textTransformer.transform(inputText)).thenReturn(transformedText);

        // MockMvc is required for working with HTTP
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(textTransformerController).build();

        // Performing GET request and verify the response
        mockMvc.perform(get("/get")
                        .param("q", inputText)
                        .param("transforms", "capitalize", "reverse"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"output\":\"Olleh\"}"));
    }
}
