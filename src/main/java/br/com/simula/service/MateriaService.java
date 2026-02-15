package br.com.simula.service;

import br.com.simula.entity.Materia;
import br.com.simula.exception.EntidadeComVinculosException;
import br.com.simula.repository.AssuntoRepository;
import br.com.simula.repository.MateriaRepository;
import br.com.simula.repository.QuestaoRepository;
import br.com.simula.repository.SimuladoQuestaoRepository;
import br.com.simula.repository.TopicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MateriaService {

    private final MateriaRepository repository;
    private final AssuntoRepository assuntoRepository;
    private final TopicoRepository topicoRepository;
    private final QuestaoRepository questaoRepository;
    private final SimuladoQuestaoRepository simuladoQuestaoRepository;

    @Transactional(readOnly = true)
    public Page<Materia> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Materia findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matéria não encontrada com id: " + id));
    }

    @Transactional
    public Materia create(Materia materia) {
        return repository.save(materia);
    }

    @Transactional
    public Materia update(Long id, Materia materiaAtualizada) {
        Materia existente = findById(id);
        existente.setNome(materiaAtualizada.getNome());
        return repository.save(existente);
    }

    @Transactional
    public void deleteById(Long id) {
        deleteById(id, false);
    }

    /**
     * Exclui a matéria. Se cascade=true, exclui em cascata: vínculos em simulados, questões,
     * tópicos e assuntos vinculados à matéria. Caso contrário, falha se houver assuntos vinculados.
     */
    @Transactional
    public void deleteById(Long id, boolean cascade) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Matéria não encontrada com id: " + id);
        }
        if (!cascade && assuntoRepository.existsByMateriaId(id)) {
            throw new EntidadeComVinculosException("Não é possível excluir a matéria pois existem assuntos vinculados a ela. Remova ou altere os assuntos antes de excluir.");
        }
        if (cascade) {
            List<Long> questaoIds = questaoRepository.findIdsByMateriaId(id);
            if (!questaoIds.isEmpty()) {
                simuladoQuestaoRepository.deleteByQuestaoIdIn(questaoIds);
            }
            questaoRepository.deleteByMateriaId(id);
            List<Long> assuntoIds = assuntoRepository.findByMateriaId(id, PageRequest.of(0, Integer.MAX_VALUE))
                    .getContent().stream().map(a -> a.getId()).collect(Collectors.toList());
            if (!assuntoIds.isEmpty()) {
                topicoRepository.deleteByAssuntoIdIn(assuntoIds);
            }
            assuntoRepository.deleteByMateriaId(id);
        }
        repository.deleteById(id);
    }
}
