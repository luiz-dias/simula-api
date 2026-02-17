package br.com.simula.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimuladoConfiguracaoResponse {

    private Long id;
    private MateriaResponse materia;
    private AssuntoResponse assunto;
    private TopicoResponse topico;
    private Integer quantidade;
    private Integer quantidadeSelecionada;
}
