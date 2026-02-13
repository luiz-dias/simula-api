package br.com.simula.controller;

import br.com.simula.entity.*;
import br.com.simula.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/export")
@RequiredArgsConstructor
public class ExportController {

    private final QuestaoRepository questaoRepository;
    private final MateriaRepository materiaRepository;
    private final AssuntoRepository assuntoRepository;
    private final TopicoRepository topicoRepository;
    private final OrgaoRepository orgaoRepository;
    private final BancaRepository bancaRepository;
    private final CargoRepository cargoRepository;
    private final SimuladoRepository simuladoRepository;
    private final TipoRepository tipoRepository;

    @GetMapping(value = "/csv", produces = "text/csv; charset=UTF-8")
    public ResponseEntity<byte[]> exportCsv() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(baos, StandardCharsets.UTF_8));

        // BOM para Excel reconhecer UTF-8
        pw.print('\uFEFF');

        exportMaterias(pw);
        exportAssuntos(pw);
        exportTopicos(pw);
        exportOrgaos(pw);
        exportBancas(pw);
        exportCargos(pw);
        exportTipos(pw);
        exportQuestoes(pw);
        exportSimulados(pw);

        pw.flush();
        byte[] bytes = baos.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv; charset=UTF-8"));
        headers.setContentDispositionFormData("attachment", "export_simulados.csv");
        return ResponseEntity.ok().headers(headers).body(bytes);
    }

    private static void csvRow(PrintWriter pw, Object... values) {
        pw.println(Stream.of(values)
                .map(v -> v == null ? "" : "\"" + String.valueOf(v).replace("\"", "\"\"") + "\"")
                .reduce((a, b) -> a + ";" + b).orElse(""));
    }

    private void exportMaterias(PrintWriter pw) {
        pw.println("=== MATÉRIAS ===");
        csvRow(pw, "id", "nome", "descricao", "created_at", "updated_at");
        for (Materia m : materiaRepository.findAll()) {
            csvRow(pw, m.getId(), m.getNome(), m.getDescricao(), m.getCreatedAt(), m.getUpdatedAt());
        }
        pw.println();
    }

    private void exportAssuntos(PrintWriter pw) {
        pw.println("=== ASSUNTOS ===");
        csvRow(pw, "id", "nome", "materia_id", "created_at", "updated_at");
        for (Assunto a : assuntoRepository.findAll()) {
            csvRow(pw, a.getId(), a.getNome(), a.getMateria() != null ? a.getMateria().getId() : null, a.getCreatedAt(), a.getUpdatedAt());
        }
        pw.println();
    }

    private void exportTopicos(PrintWriter pw) {
        pw.println("=== TÓPICOS ===");
        csvRow(pw, "id", "nome", "assunto_id", "created_at", "updated_at");
        for (Topico t : topicoRepository.findAll()) {
            csvRow(pw, t.getId(), t.getNome(), t.getAssunto() != null ? t.getAssunto().getId() : null, t.getCreatedAt(), t.getUpdatedAt());
        }
        pw.println();
    }

    private void exportOrgaos(PrintWriter pw) {
        pw.println("=== ÓRGÃOS ===");
        csvRow(pw, "id", "nome", "sigla", "created_at", "updated_at");
        for (Orgao o : orgaoRepository.findAll()) {
            csvRow(pw, o.getId(), o.getNome(), o.getSigla(), o.getCreatedAt(), o.getUpdatedAt());
        }
        pw.println();
    }

    private void exportBancas(PrintWriter pw) {
        pw.println("=== BANCAS ===");
        csvRow(pw, "id", "nome", "sigla", "created_at", "updated_at");
        for (Banca b : bancaRepository.findAll()) {
            csvRow(pw, b.getId(), b.getNome(), b.getSigla(), b.getCreatedAt(), b.getUpdatedAt());
        }
        pw.println();
    }

    private void exportCargos(PrintWriter pw) {
        pw.println("=== CARGOS ===");
        csvRow(pw, "id", "nome", "orgao_id", "created_at", "updated_at");
        for (Cargo c : cargoRepository.findAll()) {
            csvRow(pw, c.getId(), c.getNome(), c.getOrgao() != null ? c.getOrgao().getId() : null, c.getCreatedAt(), c.getUpdatedAt());
        }
        pw.println();
    }

    private void exportTipos(PrintWriter pw) {
        pw.println("=== TIPOS ===");
        csvRow(pw, "id", "nome", "created_at", "updated_at");
        for (Tipo t : tipoRepository.findAll()) {
            csvRow(pw, t.getId(), t.getNome(), t.getCreatedAt(), t.getUpdatedAt());
        }
        pw.println();
    }

    private void exportQuestoes(PrintWriter pw) {
        pw.println("=== QUESTÕES ===");
        csvRow(pw, "id", "enunciado", "alternativa_a", "alternativa_b", "alternativa_c", "alternativa_d", "alternativa_e",
                "resposta_correta", "tipo_id", "materia_id", "assunto_id", "topico_id", "orgao_id", "banca_id", "cargo_id", "ano", "created_at", "updated_at");
        for (Questao q : questaoRepository.findAll()) {
            csvRow(pw, q.getId(), q.getEnunciado(), q.getAlternativaA(), q.getAlternativaB(), q.getAlternativaC(),
                    q.getAlternativaD(), q.getAlternativaE(), q.getRespostaCorreta(),
                    q.getTipo() != null ? q.getTipo().getId() : null,
                    q.getMateria() != null ? q.getMateria().getId() : null,
                    q.getAssunto() != null ? q.getAssunto().getId() : null,
                    q.getTopico() != null ? q.getTopico().getId() : null,
                    q.getOrgao() != null ? q.getOrgao().getId() : null,
                    q.getBanca() != null ? q.getBanca().getId() : null,
                    q.getCargo() != null ? q.getCargo().getId() : null,
                    q.getAno(), q.getCreatedAt(), q.getUpdatedAt());
        }
        pw.println();
    }

    private void exportSimulados(PrintWriter pw) {
        pw.println("=== SIMULADOS ===");
        csvRow(pw, "id", "titulo", "cargo_id", "orgao_id", "ano", "data_criacao", "ordem_materias", "created_at", "updated_at");
        for (Simulado s : simuladoRepository.findAll()) {
            String ordem = s.getOrdemMaterias() != null ? s.getOrdemMaterias().toString() : "";
            csvRow(pw, s.getId(), s.getTitulo(),
                    s.getCargo() != null ? s.getCargo().getId() : null,
                    s.getOrgao() != null ? s.getOrgao().getId() : null,
                    s.getAno(), s.getDataCriacao(), ordem, s.getCreatedAt(), s.getUpdatedAt());
        }
    }
}
