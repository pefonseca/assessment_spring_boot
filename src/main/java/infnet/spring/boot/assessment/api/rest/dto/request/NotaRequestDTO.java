package infnet.spring.boot.assessment.api.rest.dto.request;

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
public class NotaRequestDTO {

    private Double valor;
    private Long alunoId;
    private Long disciplinaId;

}
