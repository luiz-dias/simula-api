package br.com.simula.repository;

import br.com.simula.entity.Questao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface QuestaoRepositoryCustom {

    Page<Questao> findAllWithAssociations(Specification<Questao> spec, Pageable pageable);
}
