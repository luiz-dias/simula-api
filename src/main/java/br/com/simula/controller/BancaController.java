package br.com.simula.controller;

import br.com.simula.entity.Banca;
import br.com.simula.service.BancaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bancas")
@RequiredArgsConstructor
public class BancaController {

    private final BancaService service;

    @GetMapping
    public Page<Banca> listar(@PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Banca detalhes(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Banca criar(@Valid @RequestBody Banca banca) {
        return service.create(banca);
    }

    @PutMapping("/{id}")
    public Banca atualizar(@PathVariable Long id, @Valid @RequestBody Banca banca) {
        return service.update(id, banca);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deleteById(id);
    }
}
