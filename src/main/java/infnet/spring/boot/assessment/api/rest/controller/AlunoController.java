package infnet.spring.boot.assessment.api.rest.controller;

import infnet.spring.boot.assessment.api.rest.dto.request.AlunoRequestDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.AlunoResponseDTO;
import infnet.spring.boot.assessment.domain.service.AlunoService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/aluno")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<List<AlunoResponseDTO>> findAll(){
        return ResponseEntity.ok(alunoService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<AlunoResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(alunoService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<AlunoResponseDTO> create(@RequestBody AlunoRequestDTO aluno) {
        AlunoResponseDTO alunoDb = alunoService.create(aluno);
        URI location = URI.create(String.format("/api/aluno/%d", alunoDb.getId()));
        return ResponseEntity.created(location).body(alunoDb);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<AlunoResponseDTO> update(@PathVariable Long id, @RequestBody AlunoRequestDTO aluno) {
        return ResponseEntity.ok(alunoService.update(id, aluno));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        alunoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{alunoId}/alocar-disciplina/{disciplinaId}")
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<AlunoResponseDTO> alocarAlunoEmDisciplina(@PathVariable Long alunoId, @PathVariable Long disciplinaId) {
        return ResponseEntity.ok(alunoService.alocarAlunoEmDisciplina(alunoId, disciplinaId));
    }

    @GetMapping("/alunos-aprovados")
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<List<AlunoResponseDTO>> findAlunosAprovados(@RequestParam("disciplinaId") Long disciplinaId) {
        return ResponseEntity.ok(alunoService.findAlunosAprovados(disciplinaId));
    }

    @GetMapping("/alunos-reprovados")
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<List<AlunoResponseDTO>> findAlunosReprovados(@RequestParam("disciplinaId") Long disciplinaId) {
        return ResponseEntity.ok(alunoService.findAlunosReprovados(disciplinaId));
    }
}
