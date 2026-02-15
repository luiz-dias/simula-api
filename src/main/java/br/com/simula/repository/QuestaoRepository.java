package br.com.simula.repository;

import br.com.simula.entity.Questao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long>, JpaSpecificationExecutor<Questao> {

    @Query("SELECT q FROM Questao q " +
           "LEFT JOIN FETCH q.tipo " +
           "LEFT JOIN FETCH q.materia " +
           "LEFT JOIN FETCH q.assunto " +
           "LEFT JOIN FETCH q.topico " +
           "LEFT JOIN FETCH q.orgao " +
           "LEFT JOIN FETCH q.banca " +
           "LEFT JOIN FETCH q.cargo " +
           "WHERE q.id = :id")
    java.util.Optional<Questao> findByIdWithAssociations(@Param("id") Long id);

    @Query("SELECT q.id FROM Questao q WHERE q.materia.id = :materiaId")
    java.util.List<Long> findIdsByMateriaId(@Param("materiaId") Long materiaId);

    void deleteByMateriaId(Long materiaId);
}
