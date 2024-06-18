package infnet.spring.boot.assessment.config;

import infnet.spring.boot.assessment.domain.entity.Professor;
import infnet.spring.boot.assessment.domain.repository.ProfessorRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DadosIniciaisProfessor {

    private final ProfessorRepository professorRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void popularBaseDeDados() {
        if(professorRepository.count() == 0) {
            Professor professor = Professor.builder().email("prof@prof.com").password(passwordEncoder.encode("prof123")).roles("PROFESSOR").build();
            professorRepository.save(professor);
            System.out.println("Base de dados de professor populada com sucesso.");
        } else {
            System.out.println("Base de dados já contém professor cadastrado.");
        }
    }

}
