package ru.rushydro.vniig.ias.dao.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.rushydro.vniig.ias.StringUtils;
import ru.rushydro.vniig.ias.dao.entity.Log;
import ru.rushydro.vniig.ias.dao.entity.TaskLog;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yazik on 21.08.2017.
 */
public class LogSpecification implements Specification<Log> {

    private String startDate;

    private String endDate;

    private String type;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public LogSpecification(String startDate, String endDate, String type) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

    @Override
    public Predicate toPredicate(Root<Log> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotEmpty(type)) {
            predicates.add(criteriaBuilder.equal(root.get("type"), type));
        }
        if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
            predicates.add(criteriaBuilder.between(root.get("date"),
                    LocalDate.parse(startDate, formatter).atStartOfDay(),
                    LocalDate.parse(endDate, formatter).atTime(23,59, 59)));
        } else if (StringUtils.isNotEmpty(startDate)) {
            predicates.add(criteriaBuilder.greaterThan(root.get("date"),
                    LocalDate.parse(startDate, formatter).atStartOfDay()));
        } else if (StringUtils.isNotEmpty(endDate)) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("date"),
                    LocalDate.parse(endDate, formatter).atTime(23,59, 59)));
        }

        if (predicates.isEmpty()) {
            return null;
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
