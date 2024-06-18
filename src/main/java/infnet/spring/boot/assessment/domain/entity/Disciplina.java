package infnet.spring.boot.assessment.domain.entity;

import infnet.spring.boot.assessment.api.rest.dto.response.DisciplinaResponseDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.NotaResponseDTO;
import infnet.spring.boot.assessment.infra.exception.ResourceNotFoundException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String codigo;

    @ManyToMany(mappedBy = "disciplinas")
    private List<Aluno> alunos;

    @OneToMany(mappedBy = "disciplina")
    private List<Nota> notas;

    public DisciplinaResponseDTO toDto() {
        DisciplinaResponseDTO disciplinaDTO = new DisciplinaResponseDTO();
        disciplinaDTO.setId(this.getId());
        disciplinaDTO.setNome(this.getNome());
        disciplinaDTO.setCodigo(this.getCodigo());

        if(this.getNotas() != null && !this.getNotas().isEmpty()) {
            Nota nota = this.getNotas().stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("Disciplina not found."));
            NotaResponseDTO notaDTO = new NotaResponseDTO();
            notaDTO.setId(nota.getId());
            notaDTO.setValor(nota.getValor());
            disciplinaDTO.setNotas(this.notas.stream().map(Nota::toDto).toList());
        }

        return disciplinaDTO;
    }

}
