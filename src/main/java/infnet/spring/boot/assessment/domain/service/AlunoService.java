package infnet.spring.boot.assessment.domain.service;

import infnet.spring.boot.assessment.api.rest.dto.request.AlunoRequestDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.AlunoResponseDTO;

import java.util.List;

public interface AlunoService {

    List<AlunoResponseDTO> findAll();
    AlunoResponseDTO findById(Long id);
    AlunoResponseDTO create(AlunoRequestDTO alunoRequestDTO);
    AlunoResponseDTO update(Long id, AlunoRequestDTO alunoRequestDTO);
    void deleteById(Long id);
    AlunoResponseDTO alocarAlunoEmDisciplina(Long alunoId, Long disciplinaId);
    List<AlunoResponseDTO> findAlunosAprovados(Long disciplinaId);
    List<AlunoResponseDTO> findAlunosReprovados(Long disciplinaId);
}
