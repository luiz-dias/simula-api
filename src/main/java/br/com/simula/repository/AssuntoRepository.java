package br.com.simula.repository;

import br.com.simula.entity.Assunto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuntoRepository extends JpaRepository<Assunto, Long> {

    Page<Assunto> findByMateriaId(Long materiaId, Pageable pageable);
}
