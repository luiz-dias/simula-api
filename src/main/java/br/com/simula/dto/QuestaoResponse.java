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

    private TipoResponse tipo;
    private MateriaResponse materia;
    private AssuntoResponse assunto;
    private TopicoResponse topico;
    private OrgaoResponse orgao;
    private BancaResponse banca;
    private CargoResponse cargo;

    private Integer ano;
    private Instant createdAt;
    private Instant updatedAt;
}
