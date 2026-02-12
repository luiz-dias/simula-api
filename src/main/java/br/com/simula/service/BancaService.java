package br.com.simula.service;

import br.com.simula.entity.Banca;
import br.com.simula.repository.BancaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BancaService {

    private final BancaRepository repository;

    @Transactional(readOnly = true)
    public Page<Banca> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Banca findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banca não encontrada com id: " + id));
    }

    @Transactional
    public Banca create(Banca banca) {
        return repository.save(banca);
    }

    @Transactional
    public Banca update(Long id, Banca bancaAtualizada) {
        Banca existente = findById(id);
        existente.setNome(bancaAtualizada.getNome());
        existente.setSigla(bancaAtualizada.getSigla());
        return repository.save(existente);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Banca não encontrada com id: " + id);
        }
        repository.deleteById(id);
    }
}
