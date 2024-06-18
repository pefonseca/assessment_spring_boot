package infnet.spring.boot.assessment.domain.entity;

import infnet.spring.boot.assessment.api.rest.dto.response.DisciplinaResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DisciplinaTest {

    @Test
    public void testToDto_NoNotas() {
        Disciplina disciplina = Disciplina.builder()
                .id(1L)
                .nome("Matemática")
                .codigo("MAT101")
                .alunos(Collections.emptyList())
                .notas(Collections.emptyList())
                .build();

        DisciplinaResponseDTO dto = disciplina.toDto();

        assertNotNull(dto);
    }

    @Test
    public void testToDto_WithNotas() {
        Disciplina disciplina = Disciplina.builder()
                .id(1L)
                .nome("Física")
                .codigo("FIS102")
                .alunos(Collections.emptyList())
                .notas(Collections.singletonList(new Nota(1L, 8.5)))
                .build();

        DisciplinaResponseDTO dto = disciplina.toDto();

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getNome()).isEqualTo("Física");
        assertThat(dto.getCodigo()).isEqualTo("FIS102");
        assertThat(dto.getNotas()).isNotNull();
        assertThat(dto.getNotas()).hasSize(1);
        assertThat(dto.getNotas().get(0).getId()).isEqualTo(1L);
        assertThat(dto.getNotas().get(0).getValor()).isEqualTo(8.5);
    }
}
