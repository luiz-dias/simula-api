package br.com.simula.controller;

import br.com.simula.dto.TopicoRequest;
import br.com.simula.dto.TopicoResponse;
import br.com.simula.entity.Assunto;
import br.com.simula.entity.Topico;
import br.com.simula.mapper.SimulaMapper;
import br.com.simula.service.AssuntoService;
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
    private final AssuntoService assuntoService;
    private final SimulaMapper mapper;

    @GetMapping
    public Page<TopicoResponse> listar(@RequestParam(required = false) Long assuntoId,
                                       @PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(assuntoId, pageable).map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public TopicoResponse detalhes(@PathVariable Long id) {
        return mapper.toResponse(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TopicoResponse criar(@Valid @RequestBody TopicoRequest request) {
        Topico topico = new Topico();
        topico.setNome(request.getNome());
        Assunto assunto = assuntoService.findById(request.getAssuntoId());
        topico.setAssunto(assunto);
        return mapper.toResponse(service.create(topico));
    }

    @PutMapping("/{id}")
    public TopicoResponse atualizar(@PathVariable Long id, @Valid @RequestBody TopicoRequest request) {
        Topico topico = new Topico();
        topico.setNome(request.getNome());
        topico.setAssunto(assuntoService.findById(request.getAssuntoId()));
        return mapper.toResponse(service.update(id, topico));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deleteById(id);
    }
}
