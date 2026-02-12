package br.com.simula.controller;

import br.com.simula.entity.Assunto;
import br.com.simula.service.AssuntoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assuntos")
@RequiredArgsConstructor
public class AssuntoController {

    private final AssuntoService service;

    @GetMapping
    public Page<Assunto> listar(@RequestParam(required = false) Long materiaId,
                                @PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(materiaId, pageable);
    }

    @GetMapping("/{id}")
    public Assunto detalhes(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Assunto criar(@Valid @RequestBody Assunto assunto) {
        return service.create(assunto);
    }

    @PutMapping("/{id}")
    public Assunto atualizar(@PathVariable Long id, @Valid @RequestBody Assunto assunto) {
        return service.update(id, assunto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deleteById(id);
    }
}
