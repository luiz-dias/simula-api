package br.com.simula.service;

import br.com.simula.entity.Topico;
import br.com.simula.repository.TopicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TopicoService {

    private final TopicoRepository repository;
    private final AssuntoService assuntoService;

    @Transactional(readOnly = true)
    public Page<Topico> findAll(Long assuntoId, Pageable pageable) {
        if (assuntoId != null) {
            return repository.findByAssuntoId(assuntoId, pageable);
        }
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Topico findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado com id: " + id));
    }

    @Transactional
    public Topico create(Topico topico) {
        if (topico.getAssunto() != null && topico.getAssunto().getId() != null) {
            topico.setAssunto(assuntoService.findById(topico.getAssunto().getId()));
        }
        return repository.save(topico);
    }

    @Transactional
    public Topico update(Long id, Topico topicoAtualizado) {
        Topico existente = findById(id);
        existente.setNome(topicoAtualizado.getNome());
        if (topicoAtualizado.getAssunto() != null && topicoAtualizado.getAssunto().getId() != null) {
            existente.setAssunto(assuntoService.findById(topicoAtualizado.getAssunto().getId()));
        }
        return repository.save(existente);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Tópico não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}   