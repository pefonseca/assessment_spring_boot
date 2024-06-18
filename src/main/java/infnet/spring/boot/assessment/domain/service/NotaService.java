package infnet.spring.boot.assessment.domain.service;

import infnet.spring.boot.assessment.api.rest.dto.request.NotaRequestDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.NotaResponseDTO;

public interface NotaService {

    NotaResponseDTO adicionarNotaParaAluno(NotaRequestDTO nota);

}
