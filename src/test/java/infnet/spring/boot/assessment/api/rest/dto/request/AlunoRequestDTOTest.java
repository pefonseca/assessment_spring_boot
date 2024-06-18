package infnet.spring.boot.assessment.api.rest.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AlunoRequestDTOTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void testValidAlunoRequestDTO() {
        AlunoRequestDTO aluno = AlunoRequestDTO.builder()
                .nome("João da Silva")
                .cpf("123.456.789-00")
                .email("joao.silva@example.com")
                .telefone("(99) 99999-9999")
                .endereco("Rua A, 123")
                .build();

        Set<ConstraintViolation<AlunoRequestDTO>> violations = validator.validate(aluno);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testInvalidAlunoRequestDTO() {
        AlunoRequestDTO aluno = AlunoRequestDTO.builder()
                .nome("Maria Oliveira")
                .cpf("123.456.789-01")
                .email("maria.oliveira@example.com")
                .telefone("9999-9999")
                .endereco("Avenida B, 456")
                .build();

        Set<ConstraintViolation<AlunoRequestDTO>> violations = validator.validate(aluno);
        assertEquals(1, violations.size());
    }

    @Test
    void testGettersAndSetters() {
        AlunoRequestDTO alunoRequestDTO = new AlunoRequestDTO();
        alunoRequestDTO.setNome("nome");
        alunoRequestDTO.setEmail("email");
        alunoRequestDTO.setTelefone("telefone");
        alunoRequestDTO.setEndereco("endereco");
        alunoRequestDTO.setCpf("cpf");

        assertEquals("nome", alunoRequestDTO.getNome());
        assertEquals("email", alunoRequestDTO.getEmail());
        assertEquals("telefone", alunoRequestDTO.getTelefone());
        assertEquals("endereco", alunoRequestDTO.getEndereco());
        assertEquals("cpf", alunoRequestDTO.getCpf());
    }
}
