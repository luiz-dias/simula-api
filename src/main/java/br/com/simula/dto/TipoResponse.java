package br.com.simula.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoResponse {

    private Long id;
    private String nome;
    private Instant createdAt;
    private Instant updatedAt;
}
