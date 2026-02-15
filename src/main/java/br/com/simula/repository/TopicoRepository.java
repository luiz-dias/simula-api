package br.com.simula.repository;

import br.com.simula.entity.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findByAssuntoId(Long assuntoId, Pageable pageable);

    void deleteByAssuntoIdIn(java.util.List<Long> assuntoIds);
}