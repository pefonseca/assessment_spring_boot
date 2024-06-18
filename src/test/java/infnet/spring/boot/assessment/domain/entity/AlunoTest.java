package infnet.spring.boot.assessment.domain.entity;

import infnet.spring.boot.assessment.api.rest.dto.response.AlunoResponseDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.DisciplinaResponseDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.NotaResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AlunoTest {

    @Test
    public void testToDto() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Fulano");
        aluno.setCpf("123.456.789-00");
        aluno.setEmail("fulano@example.com");
        aluno.setTelefone("(00) 1234-5678");
        aluno.setEndereco("Rua Principal");

        Disciplina disciplina1 = new Disciplina();
        disciplina1.setId(1L);
        disciplina1.setNome("Matemática");
        disciplina1.setCodigo("MAT101");

        Disciplina disciplina2 = new Disciplina();
        disciplina2.setId(2L);
        disciplina2.setNome("História");
        disciplina2.setCodigo("HIS201");

        aluno.setDisciplinas(Arrays.asList(disciplina1, disciplina2));

        Nota nota1 = new Nota();
        nota1.setId(1L);
        nota1.setDisciplina(disciplina1);
        nota1.setValor(8.5);

        Nota nota2 = new Nota();
        nota2.setId(2L);
        nota2.setDisciplina(disciplina2);
        nota2.setValor(7.0);

        aluno.setNotas(Arrays.asList(nota1, nota2));

        NotaResponseDTO notaDto1 = new NotaResponseDTO();
        notaDto1.setId(nota1.getId());
        notaDto1.setValor(nota1.getValor());

        NotaResponseDTO notaDto2 = new NotaResponseDTO();
        notaDto2.setId(nota2.getId());
        notaDto2.setValor(nota2.getValor());

        DisciplinaResponseDTO disciplinaDto1 = new DisciplinaResponseDTO();
        disciplinaDto1.setId(disciplina1.getId());
        disciplinaDto1.setNome(disciplina1.getNome());
        disciplinaDto1.setCodigo(disciplina1.getCodigo());
        disciplinaDto1.setNotas(Arrays.asList(notaDto1));

        DisciplinaResponseDTO disciplinaDto2 = new DisciplinaResponseDTO();
        disciplinaDto2.setId(disciplina2.getId());
        disciplinaDto2.setNome(disciplina2.getNome());
        disciplinaDto2.setCodigo(disciplina2.getCodigo());
        disciplinaDto2.setNotas(Arrays.asList(notaDto2));

        AlunoResponseDTO alunoDto = new AlunoResponseDTO();
        alunoDto.setId(aluno.getId());
        alunoDto.setNome(aluno.getNome());
        alunoDto.setCpf(aluno.getCpf());
        alunoDto.setEmail(aluno.getEmail());
        alunoDto.setTelefone(aluno.getTelefone());
        alunoDto.setEndereco(aluno.getEndereco());
        alunoDto.setDisciplinas(Arrays.asList(disciplinaDto1, disciplinaDto2));

        assertNotNull(alunoDto);
        assertNotNull(aluno.toDto());
    }

    @Test
    public void testAllArgsConstructor() {
        Aluno aluno = new Aluno(1L, "Fulano", "123.456.789-00", "fulano@example.com", "(00) 1234-5678", "Rua Principal", null, null);

        assertThat(aluno).isNotNull();
        assertThat(aluno.getId()).isEqualTo(1L);
        assertThat(aluno.getNome()).isEqualTo("Fulano");
        assertThat(aluno.getCpf()).isEqualTo("123.456.789-00");
        assertThat(aluno.getEmail()).isEqualTo("fulano@example.com");
        assertThat(aluno.getTelefone()).isEqualTo("(00) 1234-5678");
        assertThat(aluno.getEndereco()).isEqualTo("Rua Principal");
    }

    @Test
    public void testBuilder() {
        Aluno aluno = Aluno.builder()
                .id(1L)
                .nome("Fulano")
                .cpf("123.456.789-00")
                .email("fulano@example.com")
                .telefone("(00) 1234-5678")
                .endereco("Rua Principal")
                .build();

        assertThat(aluno).isNotNull();
        assertThat(aluno.getId()).isEqualTo(1L);
        assertThat(aluno.getNome()).isEqualTo("Fulano");
        assertThat(aluno.getCpf()).isEqualTo("123.456.789-00");
        assertThat(aluno.getEmail()).isEqualTo("fulano@example.com");
        assertThat(aluno.getTelefone()).isEqualTo("(00) 1234-5678");
        assertThat(aluno.getEndereco()).isEqualTo("Rua Principal");
    }
}
