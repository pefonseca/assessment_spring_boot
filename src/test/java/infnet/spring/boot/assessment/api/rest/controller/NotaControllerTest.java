package infnet.spring.boot.assessment.api.rest.controller;

import infnet.spring.boot.assessment.api.rest.dto.request.NotaRequestDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.NotaResponseDTO;
import infnet.spring.boot.assessment.domain.service.NotaService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class NotaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NotaService notaService;

    @InjectMocks
    private NotaController notaController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(notaController).build();
    }

    @Test
    void testAdicionarNotaParaAluno() throws Exception {
        NotaRequestDTO requestDTO = new NotaRequestDTO();
        requestDTO.setAlunoId(1L);
        requestDTO.setDisciplinaId(1L);
        requestDTO.setValor(8.5);

        NotaResponseDTO responseDTO = new NotaResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setValor(requestDTO.getValor());

        when(notaService.adicionarNotaParaAluno(any(NotaRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/nota")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"alunoId\": 1, \"disciplinaId\": 1, \"nota\": 8.5 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.valor").value(8.5));
    }
}
