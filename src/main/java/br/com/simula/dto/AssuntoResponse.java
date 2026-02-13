package br.com.simula.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssuntoResponse {

    private Long id;
    private String nome;
    private Long materiaId;
    private MateriaResponse materia;
    private Instant createdAt;
    private Instant updatedAt;
}
