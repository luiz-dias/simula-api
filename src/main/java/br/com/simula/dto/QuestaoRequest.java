package br.com.simula.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestaoRequest {

    @NotNull
    private String enunciado;

    private String alternativaA;
    private String alternativaB;
    private String alternativaC;
    private String alternativaD;
    private String alternativaE;

    private String respostaCorreta; // A, B, C, D ou E

    private Long tipoId;

    @NotNull
    private Long materiaId;

    private Long assuntoId;
    private Long topicoId;
    private Long orgaoId;
    private Long bancaId;
    private Long cargoId;

    private Integer ano;
}
