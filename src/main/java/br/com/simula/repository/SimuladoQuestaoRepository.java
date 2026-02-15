package br.com.simula.repository;

import br.com.simula.entity.SimuladoQuestao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimuladoQuestaoRepository extends JpaRepository<SimuladoQuestao, Long> {

    List<SimuladoQuestao> findBySimuladoIdOrderByOrdemAsc(Long simuladoId);

    void deleteBySimuladoId(Long simuladoId);

    void deleteByQuestaoIdIn(List<Long> questaoIds);
}
