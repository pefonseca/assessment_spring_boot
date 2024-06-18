package infnet.spring.boot.assessment.domain.service.impl;

import infnet.spring.boot.assessment.api.rest.dto.request.DisciplinaRequestDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.DisciplinaResponseDTO;
import infnet.spring.boot.assessment.domain.entity.Disciplina;
import infnet.spring.boot.assessment.domain.repository.DisciplinaRepository;
import infnet.spring.boot.assessment.domain.service.DisciplinaService;
import infnet.spring.boot.assessment.infra.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    @Override
    public List<DisciplinaResponseDTO> findAll() {
        return disciplinaRepository.findAll().stream().map(Disciplina::toDto).toList();
    }

    @Override
    public DisciplinaResponseDTO findById(Long id) {
        var disciplinaDb = disciplinaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Disciplina not found"));
        return disciplinaDb.toDto();
    }

    @Override
    public DisciplinaResponseDTO create(DisciplinaRequestDTO disciplinaRequestDTO) {
        Disciplina disciplina = new Disciplina();
        BeanUtils.copyProperties(disciplinaRequestDTO, disciplina);
        var disciplinaDb = disciplinaRepository.save(disciplina);
        DisciplinaResponseDTO disciplinaDTO = new DisciplinaResponseDTO();
        BeanUtils.copyProperties(disciplinaDb, disciplinaDTO);
        return disciplinaDTO;
    }

    @Override
    public DisciplinaResponseDTO update(Long id, DisciplinaRequestDTO disciplinaRequestDTO) {
        DisciplinaResponseDTO disciplina = findById(id);
        BeanUtils.copyProperties(disciplinaRequestDTO, disciplina, "id");
        return disciplina;
    }

    @Override
    public void deleteById(Long id) {
        DisciplinaResponseDTO disciplinaResponseDTO = findById(id);
        Disciplina disciplina = new Disciplina();
        BeanUtils.copyProperties(disciplinaResponseDTO, disciplina, "id");
        disciplinaRepository.delete(disciplina);
    }
}
