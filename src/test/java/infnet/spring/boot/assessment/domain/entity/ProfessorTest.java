package infnet.spring.boot.assessment.domain.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfessorTest {

    @Test
    public void testBuilder() {
        Professor professor = Professor.builder()
                .id(1L)
                .email("professor@example.com")
                .password("password123")
                .roles("ROLE_ADMIN")
                .build();

        assertThat(professor).isNotNull();
        assertThat(professor.getId()).isEqualTo(1L);
        assertThat(professor.getEmail()).isEqualTo("professor@example.com");
        assertThat(professor.getPassword()).isEqualTo("password123");
        assertThat(professor.getRoles()).isEqualTo("ROLE_ADMIN");
    }
}
