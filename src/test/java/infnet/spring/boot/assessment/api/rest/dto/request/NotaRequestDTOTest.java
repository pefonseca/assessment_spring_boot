package infnet.spring.boot.assessment.api.rest.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NotaRequestDTOTest {

    @Test
    void testNoArgsConstructor() {
        NotaRequestDTO nota = new NotaRequestDTO();
        assertNotNull(nota);
    }

    @Test
    void testAllArgsConstructor() {
        NotaRequestDTO nota = new NotaRequestDTO(8.5, 1L, 2L);
        assertNotNull(nota);
        assertEquals(8.5, nota.getValor());
        assertEquals(1L, nota.getAlunoId());
        assertEquals(2L, nota.getDisciplinaId());
    }

    @Test
    void testBuilder() {
        NotaRequestDTO nota = NotaRequestDTO.builder()
                .valor(9.0)
                .alunoId(3L)
                .disciplinaId(4L)
                .build();
        assertNotNull(nota);
        assertEquals(9.0, nota.getValor());
        assertEquals(3L, nota.getAlunoId());
        assertEquals(4L, nota.getDisciplinaId());
    }

    @Test
    void testGettersAndSetters() {
        NotaRequestDTO nota = new NotaRequestDTO();
        nota.setValor(7.5);
        nota.setAlunoId(5L);
        nota.setDisciplinaId(6L);

        assertEquals(7.5, nota.getValor());
        assertEquals(5L, nota.getAlunoId());
        assertEquals(6L, nota.getDisciplinaId());
    }
}
