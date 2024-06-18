package infnet.spring.boot.assessment.api.rest.dto.response;

import infnet.spring.boot.assessment.domain.entity.Disciplina;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisciplinaResponseDTO {

    private Long id;
    private String nome;
    private String codigo;
    private List<NotaResponseDTO> notas = new ArrayList<>();

    public Disciplina toEntity() {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(this.id);
        disciplina.setNome(this.nome);
        disciplina.setCodigo(this.codigo);
        disciplina.setNotas(this.notas.stream().map(NotaResponseDTO::toEntity).toList());
        return disciplina;
    }

}
