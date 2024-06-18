package infnet.spring.boot.assessment.api.rest.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DisciplinaRequestDTOTest {

    @Test
    void testNoArgsConstructor() {
        DisciplinaRequestDTO disciplina = new DisciplinaRequestDTO();
        assertNotNull(disciplina);
    }

    @Test
    void testAllArgsConstructor() {
        DisciplinaRequestDTO disciplina = new DisciplinaRequestDTO("Matemática", "MAT123");
        assertNotNull(disciplina);
        assertEquals("Matemática", disciplina.getNome());
        assertEquals("MAT123", disciplina.getCodigo());
    }

    @Test
    void testBuilder() {
        DisciplinaRequestDTO disciplina = DisciplinaRequestDTO.builder()
                .nome("Física")
                .codigo("FIS456")
                .build();
        assertNotNull(disciplina);
        assertEquals("Física", disciplina.getNome());
        assertEquals("FIS456", disciplina.getCodigo());
    }

    @Test
    void testGettersAndSetters() {
        DisciplinaRequestDTO disciplina = new DisciplinaRequestDTO();
        disciplina.setNome("História");
        disciplina.setCodigo("HIS789");

        assertEquals("História", disciplina.getNome());
        assertEquals("HIS789", disciplina.getCodigo());
    }
}
