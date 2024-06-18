package infnet.spring.boot.assessment.api.rest.controller;

import infnet.spring.boot.assessment.api.rest.dto.request.NotaRequestDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.NotaResponseDTO;
import infnet.spring.boot.assessment.domain.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nota")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @PostMapping
    @PreAuthorize("hasAuthority('PROFESSOR')")
    private ResponseEntity<NotaResponseDTO> adicionarNotaParaAluno(@RequestBody NotaRequestDTO notaRequestDTO) {
        return ResponseEntity.ok(notaService.adicionarNotaParaAluno(notaRequestDTO));
    }
}
