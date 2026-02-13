package br.com.simula.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicoResponse {

    private Long id;
    private String nome;
    private Long assuntoId;
    private Instant createdAt;
    private Instant updatedAt;
}
