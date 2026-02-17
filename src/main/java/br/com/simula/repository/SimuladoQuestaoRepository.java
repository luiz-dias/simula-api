package br.com.simula.repository;

import br.com.simula.entity.SimuladoQuestao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimuladoQuestaoRepository extends JpaRepository<SimuladoQuestao, Long> {

    List<SimuladoQuestao> findBySimuladoIdOrderByOrdemAsc(Long simuladoId);

    void deleteBySimuladoId(Long simuladoId);

    void deleteByQuestaoIdIn(List<Long> questaoIds);

    @Query("SELECT sq.questao.id FROM SimuladoQuestao sq " +
           "WHERE sq.simulado.orgao.id = :orgaoId AND sq.simulado.ano = :ano")
    List<Long> findQuestaoIdsUsadasByOrgaoAndAno(@Param("orgaoId") Long orgaoId,
                                                  @Param("ano") Integer ano);
}
