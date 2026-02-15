package br.com.simula.controller;

import br.com.simula.dto.MateriaRequest;
import br.com.simula.dto.MateriaResponse;
import br.com.simula.entity.Materia;
import br.com.simula.mapper.SimulaMapper;
import br.com.simula.service.MateriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/materias")
@RequiredArgsConstructor
public class MateriaController {

    private final MateriaService service;
    private final SimulaMapper mapper;

    @GetMapping
    public Page<MateriaResponse> listar(@PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(pageable).map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public MateriaResponse detalhes(@PathVariable Long id) {
        return mapper.toResponse(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MateriaResponse criar(@Valid @RequestBody MateriaRequest request) {
        Materia materia = new Materia();
        materia.setNome(request.getNome());
        return mapper.toResponse(service.create(materia));
    }

    @PutMapping("/{id}")
    public MateriaResponse atualizar(@PathVariable Long id, @Valid @RequestBody MateriaRequest request) {
        Materia materia = new Materia();
        materia.setNome(request.getNome());
        return mapper.toResponse(service.update(id, materia));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id,
                        @RequestParam(name = "cascade", defaultValue = "false") boolean cascade) {
        service.deleteById(id, cascade);
    }
}
