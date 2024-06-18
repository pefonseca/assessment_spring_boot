package infnet.spring.boot.assessment.config;

import infnet.spring.boot.assessment.domain.entity.Professor;
import infnet.spring.boot.assessment.domain.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
public class UserInfoDetailsService implements UserDetailsService {

    @Autowired
    private ProfessorRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Professor> professor = repository.findByEmail(username);
        return professor.map(UserInfoDetails::new).orElseThrow(() -> new UsernameNotFoundException("User does not exist."));
    }
}
