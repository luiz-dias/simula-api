package br.com.simula.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimuladoRequest {

    @NotBlank
    private String titulo;

    private Long cargoId;
    private Long orgaoId;
    private Integer ano;
    private LocalDate dataCriacao;
    private List<Long> ordemMaterias;
    private List<SimuladoQuestaoRequest> simuladosQuestoes;
}
