package br.com.simula.controller;

import br.com.simula.entity.Tipo;
import br.com.simula.service.TipoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tipos")
@RequiredArgsConstructor
public class TipoController {

    private final TipoService service;

    @GetMapping
    public Page<Tipo> listar(@PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Tipo detalhes(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tipo criar(@Valid @RequestBody Tipo tipo) {
        return service.create(tipo);
    }

    @PutMapping("/{id}")
    public Tipo atualizar(@PathVariable Long id, @Valid @RequestBody Tipo tipo) {
        return service.update(id, tipo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deleteById(id);
    }
}
