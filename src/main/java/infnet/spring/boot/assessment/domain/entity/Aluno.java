package infnet.spring.boot.assessment.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import infnet.spring.boot.assessment.api.rest.dto.response.AlunoResponseDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.DisciplinaResponseDTO;
import infnet.spring.boot.assessment.api.rest.dto.response.NotaResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Entity(name = "tb_aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;

    @ManyToMany
    @JoinTable(
            name = "aluno_disciplina",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    @JsonIgnoreProperties("alunos")
    private List<Disciplina> disciplinas;

    @OneToMany(mappedBy = "aluno")
    private List<Nota> notas;

    public AlunoResponseDTO toDto() {
        AlunoResponseDTO dto = new AlunoResponseDTO();
        dto.setId(this.getId());
        dto.setNome(this.getNome());
        dto.setCpf(this.getCpf());
        dto.setEmail(this.getEmail());
        dto.setTelefone(this.getTelefone());
        dto.setEndereco(this.getEndereco());

        List<DisciplinaResponseDTO> disciplinasDTO = this.getDisciplinas().stream()
                .map(disciplina -> {
                    DisciplinaResponseDTO disciplinaDTO = new DisciplinaResponseDTO();
                    disciplinaDTO.setId(disciplina.getId());
                    disciplinaDTO.setNome(disciplina.getNome());
                    disciplinaDTO.setCodigo(disciplina.getCodigo());

                    Nota nota = this.getNotas().stream()
                            .filter(n -> n.getDisciplina().equals(disciplina))
                            .findFirst().orElse(null);

                    if (nota != null) {
                        NotaResponseDTO notaDTO = new NotaResponseDTO();
                        notaDTO.setId(nota.getId());
                        notaDTO.setValor(nota.getValor());
                        disciplinaDTO.setNotas(this.notas.stream().map(Nota::toDto).toList());
                    }

                    return disciplinaDTO;
                }).toList();

        dto.setDisciplinas(disciplinasDTO);

        return dto;
    }
}
