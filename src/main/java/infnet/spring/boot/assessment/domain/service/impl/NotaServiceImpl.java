package infnet.spring.boot.assessment.domain.service.impl;

import infnet.spring.boot.assessment.api.rest.dto.request.NotaRequestDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.NotaResponseDTO;
import infnet.spring.boot.assessment.domain.entity.Aluno;
import infnet.spring.boot.assessment.domain.entity.Disciplina;
import infnet.spring.boot.assessment.domain.entity.Nota;
import infnet.spring.boot.assessment.domain.repository.NotaRepository;
import infnet.spring.boot.assessment.domain.service.AlunoService;
import infnet.spring.boot.assessment.domain.service.DisciplinaService;
import infnet.spring.boot.assessment.domain.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService {

    private final NotaRepository repository;
    private final AlunoService alunoService;
    private final DisciplinaService disciplinaService;

    @Override
    public NotaResponseDTO adicionarNotaParaAluno(NotaRequestDTO nota) {
        Aluno aluno = alunoService.findById(nota.getAlunoId()).toEntity();
        Disciplina disciplina = disciplinaService.findById(nota.getDisciplinaId()).toEntity();
        Nota notaDb = repository.save(Nota.builder().valor(nota.getValor()).aluno(aluno).disciplina(disciplina).build());
        return notaDb.toDto();
    }
}
