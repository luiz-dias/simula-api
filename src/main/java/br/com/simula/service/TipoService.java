package br.com.simula.service;

import br.com.simula.entity.Tipo;
import br.com.simula.repository.TipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TipoService {

    private final TipoRepository repository;

    @Transactional(readOnly = true)
    public Page<Tipo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Tipo findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo não encontrado com id: " + id));
    }

    @Transactional
    public Tipo create(Tipo tipo) {
        return repository.save(tipo);
    }

    @Transactional
    public Tipo update(Long id, Tipo tipoAtualizado) {
        Tipo existente = findById(id);
        existente.setNome(tipoAtualizado.getNome());
        return repository.save(existente);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Tipo não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
