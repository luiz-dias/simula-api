package br.com.simula.service;

import br.com.simula.entity.Materia;
import br.com.simula.repository.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MateriaService {

    private final MateriaRepository repository;

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
        existente.setDescricao(materiaAtualizada.getDescricao());
        return repository.save(existente);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Matéria não encontrada com id: " + id);
        }
        repository.deleteById(id);
    }
}
