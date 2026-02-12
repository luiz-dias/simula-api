package br.com.simula.repository;

import br.com.simula.entity.Simulado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SimuladoRepository extends JpaRepository<Simulado, Long> {

    Page<Simulado> findByCargoId(Long cargoId, Pageable pageable);
    Page<Simulado> findByOrgaoId(Long orgaoId, Pageable pageable);
    Page<Simulado> findByAno(Integer ano, Pageable pageable);

    @Query("SELECT DISTINCT s FROM Simulado s " +
           "LEFT JOIN FETCH s.cargo " +
           "LEFT JOIN FETCH s.orgao " +
           "LEFT JOIN FETCH s.simuladosQuestoes sq " +
           "LEFT JOIN FETCH sq.questao q " +
           "LEFT JOIN FETCH q.materia " +
           "WHERE s.id = :id")
    java.util.Optional<Simulado> findByIdWithQuestoes(@Param("id") Long id);
}
