package infnet.spring.boot.assessment.domain.service;

import infnet.spring.boot.assessment.api.rest.dto.request.DisciplinaRequestDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.DisciplinaResponseDTO;

import java.util.List;

public interface DisciplinaService {

    List<DisciplinaResponseDTO> findAll();
    DisciplinaResponseDTO findById(Long id);
    DisciplinaResponseDTO create(DisciplinaRequestDTO disciplinaRequestDTO);
    DisciplinaResponseDTO update(Long id, DisciplinaRequestDTO disciplinaRequestDTO);
    void deleteById(Long id);

}
