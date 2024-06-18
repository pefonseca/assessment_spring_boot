package infnet.spring.boot.assessment.config;

import infnet.spring.boot.assessment.domain.entity.Professor;
import infnet.spring.boot.assessment.domain.repository.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserInfoDetailsServiceTest {

    @InjectMocks
    private UserInfoDetailsService service;

    @Mock
    private ProfessorRepository repository;

    @Test
    public void testLoadUserByUsername_Success() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(new Professor(1L, "username@example.com", "senha", "ROLE_USER")));
        assertNotNull(service.loadUserByUsername("username@example.com"));
    }
}

