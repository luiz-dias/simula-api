package br.com.simula.repository;

import br.com.simula.entity.SimuladoConfiguracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimuladoConfiguracaoRepository extends JpaRepository<SimuladoConfiguracao, Long> {

    List<SimuladoConfiguracao> findBySimuladoId(Long simuladoId);

    void deleteBySimuladoId(Long simuladoId);
}
