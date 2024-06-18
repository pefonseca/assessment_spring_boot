package infnet.spring.boot.assessment.domain.service.impl;

import infnet.spring.boot.assessment.domain.entity.Professor;
import infnet.spring.boot.assessment.domain.repository.ProfessorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProfessorServiceImplTest {

    @Mock
    private ProfessorRepository professorRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ProfessorServiceImpl professorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        Professor professor = new Professor();
        professor.setEmail("professor@example.com");
        professor.setPassword("plainPassword");
        professor.setRoles("ROLE_PROFESSOR");
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(professor.getPassword())).thenReturn(encodedPassword);
        when(professorRepository.save(any(Professor.class))).thenReturn(professor);
        Professor createdProfessor = professorService.create(professor);
        assertThat(createdProfessor).isNotNull();
        assertThat(createdProfessor.getEmail()).isEqualTo(professor.getEmail());
        assertThat(createdProfessor.getRoles()).isEqualTo(professor.getRoles());
        assertThat(createdProfessor.getPassword()).isEqualTo(encodedPassword);
        verify(professorRepository).save(professor);
    }
}
