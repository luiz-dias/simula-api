package br.com.simula.mapper;

import br.com.simula.dto.*;
import br.com.simula.entity.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SimulaMapper {

    public MateriaResponse toResponse(Materia e) {
        if (e == null) return null;
        return MateriaResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }

    public AssuntoResponse toResponse(Assunto e) {
        if (e == null) return null;
        return AssuntoResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .materiaId(e.getMateria() != null ? e.getMateria().getId() : null)
                .materia(toResponse(e.getMateria()))
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }

    public TopicoResponse toResponse(Topico e) {
        if (e == null) return null;
        return TopicoResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .assuntoId(e.getAssunto() != null ? e.getAssunto().getId() : null)
                .assunto(toResponse(e.getAssunto()))
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }

    public OrgaoResponse toResponse(Orgao e) {
        if (e == null) return null;
        return OrgaoResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .sigla(e.getSigla())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }

    public BancaResponse toResponse(Banca e) {
        if (e == null) return null;
        return BancaResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .sigla(e.getSigla())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }

    public CargoResponse toResponse(Cargo e) {
        if (e == null) return null;
        return CargoResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .orgaoId(e.getOrgao() != null ? e.getOrgao().getId() : null)
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }

    public TipoResponse toResponse(Tipo e) {
        if (e == null) return null;
        return TipoResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }

    public QuestaoResponse toResponse(Questao e) {
        if (e == null) return null;
        return QuestaoResponse.builder()
                .id(e.getId())
                .enunciado(e.getEnunciado())
                .alternativaA(e.getAlternativaA())
                .alternativaB(e.getAlternativaB())
                .alternativaC(e.getAlternativaC())
                .alternativaD(e.getAlternativaD())
                .alternativaE(e.getAlternativaE())
                .respostaCorreta(e.getRespostaCorreta())
                .tipo(toResponse(e.getTipo()))
                .materia(toResponse(e.getMateria()))
                .assunto(toResponse(e.getAssunto()))
                .topico(toResponse(e.getTopico()))
                .orgao(toResponse(e.getOrgao()))
                .banca(toResponse(e.getBanca()))
                .cargo(toResponse(e.getCargo()))
                .ano(e.getAno())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }

    public SimuladoQuestaoResponse toResponse(SimuladoQuestao e) {
        if (e == null) return null;
        Long questaoId = e.getQuestao() != null ? e.getQuestao().getId() : null;
        return SimuladoQuestaoResponse.builder()
                .id(e.getId())
                .questaoId(questaoId)
                .ordem(e.getOrdem())
                .build();
    }

    public SimuladoResponse toResponse(Simulado e) {
        if (e == null) return null;
        List<SimuladoQuestaoResponse> questoes = e.getSimuladosQuestoes() != null
                ? e.getSimuladosQuestoes().stream().map(this::toResponse).collect(Collectors.toList())
                : Collections.emptyList();
        return SimuladoResponse.builder()
                .id(e.getId())
                .titulo(e.getTitulo())
                .cargoId(e.getCargo() != null ? e.getCargo().getId() : null)
                .orgaoId(e.getOrgao() != null ? e.getOrgao().getId() : null)
                .ano(e.getAno())
                .dataCriacao(e.getDataCriacao())
                .ordemMaterias(e.getOrdemMaterias())
                .simuladosQuestoes(questoes)
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }
}
