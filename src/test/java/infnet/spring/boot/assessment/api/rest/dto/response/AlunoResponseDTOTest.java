package infnet.spring.boot.assessment.api.rest.dto.response;

import infnet.spring.boot.assessment.domain.entity.Aluno;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AlunoResponseDTOTest {

    @Test
    void testNoArgsConstructor() {
        AlunoResponseDTO alunoDTO = new AlunoResponseDTO();
        assertNotNull(alunoDTO);
    }

    @Test
    void testAllArgsConstructor() {
        List<DisciplinaResponseDTO> disciplinas = Arrays.asList(
                DisciplinaResponseDTO.builder().id(1L).nome("Matemática").codigo("MAT123").build(),
                DisciplinaResponseDTO.builder().id(2L).nome("Física").codigo("FIS456").build()
        );

        AlunoResponseDTO alunoDTO = new AlunoResponseDTO(1L, "João da Silva", "123.456.789-00",
                "joao.silva@example.com", "(99) 99999-9999", "Rua A, 123", disciplinas);

        assertNotNull(alunoDTO);
        assertEquals(1L, alunoDTO.getId());
        assertEquals("João da Silva", alunoDTO.getNome());
        assertEquals("123.456.789-00", alunoDTO.getCpf());
        assertEquals("joao.silva@example.com", alunoDTO.getEmail());
        assertEquals("(99) 99999-9999", alunoDTO.getTelefone());
        assertEquals("Rua A, 123", alunoDTO.getEndereco());
        assertEquals(2, alunoDTO.getDisciplinas().size());
        assertEquals(1L, alunoDTO.getDisciplinas().get(0).getId());
        assertEquals("Matemática", alunoDTO.getDisciplinas().get(0).getNome());
        assertEquals("MAT123", alunoDTO.getDisciplinas().get(0).getCodigo());
        assertEquals(2L, alunoDTO.getDisciplinas().get(1).getId());
        assertEquals("Física", alunoDTO.getDisciplinas().get(1).getNome());
        assertEquals("FIS456", alunoDTO.getDisciplinas().get(1).getCodigo());
    }

    @Test
    void testBuilder() {
        List<DisciplinaResponseDTO> disciplinas = Arrays.asList(
                DisciplinaResponseDTO.builder().id(3L).nome("História").codigo("HIS789").build()
        );

        AlunoResponseDTO alunoDTO = AlunoResponseDTO.builder()
                .id(2L)
                .nome("Maria Oliveira")
                .cpf("987.654.321-00")
                .email("maria.oliveira@example.com")
                .telefone("(88) 88888-8888")
                .endereco("Avenida B, 456")
                .disciplinas(disciplinas)
                .build();

        assertNotNull(alunoDTO);
        assertEquals(2L, alunoDTO.getId());
        assertEquals("Maria Oliveira", alunoDTO.getNome());
        assertEquals("987.654.321-00", alunoDTO.getCpf());
        assertEquals("maria.oliveira@example.com", alunoDTO.getEmail());
        assertEquals("(88) 88888-8888", alunoDTO.getTelefone());
        assertEquals("Avenida B, 456", alunoDTO.getEndereco());
        assertEquals(1, alunoDTO.getDisciplinas().size());
        assertEquals(3L, alunoDTO.getDisciplinas().get(0).getId());
        assertEquals("História", alunoDTO.getDisciplinas().get(0).getNome());
        assertEquals("HIS789", alunoDTO.getDisciplinas().get(0).getCodigo());
    }

    @Test
    void testToEntity() {
        List<DisciplinaResponseDTO> disciplinas = Arrays.asList(
                DisciplinaResponseDTO.builder().id(4L).nome("Geografia").codigo("GEO101").notas(List.of(NotaResponseDTO.builder().build())).build()
        );

        AlunoResponseDTO alunoDTO = AlunoResponseDTO.builder()
                .id(3L)
                .nome("Pedro Santos")
                .cpf("456.789.123-00")
                .email("pedro.santos@example.com")
                .telefone("(77) 77777-7777")
                .endereco("Rua C, 789")
                .disciplinas(disciplinas)
                .build();

        Aluno aluno = alunoDTO.toEntity();

        assertNotNull(aluno);
        assertEquals(3L, aluno.getId());
        assertEquals("Pedro Santos", aluno.getNome());
        assertEquals("456.789.123-00", aluno.getCpf());
        assertEquals("pedro.santos@example.com", aluno.getEmail());
        assertEquals("(77) 77777-7777", aluno.getTelefone());
        assertEquals("Rua C, 789", aluno.getEndereco());
        assertEquals(1, aluno.getDisciplinas().size());
        assertEquals(4L, aluno.getDisciplinas().get(0).getId());
        assertEquals("Geografia", aluno.getDisciplinas().get(0).getNome());
        assertEquals("GEO101", aluno.getDisciplinas().get(0).getCodigo());
    }
}
