package br.com.simula.service;

import br.com.simula.entity.Orgao;
import br.com.simula.repository.OrgaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrgaoService {

    private final OrgaoRepository repository;

    @Transactional(readOnly = true)
    public Page<Orgao> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Orgao findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Órgão não encontrado com id: " + id));
    }

    @Transactional
    public Orgao create(Orgao orgao) {
        return repository.save(orgao);
    }

    @Transactional
    public Orgao update(Long id, Orgao orgaoAtualizado) {
        Orgao existente = findById(id);
        existente.setNome(orgaoAtualizado.getNome());
        existente.setSigla(orgaoAtualizado.getSigla());
        return repository.save(existente);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Órgão não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
