package br.com.simula.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestaoResponse {

    private Long id;
    private String enunciado;
    private String alternativaA;
    private String alternativaB;
    private String alternativaC;
    private String alternativaD;
    private String alternativaE;
    private String respostaCorreta;

    private Long tipoId;
    private Long materiaId;
    private Long assuntoId;
    private Long topicoId;
    private Long orgaoId;
    private Long bancaId;
    private Long cargoId;

    private Integer ano;
    private Instant createdAt;
    private Instant updatedAt;
}
