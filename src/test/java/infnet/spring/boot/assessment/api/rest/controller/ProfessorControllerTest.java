package infnet.spring.boot.assessment.api.rest.controller;

import infnet.spring.boot.assessment.domain.entity.Professor;
import infnet.spring.boot.assessment.domain.service.ProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProfessorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProfessorService professorService;

    @InjectMocks
    private ProfessorController professorController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(professorController).build();
    }

    @Test
    void testCreate() throws Exception {
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setEmail("john.doe@example.com");

        when(professorService.create(any(Professor.class))).thenReturn(professor);

        mockMvc.perform(post("/api/professor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"nome\": \"John Doe\", \"email\": \"john.doe@example.com\" }"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/professor/1"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }
}
