package br.com.simula.controller;

import br.com.simula.entity.Cargo;
import br.com.simula.service.CargoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cargos")
@RequiredArgsConstructor
public class CargoController {

    private final CargoService service;

    @GetMapping
    public Page<Cargo> listar(@RequestParam(required = false) Long orgaoId,
                              @PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(orgaoId, pageable);
    }

    @GetMapping("/{id}")
    public Cargo detalhes(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cargo criar(@Valid @RequestBody Cargo cargo) {
        return service.create(cargo);
    }

    @PutMapping("/{id}")
    public Cargo atualizar(@PathVariable Long id, @Valid @RequestBody Cargo cargo) {
        return service.update(id, cargo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deleteById(id);
    }
}
