package br.com.specification.common;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationBuilder {

    private List<Predicate> predicate = new ArrayList<>();

    public SpecificationBuilder() {
    }

    public Specification<?> build(List<SearchCriteria> criteriaList){
        return (root, query, criteriaBuilder) -> {
            criteriaList.forEach(criteria -> this.predicate.add(toPredicate(criteria, criteriaBuilder, root)));

            return criteriaBuilder.and(this.predicate.toArray(new Predicate[0]));
        };
    }

    public Predicate toPredicate(SearchCriteria criteria, CriteriaBuilder criteriaBuilder, Root root) {
        return switch (criteria.getOperation()) {
            case EQUALS -> criteriaBuilder.and(criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue()));
            case NEGATE -> criteriaBuilder.and(criteriaBuilder.notEqual(root.get(criteria.getKey()), criteria.getValue()));
            case GREATER_THAN -> criteriaBuilder.and(criteriaBuilder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
            case LESS_THAN -> criteriaBuilder.and(criteriaBuilder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
            case STARTS_WITH -> criteriaBuilder.and(criteriaBuilder.like(root.get(criteria.getKey()), criteria.getValue() + "%"));
            case CONTAINS -> criteriaBuilder.and(criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%"));
        };
    }

}