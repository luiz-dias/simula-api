package br.com.simula.controller;

import br.com.simula.entity.Simulado;
import br.com.simula.service.SimuladoDocxService;
import br.com.simula.service.SimuladoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/simulados")
@RequiredArgsConstructor
public class SimuladoController {

    private final SimuladoService service;
    private final SimuladoDocxService docxService;

    @GetMapping
    public Page<Simulado> listar(@RequestParam(required = false) Long cargoId,
                                 @RequestParam(required = false) Long orgaoId,
                                 @RequestParam(required = false) Integer ano,
                                 @PageableDefault(size = 20) Pageable pageable) {
        return service.findAll(cargoId, orgaoId, ano, pageable);
    }

    @GetMapping("/{id}")
    public Simulado detalhes(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Simulado criar(@Valid @RequestBody Simulado simulado) {
        return service.create(simulado);
    }

    @PutMapping("/{id}")
    public Simulado atualizar(@PathVariable Long id, @Valid @RequestBody Simulado simulado) {
        return service.update(id, simulado);
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
}
