package infnet.spring.boot.assessment.api.rest.controller;

import infnet.spring.boot.assessment.api.rest.dto.request.DisciplinaRequestDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.DisciplinaResponseDTO;
import infnet.spring.boot.assessment.domain.service.DisciplinaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class DisciplinaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DisciplinaService disciplinaService;

    @InjectMocks
    private DisciplinaController disciplinaController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(disciplinaController).build();
    }

    @Test
    void testFindAll() throws Exception {
        when(disciplinaService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/disciplina")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        Long id = 1L;
        DisciplinaResponseDTO disciplina = new DisciplinaResponseDTO();
        disciplina.setId(id);
        when(disciplinaService.findById(id)).thenReturn(disciplina);

        mockMvc.perform(get("/api/disciplina/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void testCreate() throws Exception {
        DisciplinaRequestDTO requestDTO = new DisciplinaRequestDTO();
        requestDTO.setNome("Matemática");
        requestDTO.setCodigo("MAT123");

        DisciplinaResponseDTO createdDisciplina = new DisciplinaResponseDTO();
        createdDisciplina.setId(1L);
        createdDisciplina.setNome(requestDTO.getNome());
        createdDisciplina.setCodigo(requestDTO.getCodigo());

        when(disciplinaService.create(any(DisciplinaRequestDTO.class))).thenReturn(createdDisciplina);

        mockMvc.perform(post("/api/disciplina")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"Matemática\", \"codigo\": \"MAT123\" }"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/aluno/1"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Matemática"))
                .andExpect(jsonPath("$.codigo").value("MAT123"));
    }

    @Test
    void testUpdate() throws Exception {
        Long id = 1L;
        DisciplinaRequestDTO requestDTO = new DisciplinaRequestDTO();
        requestDTO.setNome("Física");
        requestDTO.setCodigo("FIS456");

        DisciplinaResponseDTO updatedDisciplina = new DisciplinaResponseDTO();
        updatedDisciplina.setId(id);
        updatedDisciplina.setNome(requestDTO.getNome());
        updatedDisciplina.setCodigo(requestDTO.getCodigo());

        when(disciplinaService.update(eq(id), any(DisciplinaRequestDTO.class))).thenReturn(updatedDisciplina);

        mockMvc.perform(put("/api/disciplina/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"Física\", \"codigo\": \"FIS456\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Física"))
                .andExpect(jsonPath("$.codigo").value("FIS456"));
    }

    @Test
    void testDelete() throws Exception {
        Long id = 1L;
        doNothing().when(disciplinaService).deleteById(id);

        mockMvc.perform(delete("/api/disciplina/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
