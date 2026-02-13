package br.com.simula.controller;

import br.com.simula.dto.OrgaoRequest;
import br.com.simula.dto.OrgaoResponse;
import br.com.simula.entity.Orgao;
import br.com.simula.mapper.SimulaMapper;
import br.com.simula.service.OrgaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orgaos")
@RequiredArgsConstructor
public class OrgaoController {

    private final OrgaoService service;
    private final SimulaMapper mapper;

    @GetMapping
    public Page<OrgaoResponse> listar(@PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(pageable).map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public OrgaoResponse detalhes(@PathVariable Long id) {
        return mapper.toResponse(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrgaoResponse criar(@Valid @RequestBody OrgaoRequest request) {
        Orgao orgao = new Orgao();
        orgao.setNome(request.getNome());
        orgao.setSigla(request.getSigla());
        return mapper.toResponse(service.create(orgao));
    }

    @PutMapping("/{id}")
    public OrgaoResponse atualizar(@PathVariable Long id, @Valid @RequestBody OrgaoRequest request) {
        Orgao orgao = new Orgao();
        orgao.setNome(request.getNome());
        orgao.setSigla(request.getSigla());
        return mapper.toResponse(service.update(id, orgao));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deleteById(id);
    }
}
