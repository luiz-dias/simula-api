package br.com.simula.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GerarSimuladoRequest {

    @NotBlank
    private String titulo;

    @NotNull
    private Long orgaoId;

    private Long cargoId;
    private Long bancaId;
    private Integer ano;
    private LocalDate dataCriacao;

    @NotEmpty
    @Valid
    private List<ConfiguracaoMateriaRequest> configuracaoMaterias;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ConfiguracaoMateriaRequest {

        @NotNull
        private Long materiaId;

        private Long assuntoId;
        private Long topicoId;

        @NotNull
        @Min(1)
        private Integer quantidade;
    }
}
