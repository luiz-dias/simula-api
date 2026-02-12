package br.com.simula.service;

import br.com.simula.entity.Questao;
import br.com.simula.entity.Simulado;
import br.com.simula.entity.SimuladoQuestao;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SimuladoDocxService {

    public byte[] gerarDocx(Simulado simulado) throws IOException {
        try (XWPFDocument doc = new XWPFDocument()) {
            // Título
            XWPFParagraph tituloPara = doc.createParagraph();
            tituloPara.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun tituloRun = tituloPara.createRun();
            tituloRun.setText(simulado.getTitulo());
            tituloRun.setBold(true);
            tituloRun.setFontSize(18);
            tituloPara.createRun().addBreak();

            if (simulado.getOrgao() != null || simulado.getCargo() != null || simulado.getAno() != null) {
                XWPFParagraph info = doc.createParagraph();
                info.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun infoRun = info.createRun();
                StringBuilder sb = new StringBuilder();
                if (simulado.getOrgao() != null) sb.append(simulado.getOrgao().getNome()).append(" ");
                if (simulado.getCargo() != null) sb.append("– ").append(simulado.getCargo().getNome()).append(" ");
                if (simulado.getAno() != null) sb.append("– ").append(simulado.getAno());
                infoRun.setText(sb.toString().trim());
                info.createRun().addBreak();
            }

            List<SimuladoQuestao> itens = simulado.getSimuladosQuestoes();
            if (itens == null || itens.isEmpty()) {
                XWPFParagraph p = doc.createParagraph();
                p.createRun().setText("Nenhuma questão incluída neste simulado.");
                return toByteArray(doc);
            }

            int numero = 1;
            for (SimuladoQuestao sq : itens) {
                Questao q = sq.getQuestao();
                if (q == null) continue;

                // Número da questão
                XWPFParagraph numPara = doc.createParagraph();
                numPara.setSpacingBefore(200);
                XWPFRun numRun = numPara.createRun();
                numRun.setText(numero + ". ");
                numRun.setBold(true);
                numRun.setFontSize(12);

                // Enunciado
                XWPFParagraph enuncPara = doc.createParagraph();
                enuncPara.setWordWrapped(true);
                XWPFRun enuncRun = enuncPara.createRun();
                enuncRun.setText(q.getEnunciado() != null ? q.getEnunciado() : "");
                enuncRun.setFontSize(11);
                enuncPara.createRun().addBreak();

                // Alternativas
                appendAlternativa(enuncPara, "A)", q.getAlternativaA());
                appendAlternativa(enuncPara, "B)", q.getAlternativaB());
                appendAlternativa(enuncPara, "C)", q.getAlternativaC());
                appendAlternativa(enuncPara, "D)", q.getAlternativaD());
                appendAlternativa(enuncPara, "E)", q.getAlternativaE());

                numero++;
            }

            return toByteArray(doc);
        }
    }

    private void appendAlternativa(XWPFParagraph para, String letra, String texto) {
        if (texto == null || texto.isBlank()) return;
        XWPFRun r = para.createRun();
        r.setText(letra + " " + texto.trim());
        r.setFontSize(11);
        para.createRun().addBreak();
    }

    private byte[] toByteArray(XWPFDocument doc) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        doc.write(out);
        return out.toByteArray();
    }
}
