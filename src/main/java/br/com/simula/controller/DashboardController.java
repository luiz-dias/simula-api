package br.com.simula.controller;

import br.com.simula.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final QuestaoRepository questaoRepository;
    private final MateriaRepository materiaRepository;
    private final AssuntoRepository assuntoRepository;
    private final TopicoRepository topicoRepository;
    private final OrgaoRepository orgaoRepository;
    private final BancaRepository bancaRepository;
    private final CargoRepository cargoRepository;
    private final SimuladoRepository simuladoRepository;
    private final TipoRepository tipoRepository;

    @GetMapping("/estatisticas")
    public Map<String, Long> estatisticas() {
        return Map.of(
                "totalQuestoes", questaoRepository.count(),
                "totalMaterias", materiaRepository.count(),
                "totalAssuntos", assuntoRepository.count(),
                "totalTopicos", topicoRepository.count(),
                "totalOrgaos", orgaoRepository.count(),
                "totalBancas", bancaRepository.count(),
                "totalCargos", cargoRepository.count(),
                "totalTipos", tipoRepository.count(),
                "totalSimulados", simuladoRepository.count()
        );
    }
}
