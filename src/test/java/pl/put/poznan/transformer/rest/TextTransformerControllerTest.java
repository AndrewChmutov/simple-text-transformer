package pl.put.poznan.transformer.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.put.poznan.transformer.logic.TextTransformer;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TextTransformerControllerTest {
    private TextTransformer textTransformer;
    private MockMvc mockMvc; // For HTTP requests

    @BeforeEach
    void setUp() {
        textTransformer = mock(TextTransformer.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new TextTransformerController()).build();
    }

    @Test
    void testGetTransformation() throws Exception {
        String inputText = "hello";
        String transformedText = "Olleh";

        // We don't test transform() function in transformationProcedure()
        when(textTransformer.transform(inputText)).thenReturn(transformedText);

        // Performing GET request and verify the response
        mockMvc.perform(get("/get")
                        .param("q", inputText)
                        .param("transforms", "capitalize", "reverse"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"output\":\"Olleh\"}"));
    }

    @Test
    void testPostTransformation() throws Exception {
        String inputText = "hello";
        String transformedText = "Olleh";

        // We don't test transform() function in transformationProcedure()
        when(textTransformer.transform(inputText)).thenReturn(transformedText);

        String inputJson = "{\"input\": \"hello\",\"transforms\":[\"capitalize\",\"reverse\"]}";
        String outputJson = "{\"output\":\"Olleh\"}";

        // Performing GET request and verify the response
        mockMvc.perform(post("/post")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
}
