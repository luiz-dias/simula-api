package br.com.simula.controller;

import br.com.simula.dto.QuestaoRequest;
import br.com.simula.dto.QuestaoResponse;
import br.com.simula.entity.*;
import br.com.simula.mapper.SimulaMapper;
import br.com.simula.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questoes")
@RequiredArgsConstructor
public class QuestaoController {

    private final QuestaoService service;
    private final TipoService tipoService;
    private final MateriaService materiaService;
    private final AssuntoService assuntoService;
    private final TopicoService topicoService;
    private final OrgaoService orgaoService;
    private final BancaService bancaService;
    private final CargoService cargoService;
    private final SimulaMapper mapper;

    @GetMapping
    public Page<QuestaoResponse> listar(@RequestParam(required = false) Long tipoId,
                                        @RequestParam(required = false) Long materiaId,
                                        @RequestParam(required = false) Long assuntoId,
                                        @RequestParam(required = false) Long topicoId,
                                        @RequestParam(required = false) Long orgaoId,
                                        @RequestParam(required = false) Long bancaId,
                                        @RequestParam(required = false) Long cargoId,
                                        @RequestParam(required = false) Integer ano,
                                        @PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(tipoId, materiaId, assuntoId, topicoId, orgaoId, bancaId, cargoId, ano, pageable)
                .map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public QuestaoResponse detalhes(@PathVariable Long id) {
        return mapper.toResponse(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestaoResponse criar(@Valid @RequestBody QuestaoRequest request) {
        Questao questao = toEntity(request);
        return mapper.toResponse(service.create(questao));
    }

    @PutMapping("/{id}")
    public QuestaoResponse atualizar(@PathVariable Long id, @Valid @RequestBody QuestaoRequest request) {
        Questao questao = toEntity(request);
        return mapper.toResponse(service.update(id, questao));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deleteById(id);
    }

    private Questao toEntity(QuestaoRequest req) {
        Questao q = new Questao();
        q.setEnunciado(req.getEnunciado());
        q.setAlternativaA(req.getAlternativaA());
        q.setAlternativaB(req.getAlternativaB());
        q.setAlternativaC(req.getAlternativaC());
        q.setAlternativaD(req.getAlternativaD());
        q.setAlternativaE(req.getAlternativaE());
        q.setRespostaCorreta(req.getRespostaCorreta());
        q.setAno(req.getAno());
        q.setTipo(req.getTipoId() != null ? tipoService.findById(req.getTipoId()) : null);
        q.setMateria(materiaService.findById(req.getMateriaId()));
        q.setAssunto(req.getAssuntoId() != null ? assuntoService.findById(req.getAssuntoId()) : null);
        q.setTopico(req.getTopicoId() != null ? topicoService.findById(req.getTopicoId()) : null);
        q.setOrgao(req.getOrgaoId() != null ? orgaoService.findById(req.getOrgaoId()) : null);
        q.setBanca(req.getBancaId() != null ? bancaService.findById(req.getBancaId()) : null);
        q.setCargo(req.getCargoId() != null ? cargoService.findById(req.getCargoId()) : null);
        return q;
    }
}
