package com.example.requestservice.spec;

import com.example.requestservice.model.RequestView;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class RequestViewSpecifications {

    public static Specification<RequestView> filtered(
            String host,
            String path,
            LocalDateTime from,
            LocalDateTime to,
            Integer minHeaders,
            Integer minParams
    ) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (host != null && !host.isEmpty()) {
                predicates = cb.and(predicates, cb.equal(root.get("host"), host));
            }
            if (path != null && !path.isEmpty()) {
                predicates = cb.and(predicates, cb.equal(root.get("path"), path));
            }
            if (from != null) {
                predicates = cb.and(predicates, cb.greaterThanOrEqualTo(root.get("timestamp"), from));
            }
            if (to != null) {
                predicates = cb.and(predicates, cb.lessThanOrEqualTo(root.get("timestamp"), to));
            }
            if (minHeaders != null) {
                predicates = cb.and(predicates, cb.greaterThanOrEqualTo(root.get("avgHeaders"), minHeaders.doubleValue()));
            }
            if (minParams != null) {
                predicates = cb.and(predicates, cb.greaterThanOrEqualTo(root.get("avgParams"), minParams.doubleValue()));
            }
            return predicates;
        };
    }
}
