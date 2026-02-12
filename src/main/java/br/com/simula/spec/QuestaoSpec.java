package br.com.simula.spec;

import br.com.simula.entity.Questao;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public final class QuestaoSpec {

    private QuestaoSpec() {}

    public static Specification<Questao> withFilters(Long materiaId, Long assuntoId, Long topicoId,
                                                      Long orgaoId, Long bancaId, Long cargoId, Integer ano) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (materiaId != null) {
                predicates.add(cb.equal(root.get("materia").get("id"), materiaId));
            }
            if (assuntoId != null) {
                predicates.add(cb.equal(root.get("assunto").get("id"), assuntoId));
            }
            if (topicoId != null) {
                predicates.add(cb.equal(root.get("topico").get("id"), topicoId));
            }
            if (orgaoId != null) {
                predicates.add(cb.equal(root.get("orgao").get("id"), orgaoId));
            }
            if (bancaId != null) {
                predicates.add(cb.equal(root.get("banca").get("id"), bancaId));
            }
            if (cargoId != null) {
                predicates.add(cb.equal(root.get("cargo").get("id"), cargoId));
            }
            if (ano != null) {
                predicates.add(cb.equal(root.get("ano"), ano));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
