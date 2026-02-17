package br.com.simula.service;

import br.com.simula.entity.Questao;
import br.com.simula.entity.Simulado;
import br.com.simula.entity.SimuladoQuestao;
import br.com.simula.repository.SimuladoQuestaoRepository;
import br.com.simula.repository.SimuladoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SimuladoService {

    private final SimuladoRepository repository;
    private final SimuladoQuestaoRepository simuladoQuestaoRepository;
    private final CargoService cargoService;
    private final OrgaoService orgaoService;
    private final QuestaoService questaoService;

    @Transactional(readOnly = true)
    public Page<Simulado> findAll(Long cargoId, Long orgaoId, Integer ano, Pageable pageable) {
        if (cargoId != null) {
            return repository.findByCargoId(cargoId, pageable);
        }
        if (orgaoId != null) {
            return repository.findByOrgaoId(orgaoId, pageable);
        }
        if (ano != null) {
            return repository.findByAno(ano, pageable);
        }
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Simulado findById(Long id) {
        return repository.findByIdWithQuestoes(id)
                .orElseThrow(() -> new RuntimeException("Simulado não encontrado com id: " + id));
    }

    @Transactional(readOnly = true)
    public Simulado findByIdCompleto(Long id) {
        Simulado simulado = repository.findByIdComConfiguracoes(id)
                .orElseThrow(() -> new RuntimeException("Simulado não encontrado com id: " + id));
        Simulado comQuestoes = repository.findByIdComQuestoesCompletas(id)
                .orElseThrow(() -> new RuntimeException("Simulado não encontrado com id: " + id));
        simulado.setSimuladosQuestoes(comQuestoes.getSimuladosQuestoes());
        return simulado;
    }

    @Transactional
    public Simulado create(Simulado simulado) {
        if (simulado.getCargo() != null && simulado.getCargo().getId() != null) {
            simulado.setCargo(cargoService.findById(simulado.getCargo().getId()));
        }
        if (simulado.getOrgao() != null && simulado.getOrgao().getId() != null) {
            simulado.setOrgao(orgaoService.findById(simulado.getOrgao().getId()));
        }
        Simulado salvo = repository.save(simulado);
        if (simulado.getSimuladosQuestoes() != null && !simulado.getSimuladosQuestoes().isEmpty()) {
            int ordem = 0;
            for (SimuladoQuestao sq : simulado.getSimuladosQuestoes()) {
                sq.setSimulado(salvo);
                sq.setQuestao(questaoService.findById(sq.getQuestao().getId()));
                sq.setOrdem(sq.getOrdem() != null ? sq.getOrdem() : ordem++);
                simuladoQuestaoRepository.save(sq);
            }
        }
        return findById(salvo.getId());
    }

    @Transactional
    public Simulado update(Long id, Simulado simuladoAtualizado) {
        Simulado existente = findById(id);
        existente.setTitulo(simuladoAtualizado.getTitulo());
        existente.setAno(simuladoAtualizado.getAno());
        existente.setDataCriacao(simuladoAtualizado.getDataCriacao());
        existente.setOrdemMaterias(simuladoAtualizado.getOrdemMaterias());
        if (simuladoAtualizado.getCargo() != null && simuladoAtualizado.getCargo().getId() != null) {
            existente.setCargo(cargoService.findById(simuladoAtualizado.getCargo().getId()));
        } else {
            existente.setCargo(null);
        }
        if (simuladoAtualizado.getOrgao() != null && simuladoAtualizado.getOrgao().getId() != null) {
            existente.setOrgao(orgaoService.findById(simuladoAtualizado.getOrgao().getId()));
        } else {
            existente.setOrgao(null);
        }
        if (simuladoAtualizado.getSimuladosQuestoes() != null) {
            simuladoQuestaoRepository.deleteBySimuladoId(id);
            int ordem = 0;
            for (SimuladoQuestao sq : simuladoAtualizado.getSimuladosQuestoes()) {
                SimuladoQuestao nova = new SimuladoQuestao();
                nova.setSimulado(existente);
                nova.setQuestao(questaoService.findById(sq.getQuestao().getId()));
                nova.setOrdem(sq.getOrdem() != null ? sq.getOrdem() : ordem++);
                simuladoQuestaoRepository.save(nova);
            }
        }
        repository.save(existente);
        return findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Simulado não encontrado com id: " + id);
        }
        simuladoQuestaoRepository.deleteBySimuladoId(id);
        repository.deleteById(id);
    }
}
