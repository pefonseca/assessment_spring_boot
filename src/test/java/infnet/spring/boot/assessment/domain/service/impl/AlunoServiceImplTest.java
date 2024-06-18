package infnet.spring.boot.assessment.domain.service.impl;

import infnet.spring.boot.assessment.api.rest.dto.request.AlunoRequestDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.AlunoResponseDTO;
import infnet.spring.boot.assessment.domain.entity.Aluno;
import infnet.spring.boot.assessment.domain.entity.Disciplina;
import infnet.spring.boot.assessment.domain.repository.AlunoRepository;
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
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlunoServiceImplTest {

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private DisciplinaRepository disciplinaRepository;

    @InjectMocks
    private AlunoServiceImpl alunoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        when(alunoRepository.findAll()).thenReturn(new ArrayList<>());
        List<AlunoResponseDTO> result = alunoService.findAll();
        assertNotNull(result);
    }

    @Test
    public void testFindById_AlunoNotFound() {
        when(alunoRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> alunoService.findById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Aluno not Found.");
    }

    @Test
    public void testCreate() {
        AlunoRequestDTO requestDTO = new AlunoRequestDTO();
        requestDTO.setNome("João");
        Aluno aluno = criarAluno(1L, "João");
        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);
        AlunoResponseDTO result = alunoService.create(requestDTO);
        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo("João");
    }

    @Test
    public void testUpdate_AlunoNotFound() {
        AlunoRequestDTO requestDTO = new AlunoRequestDTO();
        requestDTO.setNome("José");
        when(alunoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> alunoService.update(1L, requestDTO))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Aluno not Found.");
    }

    @Test
    public void testDeleteById() {
        alunoService.deleteById(1L);
        verify(alunoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testAlocarAlunoEmDisciplina_AlunoNotFound() {
        when(alunoRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> alunoService.alocarAlunoEmDisciplina(1L, 1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Aluno não encontrado.");
    }

    @Test
    public void testAlocarAlunoEmDisciplina_DisciplinaNotFound() {
        Aluno aluno = criarAluno(1L, "João");
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(disciplinaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> alunoService.alocarAlunoEmDisciplina(1L, 1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Disciplina não encontrada.");
    }

    @Test
    public void testFindAlunosAprovados() {
        when(alunoRepository.findAlunosPorDisciplinaComNotaMaiorOuIgualSete(anyLong())).thenReturn(new ArrayList<>());
        List<AlunoResponseDTO> result = alunoService.findAlunosAprovados(1L);
        assertNotNull(result);
    }

    @Test
    public void testFindAlunosReprovados() {
        when(alunoRepository.findAlunosPorDisciplinaComNotaMenorQueSete(anyLong())).thenReturn(new ArrayList<>());
        List<AlunoResponseDTO> result = alunoService.findAlunosAprovados(1L);
        assertNotNull(result);
    }

    private Aluno criarAluno(Long id, String nome) {
        return Aluno.builder()
                .id(id)
                .nome(nome)
                .disciplinas(List.of(Disciplina.builder().id(2L).codigo("Port").build()))
                .build();
    }

    private Disciplina criarDisciplina(Long id, String nome) {
        return Disciplina.builder()
                .id(id)
                .nome(nome)
                .build();
    }
}
