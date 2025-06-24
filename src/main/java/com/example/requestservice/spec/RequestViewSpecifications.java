package com.example.requestservice.spec;

import com.example.requestservice.model.RequestView;
import jakarta.persistence.criteria.Predicate;
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
            Predicate predicate = cb.conjunction();

            if (host != null && !host.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("host"), host));
            }

            if (path != null && !path.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("path"), path));
            }

            if (from != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("timestamp"), from));
            }

            if (to != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("timestamp"), to));
            }

            if (minHeaders != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("avgHeaders"), minHeaders));
            }

            if (minParams != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("avgParams"), minParams));
            }

            return predicate;
        };
    }
}
