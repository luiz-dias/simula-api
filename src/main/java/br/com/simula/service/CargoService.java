package br.com.simula.service;

import br.com.simula.entity.Cargo;
import br.com.simula.repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CargoService {

    private final CargoRepository repository;
    private final OrgaoService orgaoService;

    @Transactional(readOnly = true)
    public Page<Cargo> findAll(Long orgaoId, Pageable pageable) {
        if (orgaoId != null) {
            return repository.findByOrgaoId(orgaoId, pageable);
        }
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Cargo findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cargo não encontrado com id: " + id));
    }

    @Transactional
    public Cargo create(Cargo cargo) {
        if (cargo.getOrgao() != null && cargo.getOrgao().getId() != null) {
            cargo.setOrgao(orgaoService.findById(cargo.getOrgao().getId()));
        }
        return repository.save(cargo);
    }

    @Transactional
    public Cargo update(Long id, Cargo cargoAtualizado) {
        Cargo existente = findById(id);
        existente.setNome(cargoAtualizado.getNome());
        if (cargoAtualizado.getOrgao() != null && cargoAtualizado.getOrgao().getId() != null) {
            existente.setOrgao(orgaoService.findById(cargoAtualizado.getOrgao().getId()));
        }
        return repository.save(existente);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cargo não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
