package br.com.simula.controller;

import br.com.simula.dto.BancaRequest;
import br.com.simula.dto.BancaResponse;
import br.com.simula.entity.Banca;
import br.com.simula.mapper.SimulaMapper;
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
    private final SimulaMapper mapper;

    @GetMapping
    public Page<BancaResponse> listar(@PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(pageable).map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public BancaResponse detalhes(@PathVariable Long id) {
        return mapper.toResponse(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BancaResponse criar(@Valid @RequestBody BancaRequest request) {
        Banca banca = new Banca();
        banca.setNome(request.getNome());
        banca.setSigla(request.getSigla());
        return mapper.toResponse(service.create(banca));
    }

    @PutMapping("/{id}")
    public BancaResponse atualizar(@PathVariable Long id, @Valid @RequestBody BancaRequest request) {
        Banca banca = new Banca();
        banca.setNome(request.getNome());
        banca.setSigla(request.getSigla());
        return mapper.toResponse(service.update(id, banca));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deleteById(id);
    }
}
