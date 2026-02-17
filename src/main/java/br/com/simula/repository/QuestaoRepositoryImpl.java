package br.com.simula.repository;

import br.com.simula.entity.Questao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestaoRepositoryImpl implements QuestaoRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public Page<Questao> findAllWithAssociations(Specification<Questao> spec, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Questao> query = cb.createQuery(Questao.class);
        Root<Questao> root = query.from(Questao.class);

        root.fetch("tipo", JoinType.LEFT);
        root.fetch("materia", JoinType.LEFT);
        root.fetch("assunto", JoinType.LEFT).fetch("materia", JoinType.LEFT);
        root.fetch("topico", JoinType.LEFT).fetch("assunto", JoinType.LEFT).fetch("materia", JoinType.LEFT);
        root.fetch("orgao", JoinType.LEFT);
        root.fetch("banca", JoinType.LEFT);
        root.fetch("cargo", JoinType.LEFT);

        if (spec != null) {
            query.where(spec.toPredicate(root, query, cb));
        }
        query.distinct(true);
        if (pageable.getSort().isSorted()) {
            query.orderBy(QueryUtils.toOrders(pageable.getSort(), root, cb));
        }

        TypedQuery<Questao> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());
        List<Questao> content = typedQuery.getResultList();

        long total = count(spec);
        return new PageImpl<>(content, pageable, total);
    }

    private long count(Specification<Questao> spec) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Questao> root = query.from(Questao.class);
        query.select(cb.count(root));
        if (spec != null) {
            query.where(spec.toPredicate(root, query, cb));
        }
        return entityManager.createQuery(query).getSingleResult();
    }
}
