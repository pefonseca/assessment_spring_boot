package infnet.spring.boot.assessment.api.rest.controller;

import infnet.spring.boot.assessment.api.rest.dto.request.DisciplinaRequestDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.DisciplinaResponseDTO;
import infnet.spring.boot.assessment.domain.entity.Disciplina;
import infnet.spring.boot.assessment.domain.service.DisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/disciplina")
@RequiredArgsConstructor
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @GetMapping
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<List<DisciplinaResponseDTO>> findAll() {
        return ResponseEntity.ok(disciplinaService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<DisciplinaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(disciplinaService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<DisciplinaResponseDTO> create(@RequestBody DisciplinaRequestDTO disciplinaRequestDTO) {
        DisciplinaResponseDTO disciplinaDb = disciplinaService.create(disciplinaRequestDTO);
        URI location = URI.create(String.format("/api/aluno/%d", disciplinaDb.getId()));
        return ResponseEntity.created(location).body(disciplinaDb);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<DisciplinaResponseDTO> update(@PathVariable Long id, @RequestBody DisciplinaRequestDTO disciplinaDTO) {
        return ResponseEntity.ok(disciplinaService.update(id, disciplinaDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<Disciplina> delete(@PathVariable Long id) {
        disciplinaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
