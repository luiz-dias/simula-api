package br.com.simula.service;

import br.com.simula.entity.Questao;
import br.com.simula.repository.QuestaoRepository;
import br.com.simula.spec.QuestaoSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestaoService {

    private final QuestaoRepository repository;
    private final TipoService tipoService;
    private final MateriaService materiaService;
    private final AssuntoService assuntoService;
    private final TopicoService topicoService;
    private final OrgaoService orgaoService;
    private final BancaService bancaService;
    private final CargoService cargoService;

    @Transactional(readOnly = true)
    public Page<Questao> findAll(Long tipoId, Long materiaId, Long assuntoId, Long topicoId,
                                 Long orgaoId, Long bancaId, Long cargoId, Integer ano,
                                 Pageable pageable) {
        Specification<Questao> spec = QuestaoSpec.withFilters(tipoId, materiaId, assuntoId, topicoId, orgaoId, bancaId, cargoId, ano);
        return repository.findAll(spec, pageable);
    }

    @Transactional(readOnly = true)
    public Questao findById(Long id) {
        return repository.findByIdWithAssociations(id)
                .orElseThrow(() -> new RuntimeException("Questão não encontrada com id: " + id));
    }

    @Transactional
    public Questao create(Questao questao) {
        resolveAssociations(questao);
        return repository.save(questao);
    }

    @Transactional
    public Questao update(Long id, Questao questaoAtualizada) {
        Questao existente = findById(id);
        existente.setEnunciado(questaoAtualizada.getEnunciado());
        existente.setAlternativaA(questaoAtualizada.getAlternativaA());
        existente.setAlternativaB(questaoAtualizada.getAlternativaB());
        existente.setAlternativaC(questaoAtualizada.getAlternativaC());
        existente.setAlternativaD(questaoAtualizada.getAlternativaD());
        existente.setAlternativaE(questaoAtualizada.getAlternativaE());
        existente.setRespostaCorreta(questaoAtualizada.getRespostaCorreta());
        existente.setAno(questaoAtualizada.getAno());
        resolveAssociations(existente, questaoAtualizada);
        return repository.save(existente);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Questão não encontrada com id: " + id);
        }
        repository.deleteById(id);
    }

    private void resolveAssociations(Questao questao) {
        resolveAssociations(questao, questao);
    }

    private void resolveAssociations(Questao target, Questao source) {
        if (source.getTipo() != null && source.getTipo().getId() != null) {
            target.setTipo(tipoService.findById(source.getTipo().getId()));
        } else {
            target.setTipo(null);
        }
        if (source.getMateria() != null && source.getMateria().getId() != null) {
            target.setMateria(materiaService.findById(source.getMateria().getId()));
        }
        if (source.getAssunto() != null && source.getAssunto().getId() != null) {
            target.setAssunto(assuntoService.findById(source.getAssunto().getId()));
        } else {
            target.setAssunto(null);
        }
        if (source.getTopico() != null && source.getTopico().getId() != null) {
            target.setTopico(topicoService.findById(source.getTopico().getId()));
        } else {
            target.setTopico(null);
        }
        if (source.getOrgao() != null && source.getOrgao().getId() != null) {
            target.setOrgao(orgaoService.findById(source.getOrgao().getId()));
        } else {
            target.setOrgao(null);
        }
        if (source.getBanca() != null && source.getBanca().getId() != null) {
            target.setBanca(bancaService.findById(source.getBanca().getId()));
        } else {
            target.setBanca(null);
        }
        if (source.getCargo() != null && source.getCargo().getId() != null) {
            target.setCargo(cargoService.findById(source.getCargo().getId()));
        } else {
            target.setCargo(null);
        }
    }
}
