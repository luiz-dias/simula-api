package br.com.simula.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CargoRequest {

    @NotBlank
    private String nome;

    @NotNull
    private Long orgaoId;
}
