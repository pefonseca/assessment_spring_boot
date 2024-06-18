package infnet.spring.boot.assessment.config;

import infnet.spring.boot.assessment.domain.entity.Professor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoDetails implements UserDetails {
    private final String email;
    private final String password;
    private final List<GrantedAuthority> roles;

    public UserInfoDetails(Professor professor) {
        this.email = professor.getEmail();
        this.password = professor.getPassword();
        this.roles = Arrays.stream(professor.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
