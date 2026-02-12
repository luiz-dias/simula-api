package br.com.simula.controller;

import br.com.simula.entity.Orgao;
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

    @GetMapping
    public Page<Orgao> listar(@PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Orgao detalhes(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Orgao criar(@Valid @RequestBody Orgao orgao) {
        return service.create(orgao);
    }

    @PutMapping("/{id}")
    public Orgao atualizar(@PathVariable Long id, @Valid @RequestBody Orgao orgao) {
        return service.update(id, orgao);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deleteById(id);
    }
}
