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

    @Query("SELECT DISTINCT s FROM Simulado s " +
           "LEFT JOIN FETCH s.cargo c LEFT JOIN FETCH c.orgao " +
           "LEFT JOIN FETCH s.orgao " +
           "LEFT JOIN FETCH s.configuracoes cfg " +
           "LEFT JOIN FETCH cfg.materia " +
           "LEFT JOIN FETCH cfg.assunto ca LEFT JOIN FETCH ca.materia " +
           "LEFT JOIN FETCH cfg.topico ct LEFT JOIN FETCH ct.assunto cta LEFT JOIN FETCH cta.materia " +
           "WHERE s.id = :id")
    java.util.Optional<Simulado> findByIdComConfiguracoes(@Param("id") Long id);

    @Query("SELECT DISTINCT s FROM Simulado s " +
           "LEFT JOIN FETCH s.simuladosQuestoes sq " +
           "LEFT JOIN FETCH sq.questao q " +
           "LEFT JOIN FETCH q.tipo " +
           "LEFT JOIN FETCH q.materia " +
           "LEFT JOIN FETCH q.assunto a LEFT JOIN FETCH a.materia " +
           "LEFT JOIN FETCH q.topico t LEFT JOIN FETCH t.assunto ta LEFT JOIN FETCH ta.materia " +
           "LEFT JOIN FETCH q.orgao " +
           "LEFT JOIN FETCH q.banca " +
           "LEFT JOIN FETCH q.cargo " +
           "WHERE s.id = :id")
    java.util.Optional<Simulado> findByIdComQuestoesCompletas(@Param("id") Long id);

    @Query("SELECT DISTINCT s FROM Simulado s " +
           "LEFT JOIN FETCH s.cargo c LEFT JOIN FETCH c.orgao " +
           "LEFT JOIN FETCH s.orgao " +
           "WHERE s.id = :id")
    java.util.Optional<Simulado> findByIdCompleto(@Param("id") Long id);
}
