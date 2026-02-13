package br.com.simula.controller;

import br.com.simula.dto.AssuntoRequest;
import br.com.simula.dto.AssuntoResponse;
import br.com.simula.entity.Assunto;
import br.com.simula.entity.Materia;
import br.com.simula.mapper.SimulaMapper;
import br.com.simula.service.AssuntoService;
import br.com.simula.service.MateriaService;
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
    private final MateriaService materiaService;
    private final SimulaMapper mapper;

    @GetMapping
    public Page<AssuntoResponse> listar(@RequestParam(required = false) Long materiaId,
                                        @PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(materiaId, pageable).map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public AssuntoResponse detalhes(@PathVariable Long id) {
        return mapper.toResponse(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssuntoResponse criar(@Valid @RequestBody AssuntoRequest request) {
        Assunto assunto = new Assunto();
        assunto.setNome(request.getNome());
        Materia materia = materiaService.findById(request.getMateriaId());
        assunto.setMateria(materia);
        return mapper.toResponse(service.create(assunto));
    }

    @PutMapping("/{id}")
    public AssuntoResponse atualizar(@PathVariable Long id, @Valid @RequestBody AssuntoRequest request) {
        Assunto assunto = new Assunto();
        assunto.setNome(request.getNome());
        assunto.setMateria(materiaService.findById(request.getMateriaId()));
        return mapper.toResponse(service.update(id, assunto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deleteById(id);
    }
}
