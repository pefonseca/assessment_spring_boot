package infnet.spring.boot.assessment.domain.entity;

import infnet.spring.boot.assessment.api.rest.dto.response.NotaResponseDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NotaTest {

    @Test
    public void testToDto() {
        Nota nota = Nota.builder()
                .id(1L)
                .valor(8.5)
                .build();

        NotaResponseDTO dto = nota.toDto();

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getValor()).isEqualTo(8.5);
    }

    @Test
    public void testAllArgsConstructor() {
        Nota nota = new Nota(1L, 9.0);

        assertThat(nota.getId()).isEqualTo(1L);
        assertThat(nota.getValor()).isEqualTo(9.0);
    }

    @Test
    public void testBuilder() {
        Nota nota = Nota.builder()
                .id(2L)
                .valor(7.5)
                .build();

        assertThat(nota.getId()).isEqualTo(2L);
        assertThat(nota.getValor()).isEqualTo(7.5);
    }
}
