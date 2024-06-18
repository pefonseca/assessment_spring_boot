package infnet.spring.boot.assessment.domain.service.impl;

import infnet.spring.boot.assessment.api.rest.dto.request.DisciplinaRequestDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.DisciplinaResponseDTO;
import infnet.spring.boot.assessment.domain.entity.Disciplina;
import infnet.spring.boot.assessment.domain.repository.DisciplinaRepository;
import infnet.spring.boot.assessment.infra.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DisciplinaServiceImplTest {

    @Mock
    private DisciplinaRepository disciplinaRepository;

    @InjectMocks
    private DisciplinaServiceImpl disciplinaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Disciplina> disciplinas = new ArrayList<>();
        disciplinas.add(criarDisciplina(1L, "Matemática"));
        disciplinas.add(criarDisciplina(2L, "Português"));
        when(disciplinaRepository.findAll()).thenReturn(disciplinas);

        List<DisciplinaResponseDTO> result = disciplinaService.findAll();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNome()).isEqualTo("Matemática");
        assertThat(result.get(1).getNome()).isEqualTo("Português");
    }

    @Test
    public void testFindById_Success() {
        Disciplina disciplina = criarDisciplina(1L, "Matemática");
        when(disciplinaRepository.findById(1L)).thenReturn(Optional.of(disciplina));
        DisciplinaResponseDTO result = disciplinaService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo("Matemática");
    }

    @Test
    public void testFindById_DisciplinaNotFound() {
        when(disciplinaRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> disciplinaService.findById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Disciplina not found");
    }

    @Test
    public void testCreate() {
        DisciplinaRequestDTO requestDTO = new DisciplinaRequestDTO();
        requestDTO.setNome("Física");
        Disciplina disciplina = criarDisciplina(1L, "Física");
        when(disciplinaRepository.save(any(Disciplina.class))).thenReturn(disciplina);
        DisciplinaResponseDTO result = disciplinaService.create(requestDTO);
        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo("Física");
    }

    @Test
    public void testUpdate() {
        DisciplinaResponseDTO disciplinaExistente = new DisciplinaResponseDTO();
        disciplinaExistente.setId(1L);
        disciplinaExistente.setNome("Química");
        DisciplinaRequestDTO requestDTO = new DisciplinaRequestDTO();
        requestDTO.setNome("Biologia");
        when(disciplinaRepository.findById(1L)).thenReturn(Optional.of(new Disciplina()));
        when(disciplinaRepository.save(any(Disciplina.class))).thenAnswer(invocation -> invocation.getArgument(0));
        DisciplinaResponseDTO result = disciplinaService.update(1L, requestDTO);
        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo("Biologia");
    }

    @Test
    public void testUpdate_DisciplinaNotFound() {
        DisciplinaRequestDTO requestDTO = new DisciplinaRequestDTO();
        requestDTO.setNome("Biologia");
        when(disciplinaRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> disciplinaService.update(1L, requestDTO))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Disciplina not found");
    }

    @Test
    public void testDeleteById() {
        DisciplinaResponseDTO disciplinaResponseDTO = new DisciplinaResponseDTO();
        disciplinaResponseDTO.setId(1L);
        disciplinaResponseDTO.setNome("História");
        when(disciplinaRepository.findById(1L)).thenReturn(Optional.of(new Disciplina()));
        disciplinaService.deleteById(1L);
        verify(disciplinaRepository, times(1)).delete(any(Disciplina.class));
    }

    private Disciplina criarDisciplina(Long id, String nome) {
        return Disciplina.builder()
                .id(id)
                .nome(nome)
                .build();
    }
}
