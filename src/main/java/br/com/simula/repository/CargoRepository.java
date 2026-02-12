package br.com.simula.repository;

import br.com.simula.entity.Cargo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    Page<Cargo> findByOrgaoId(Long orgaoId, Pageable pageable);
}
