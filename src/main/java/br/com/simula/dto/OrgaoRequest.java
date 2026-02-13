package br.com.simula.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrgaoRequest {

    @NotBlank
    private String nome;

    private String sigla;
}
