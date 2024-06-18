package infnet.spring.boot.assessment.api.rest.controller;

import infnet.spring.boot.assessment.domain.entity.Professor;
import infnet.spring.boot.assessment.domain.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/professor")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody final Professor professor) {
        var result = professorService.create(professor);
        URI location = URI.create(String.format("/api/professor/%d", result.getId()));
        return ResponseEntity.created(location).body(result);
    }
}
