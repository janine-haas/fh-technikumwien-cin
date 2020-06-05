package at.technikumwien.personwebapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc // die Web Schicht wird weggemockt
@Tag("integration-test")
public class PersonResourceTest {

    @Autowired
    private MockMvc mockMvc; // das zentrale Objekt, das die Web Schicht mockt
    @Autowired
    private ObjectMapper om;

    @Test
   public void testRetrive() throws Exception{
        var requestBuilder = MockMvcRequestBuilders // hier erstellen wir einen Request
                .get("/resources/persons/1")
                .accept(MediaType.APPLICATION_JSON);

        // variante 1: uses expect methods
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
   }

    @Test
    public void testRetriveAll() throws Exception{
        var requestBuilder = MockMvcRequestBuilders // hier erstellen wir einen Request
                .get("/resources/persons")
                .accept(MediaType.APPLICATION_JSON);

        var response = mockMvc
                .perform(requestBuilder)
                .andReturn().getResponse();

        var persons = om.readValue(
                response.getContentAsString(),
                new TypeReference<List<Person>>() {} // List<Person>.class ist nicht möglich, daher so
        );

        // variante 2: uses assertEquals
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertThat(response.getContentType()).containsIgnoringCase(MediaType.APPLICATION_JSON_VALUE);
        assertEquals(1, persons.size());
    }
}
