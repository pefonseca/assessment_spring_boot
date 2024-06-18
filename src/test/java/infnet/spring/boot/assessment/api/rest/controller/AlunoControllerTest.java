package infnet.spring.boot.assessment.api.rest.controller;

import infnet.spring.boot.assessment.api.rest.dto.request.AlunoRequestDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.AlunoResponseDTO;
import infnet.spring.boot.assessment.domain.service.AlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    private AlunoResponseDTO alunoResponseDTO;

    @BeforeEach
    void setUp() {
        alunoResponseDTO = new AlunoResponseDTO();
        alunoResponseDTO.setId(1L);
        alunoResponseDTO.setNome("Test Aluno");
        alunoResponseDTO.setCpf("123.456.789-00");
        alunoResponseDTO.setEmail("test@example.com");
        alunoResponseDTO.setTelefone("123456789");
        alunoResponseDTO.setEndereco("Test Address");
    }

    @Test
    @WithMockUser(authorities = "PROFESSOR")
    void testFindAll() throws Exception {
        Mockito.when(alunoService.findAll()).thenReturn(List.of(alunoResponseDTO));

        mockMvc.perform(get("/api/aluno"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", is(alunoResponseDTO.getNome())));
    }

    @Test
    @WithMockUser(authorities = "PROFESSOR")
    void testFindById() throws Exception {
        Mockito.when(alunoService.findById(anyLong())).thenReturn(alunoResponseDTO);

        mockMvc.perform(get("/api/aluno/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(alunoResponseDTO.getNome())));
    }

    @Test
    @WithMockUser(authorities = "PROFESSOR")
    void testCreate() throws Exception {
        Mockito.when(alunoService.create(any(AlunoRequestDTO.class))).thenReturn(alunoResponseDTO);

        AlunoRequestDTO alunoRequestDTO = new AlunoRequestDTO();
        alunoRequestDTO.setNome("Test Aluno");
        alunoRequestDTO.setCpf("123.456.789-00");
        alunoRequestDTO.setEmail("test@example.com");
        alunoRequestDTO.setTelefone("123456789");
        alunoRequestDTO.setEndereco("Test Address");

        mockMvc.perform(post("/api/aluno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Test Aluno\", \"cpf\": \"123.456.789-00\", \"email\": \"test@example.com\", \"telefone\": \"123456789\", \"endereco\": \"Test Address\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is(alunoResponseDTO.getNome())));
    }

    @Test
    @WithMockUser(authorities = "PROFESSOR")
    void testUpdate() throws Exception {
        Mockito.when(alunoService.update(anyLong(), any(AlunoRequestDTO.class))).thenReturn(alunoResponseDTO);

        mockMvc.perform(put("/api/aluno/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Updated Aluno\", \"cpf\": \"123.456.789-00\", \"email\": \"updated@example.com\", \"telefone\": \"123456789\", \"endereco\": \"Updated Address\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(alunoResponseDTO.getNome())));
    }

    @Test
    @WithMockUser(authorities = "PROFESSOR")
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/aluno/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(authorities = "PROFESSOR")
    void testAlocarAlunoEmDisciplina() throws Exception {
        Mockito.when(alunoService.alocarAlunoEmDisciplina(anyLong(), anyLong())).thenReturn(alunoResponseDTO);

        mockMvc.perform(put("/api/aluno/{alunoId}/alocar-disciplina/{disciplinaId}", 1L, 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(alunoResponseDTO.getNome())));
    }

    @Test
    @WithMockUser(authorities = "PROFESSOR")
    void testFindAlunosAprovados() throws Exception {
        Mockito.when(alunoService.findAlunosAprovados(anyLong())).thenReturn(List.of(alunoResponseDTO));

        mockMvc.perform(get("/api/aluno/alunos-aprovados")
                        .param("disciplinaId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", is(alunoResponseDTO.getNome())));
    }

    @Test
    @WithMockUser(authorities = "PROFESSOR")
    void testFindAlunosReprovados() throws Exception {
        Mockito.when(alunoService.findAlunosReprovados(anyLong())).thenReturn(List.of(alunoResponseDTO));

        mockMvc.perform(get("/api/aluno/alunos-reprovados")
                        .param("disciplinaId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", is(alunoResponseDTO.getNome())));
    }
}
