package br.com.simula.controller;

import br.com.simula.dto.CargoRequest;
import br.com.simula.dto.CargoResponse;
import br.com.simula.entity.Cargo;
import br.com.simula.entity.Orgao;
import br.com.simula.mapper.SimulaMapper;
import br.com.simula.service.CargoService;
import br.com.simula.service.OrgaoService;
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
    private final OrgaoService orgaoService;
    private final SimulaMapper mapper;

    @GetMapping
    public Page<CargoResponse> listar(@RequestParam(required = false) Long orgaoId,
                                      @PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(orgaoId, pageable).map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public CargoResponse detalhes(@PathVariable Long id) {
        return mapper.toResponse(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CargoResponse criar(@Valid @RequestBody CargoRequest request) {
        Cargo cargo = new Cargo();
        cargo.setNome(request.getNome());
        Orgao orgao = orgaoService.findById(request.getOrgaoId());
        cargo.setOrgao(orgao);
        return mapper.toResponse(service.create(cargo));
    }

    @PutMapping("/{id}")
    public CargoResponse atualizar(@PathVariable Long id, @Valid @RequestBody CargoRequest request) {
        Cargo cargo = new Cargo();
        cargo.setNome(request.getNome());
        cargo.setOrgao(orgaoService.findById(request.getOrgaoId()));
        return mapper.toResponse(service.update(id, cargo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deleteById(id);
    }
}
