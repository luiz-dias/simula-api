package br.com.simula.controller;

import br.com.simula.entity.Topico;
import br.com.simula.service.TopicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoService service;

    @GetMapping
    public Page<Topico> listar(@RequestParam(required = false) Long assuntoId,
                               @PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(assuntoId, pageable);
    }

    @GetMapping("/{id}")
    public Topico detalhes(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Topico criar(@Valid @RequestBody Topico topico) {
        return service.create(topico);
    }

    @PutMapping("/{id}")
    public Topico atualizar(@PathVariable Long id, @Valid @RequestBody Topico topico) {
        return service.update(id, topico);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deleteById(id);
    }
}
