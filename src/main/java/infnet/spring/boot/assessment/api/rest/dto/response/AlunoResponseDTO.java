package infnet.spring.boot.assessment.api.rest.dto.response;

import infnet.spring.boot.assessment.domain.entity.Aluno;
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
public class AlunoResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;
    private List<DisciplinaResponseDTO> disciplinas;

    public Aluno toEntity() {
        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setEmail(email);
        aluno.setTelefone(telefone);
        aluno.setEndereco(endereco);
        aluno.setDisciplinas(this.disciplinas.stream().map(DisciplinaResponseDTO::toEntity).toList());
        return aluno;
    }
}
