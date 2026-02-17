package br.com.simula.service;

import br.com.simula.dto.GerarSimuladoRequest;
import br.com.simula.dto.GerarSimuladoRequest.ConfiguracaoMateriaRequest;
import br.com.simula.entity.*;
import br.com.simula.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GerarSimuladoService {

    private final SimuladoRepository simuladoRepository;
    private final SimuladoQuestaoRepository simuladoQuestaoRepository;
    private final SimuladoConfiguracaoRepository configuracaoRepository;
    private final SimuladoService simuladoService;
    private final MateriaService materiaService;
    private final AssuntoService assuntoService;
    private final TopicoService topicoService;
    private final OrgaoService orgaoService;
    private final CargoService cargoService;
    private final EntityManager entityManager;

    @Transactional
    public Simulado gerar(GerarSimuladoRequest request) {
        Orgao orgao = orgaoService.findById(request.getOrgaoId());

        Simulado simulado = Simulado.builder()
                .titulo(request.getTitulo())
                .orgao(orgao)
                .cargo(request.getCargoId() != null ? cargoService.findById(request.getCargoId()) : null)
                .ano(request.getAno())
                .dataCriacao(request.getDataCriacao())
                .ordemMaterias(extrairOrdemMaterias(request.getConfiguracaoMaterias()))
                .build();
        simulado = simuladoRepository.save(simulado);

        Set<Long> questoesJaUsadas = buscarQuestoesJaUsadas(request.getOrgaoId(), request.getAno());
        Set<Long> questoesSelecionadasNoSimulado = new HashSet<>();
        List<SimuladoConfiguracao> configuracoes = new ArrayList<>();
        List<SimuladoQuestao> questoesDoSimulado = new ArrayList<>();
        int ordem = 1;

        for (ConfiguracaoMateriaRequest config : request.getConfiguracaoMaterias()) {
            Materia materia = materiaService.findById(config.getMateriaId());
            Assunto assunto = config.getAssuntoId() != null ? assuntoService.findById(config.getAssuntoId()) : null;
            Topico topico = config.getTopicoId() != null ? topicoService.findById(config.getTopicoId()) : null;

            Set<Long> idsExcluidos = new HashSet<>();
            idsExcluidos.addAll(questoesJaUsadas);
            idsExcluidos.addAll(questoesSelecionadasNoSimulado);

            List<Long> questoesDisponiveis = buscarQuestoesDisponiveis(
                    config.getMateriaId(),
                    config.getAssuntoId(),
                    config.getTopicoId(),
                    request.getBancaId(),
                    idsExcluidos
            );

            List<Long> selecionadas = selecionarAleatoriamente(questoesDisponiveis, config.getQuantidade());
            questoesSelecionadasNoSimulado.addAll(selecionadas);

            SimuladoConfiguracao configuracao = SimuladoConfiguracao.builder()
                    .simulado(simulado)
                    .materia(materia)
                    .assunto(assunto)
                    .topico(topico)
                    .quantidade(config.getQuantidade())
                    .quantidadeSelecionada(selecionadas.size())
                    .build();
            configuracoes.add(configuracaoRepository.save(configuracao));

            for (Long questaoId : selecionadas) {
                SimuladoQuestao sq = SimuladoQuestao.builder()
                        .simulado(simulado)
                        .questao(entityManager.getReference(Questao.class, questaoId))
                        .ordem(ordem++)
                        .build();
                questoesDoSimulado.add(simuladoQuestaoRepository.save(sq));
            }
        }

        simulado.setConfiguracoes(configuracoes);
        simulado.setSimuladosQuestoes(questoesDoSimulado);

        return simuladoService.findByIdCompleto(simulado.getId());
    }

    private Set<Long> buscarQuestoesJaUsadas(Long orgaoId, Integer ano) {
        if (orgaoId == null || ano == null) {
            return Collections.emptySet();
        }
        return new HashSet<>(simuladoQuestaoRepository.findQuestaoIdsUsadasByOrgaoAndAno(orgaoId, ano));
    }

    private List<Long> buscarQuestoesDisponiveis(Long materiaId, Long assuntoId, Long topicoId,
                                                  Long bancaId, Set<Long> idsExcluidos) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Questao> root = query.from(Questao.class);
        query.select(root.get("id"));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("materia").get("id"), materiaId));

        if (assuntoId != null) {
            predicates.add(cb.equal(root.get("assunto").get("id"), assuntoId));
        }
        if (topicoId != null) {
            predicates.add(cb.equal(root.get("topico").get("id"), topicoId));
        }
        if (bancaId != null) {
            predicates.add(cb.equal(root.get("banca").get("id"), bancaId));
        }
        if (!idsExcluidos.isEmpty()) {
            predicates.add(cb.not(root.get("id").in(idsExcluidos)));
        }

        query.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Long> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    private List<Long> selecionarAleatoriamente(List<Long> disponiveis, int quantidade) {
        if (disponiveis.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> copia = new ArrayList<>(disponiveis);
        Collections.shuffle(copia, ThreadLocalRandom.current());
        return copia.subList(0, Math.min(quantidade, copia.size()));
    }

    private List<Long> extrairOrdemMaterias(List<ConfiguracaoMateriaRequest> configs) {
        return configs.stream()
                .map(ConfiguracaoMateriaRequest::getMateriaId)
                .distinct()
                .collect(Collectors.toList());
    }
}
