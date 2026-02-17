package br.com.simula.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimuladoQuestaoResponse {

    private Long id;
    private Integer ordem;
    private QuestaoResponse questao;
}
