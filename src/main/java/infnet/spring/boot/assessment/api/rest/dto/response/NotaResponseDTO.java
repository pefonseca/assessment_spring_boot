package infnet.spring.boot.assessment.api.rest.dto.response;

import infnet.spring.boot.assessment.domain.entity.Nota;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotaResponseDTO {

    private Long id;
    private Double valor;

    public Nota toEntity() {
        Nota nota = new Nota();
        nota.setId(id);
        nota.setValor(valor);
        return nota;
    }
}
