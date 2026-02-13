package br.com.simula.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimuladoQuestaoRequest {

    @NotNull
    private Long questaoId;

    @NotNull
    private Integer ordem;
}
