package br.com.simula.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrgaoResponse {

    private Long id;
    private String nome;
    private String sigla;
    private Instant createdAt;
    private Instant updatedAt;
}
