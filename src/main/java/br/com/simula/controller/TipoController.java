package br.com.simula.controller;

import br.com.simula.dto.TipoRequest;
import br.com.simula.dto.TipoResponse;
import br.com.simula.entity.Tipo;
import br.com.simula.mapper.SimulaMapper;
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
    private final SimulaMapper mapper;

    @GetMapping
    public Page<TipoResponse> listar(@PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(pageable).map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public TipoResponse detalhes(@PathVariable Long id) {
        return mapper.toResponse(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoResponse criar(@Valid @RequestBody TipoRequest request) {
        Tipo tipo = new Tipo();
        tipo.setNome(request.getNome());
        return mapper.toResponse(service.create(tipo));
    }

    @PutMapping("/{id}")
    public TipoResponse atualizar(@PathVariable Long id, @Valid @RequestBody TipoRequest request) {
        Tipo tipo = new Tipo();
        tipo.setNome(request.getNome());
        return mapper.toResponse(service.update(id, tipo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deleteById(id);
    }
}
