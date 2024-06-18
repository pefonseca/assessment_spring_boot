package infnet.spring.boot.assessment.config;

import infnet.spring.boot.assessment.domain.entity.Professor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserInfoDetailsTest {

    @Test
    public void testGetAuthorities() {
        Professor professor = new Professor();
        professor.setEmail("professor@example.com");
        professor.setPassword("password");
        professor.setRoles("ROLE_ADMIN,ROLE_USER");

        UserInfoDetails userInfoDetails = new UserInfoDetails(professor);

        Collection<? extends GrantedAuthority> authorities = userInfoDetails.getAuthorities();

        List<String> authorityStrings = Arrays.asList("ROLE_ADMIN", "ROLE_USER");
        List<SimpleGrantedAuthority> expectedAuthorities = authorityStrings.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        assertEquals(expectedAuthorities.size(), authorities.size());
        assertEquals(expectedAuthorities.get(0).getAuthority(), authorities.iterator().next().getAuthority());
    }

    @Test
    public void testGetPassword() {
        Professor professor = new Professor();
        professor.setEmail("professor@example.com");
        professor.setPassword("password");
        professor.setRoles("ROLE_ADMIN,ROLE_USER");

        UserInfoDetails userInfoDetails = new UserInfoDetails(professor);

        String password = userInfoDetails.getPassword();

        assertEquals("password", password);
    }

    @Test
    public void testGetUsername() {
        Professor professor = new Professor();
        professor.setEmail("professor@example.com");
        professor.setPassword("password");
        professor.setRoles("ROLE_ADMIN,ROLE_USER");

        UserInfoDetails userInfoDetails = new UserInfoDetails(professor);

        String username = userInfoDetails.getUsername();

        assertEquals("professor@example.com", username);
    }
}
