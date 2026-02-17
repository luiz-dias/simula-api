package br.com.simula.controller;

import br.com.simula.dto.*;
import br.com.simula.entity.Simulado;
import br.com.simula.entity.SimuladoQuestao;
import br.com.simula.mapper.SimulaMapper;
import br.com.simula.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/simulados")
@RequiredArgsConstructor
public class SimuladoController {

    private final SimuladoService service;
    private final GerarSimuladoService gerarSimuladoService;
    private final SimuladoDocxService docxService;
    private final CargoService cargoService;
    private final OrgaoService orgaoService;
    private final QuestaoService questaoService;
    private final SimulaMapper mapper;

    @GetMapping
    public Page<SimuladoResponse> listar(@RequestParam(required = false) Long cargoId,
                                          @RequestParam(required = false) Long orgaoId,
                                          @RequestParam(required = false) Integer ano,
                                          @PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(cargoId, orgaoId, ano, pageable).map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public SimuladoResponse detalhes(@PathVariable Long id) {
        return mapper.toResponse(service.findByIdCompleto(id));
    }

    @PostMapping("/gerar")
    @ResponseStatus(HttpStatus.CREATED)
    public SimuladoResponse gerar(@Valid @RequestBody GerarSimuladoRequest request) {
        Simulado simulado = gerarSimuladoService.gerar(request);
        return mapper.toResponse(simulado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SimuladoResponse criar(@Valid @RequestBody SimuladoRequest request) {
        Simulado simulado = toEntity(request);
        return mapper.toResponse(service.create(simulado));
    }

    @PutMapping("/{id}")
    public SimuladoResponse atualizar(@PathVariable Long id, @Valid @RequestBody SimuladoRequest request) {
        Simulado simulado = toEntity(request);
        return mapper.toResponse(service.update(id, simulado));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping(value = "/{id}/gerar-docx", produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public ResponseEntity<byte[]> gerarDocx(@PathVariable Long id) {
        Simulado simulado = service.findById(id);
        try {
            byte[] docx = docxService.gerarDocx(simulado);
            String nomeArquivo = (simulado.getTitulo() != null ? simulado.getTitulo() : "simulado")
                    .replaceAll("[^a-zA-Z0-9\\s-]", "") + ".docx";
            String encoded = URLEncoder.encode(nomeArquivo, StandardCharsets.UTF_8).replace("+", "%20");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
            headers.setContentDispositionFormData("attachment", nomeArquivo);
            headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Content-Disposition");
            return ResponseEntity.ok().headers(headers).body(docx);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar documento: " + e.getMessage());
        }
    }

    private Simulado toEntity(SimuladoRequest req) {
        Simulado s = new Simulado();
        s.setTitulo(req.getTitulo());
        s.setCargo(req.getCargoId() != null ? cargoService.findById(req.getCargoId()) : null);
        s.setOrgao(req.getOrgaoId() != null ? orgaoService.findById(req.getOrgaoId()) : null);
        s.setAno(req.getAno());
        s.setDataCriacao(req.getDataCriacao());
        s.setOrdemMaterias(req.getOrdemMaterias());
        if (req.getSimuladosQuestoes() != null && !req.getSimuladosQuestoes().isEmpty()) {
            List<SimuladoQuestao> list = new ArrayList<>();
            for (SimuladoQuestaoRequest sqReq : req.getSimuladosQuestoes()) {
                SimuladoQuestao sq = new SimuladoQuestao();
                sq.setQuestao(questaoService.findById(sqReq.getQuestaoId()));
                sq.setOrdem(sqReq.getOrdem());
                list.add(sq);
            }
            s.setSimuladosQuestoes(list);
        }
        return s;
    }
}
