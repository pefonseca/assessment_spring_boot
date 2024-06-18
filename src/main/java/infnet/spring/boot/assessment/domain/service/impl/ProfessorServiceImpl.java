package infnet.spring.boot.assessment.domain.service.impl;

import infnet.spring.boot.assessment.domain.entity.Professor;
import infnet.spring.boot.assessment.domain.repository.ProfessorRepository;
import infnet.spring.boot.assessment.domain.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Professor create(Professor professor) {
        professor.setPassword(passwordEncoder.encode(professor.getPassword()));
        return professorRepository.save(professor);
    }
}
