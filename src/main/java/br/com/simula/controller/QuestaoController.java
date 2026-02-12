package br.com.simula.controller;

import br.com.simula.entity.Questao;
import br.com.simula.service.QuestaoService;
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

    @GetMapping
    public Page<Questao> listar(@RequestParam(required = false) Long materiaId,
                               @RequestParam(required = false) Long assuntoId,
                               @RequestParam(required = false) Long topicoId,
                               @RequestParam(required = false) Long orgaoId,
                               @RequestParam(required = false) Long bancaId,
                               @RequestParam(required = false) Long cargoId,
                               @RequestParam(required = false) Integer ano,
                               @PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(materiaId, assuntoId, topicoId, orgaoId, bancaId, cargoId, ano, pageable);
    }

    @GetMapping("/{id}")
    public Questao detalhes(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Questao criar(@Valid @RequestBody Questao questao) {
        return service.create(questao);
    }

    @PutMapping("/{id}")
    public Questao atualizar(@PathVariable Long id, @Valid @RequestBody Questao questao) {
        return service.update(id, questao);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deleteById(id);
    }
}
