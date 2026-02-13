package br.com.simula.service;

import br.com.simula.entity.Assunto;
import br.com.simula.repository.AssuntoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AssuntoService {

    private final AssuntoRepository repository;
    private final MateriaService materiaService;

    @Transactional(readOnly = true)
    public Page<Assunto> findAll(Long materiaId, Pageable pageable) {
        if (materiaId != null) {
            return repository.findByMateriaId(materiaId, pageable);
        }
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Assunto findById(Long id) {
        return repository.findWithMateriaById(id)
                .orElseThrow(() -> new RuntimeException("Assunto não encontrado com id: " + id));
    }

    @Transactional
    public Assunto create(Assunto assunto) {
        if (assunto.getMateria() != null && assunto.getMateria().getId() != null) {
            assunto.setMateria(materiaService.findById(assunto.getMateria().getId()));
        }
        return repository.save(assunto);
    }

    @Transactional
    public Assunto update(Long id, Assunto assuntoAtualizado) {
        Assunto existente = findById(id);
        existente.setNome(assuntoAtualizado.getNome());
        if (assuntoAtualizado.getMateria() != null && assuntoAtualizado.getMateria().getId() != null) {
            existente.setMateria(materiaService.findById(assuntoAtualizado.getMateria().getId()));
        }
        return repository.save(existente);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Assunto não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
