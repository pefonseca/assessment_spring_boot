package infnet.spring.boot.assessment.domain.service.impl;

import infnet.spring.boot.assessment.api.rest.dto.request.AlunoRequestDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.AlunoResponseDTO;
import infnet.spring.boot.assessment.domain.entity.Aluno;
import infnet.spring.boot.assessment.domain.entity.Disciplina;
import infnet.spring.boot.assessment.domain.repository.AlunoRepository;
import infnet.spring.boot.assessment.domain.repository.DisciplinaRepository;
import infnet.spring.boot.assessment.domain.service.AlunoService;
import infnet.spring.boot.assessment.infra.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;

    @Override
    public List<AlunoResponseDTO> findAll() {
        return alunoRepository.findAll().stream().map(Aluno::toDto).toList();
    }

    @Override
    public AlunoResponseDTO findById(Long id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aluno not Found."));
        return aluno.toDto();
    }

    @Override
    public AlunoResponseDTO create(AlunoRequestDTO alunoRequestDTO) {
        Aluno aluno = new Aluno();
        BeanUtils.copyProperties(alunoRequestDTO, aluno);
        var alunoDb = alunoRepository.save(aluno);
        AlunoResponseDTO alunoResponseDTO = new AlunoResponseDTO();
        BeanUtils.copyProperties(alunoDb, alunoResponseDTO);
        return alunoResponseDTO;
    }

    @Override
    public AlunoResponseDTO update(Long id, AlunoRequestDTO alunoRequestDTO) {
        AlunoResponseDTO aluno = findById(id);
        Aluno alunoDb = new Aluno();
        BeanUtils.copyProperties(aluno, alunoDb, "id");
        var alunoSaved = alunoRepository.save(alunoDb);
        BeanUtils.copyProperties(alunoSaved, aluno, "id");
        return aluno;
    }

    @Override
    public void deleteById(Long id) {
        alunoRepository.deleteById(id);
    }

    @Override
    public AlunoResponseDTO alocarAlunoEmDisciplina(Long alunoId, Long disciplinaId) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado."));
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId).orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada."));

        if(aluno != null && disciplina != null) {
            if(!aluno.getDisciplinas().contains(disciplina)) {
                aluno.getDisciplinas().add(disciplina);
                alunoRepository.save(aluno);
            }
        }

        return aluno.toDto();
    }

    @Override
    public List<AlunoResponseDTO> findAlunosAprovados(Long disciplinaId) {
        return alunoRepository.findAlunosPorDisciplinaComNotaMaiorOuIgualSete(disciplinaId).stream().map(Aluno::toDto).toList();
    }

    @Override
    public List<AlunoResponseDTO> findAlunosReprovados(Long disciplinaId) {
        return alunoRepository.findAlunosPorDisciplinaComNotaMenorQueSete(disciplinaId).stream().map(Aluno::toDto).toList();
    }
}
