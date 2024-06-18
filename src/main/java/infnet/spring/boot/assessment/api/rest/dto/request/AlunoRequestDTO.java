package infnet.spring.boot.assessment.api.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoRequestDTO {

    private String nome;
    @CPF
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;

}
