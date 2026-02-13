package br.com.simula.dto;

import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimuladoResponse {

    private Long id;
    private String titulo;
    private Long cargoId;
    private Long orgaoId;
    private Integer ano;
    private LocalDate dataCriacao;
    private List<Long> ordemMaterias;
    private List<SimuladoQuestaoResponse> simuladosQuestoes;
    private Instant createdAt;
    private Instant updatedAt;
}
