package br.com.simula.repository;

import br.com.simula.entity.Assunto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuntoRepository extends JpaRepository<Assunto, Long> {

    @Override
    @EntityGraph(attributePaths = {"materia"})
    Page<Assunto> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"materia"})
    Page<Assunto> findByMateriaId(Long materiaId, Pageable pageable);

    @EntityGraph(attributePaths = {"materia"})
    java.util.Optional<Assunto> findWithMateriaById(Long id);
}
