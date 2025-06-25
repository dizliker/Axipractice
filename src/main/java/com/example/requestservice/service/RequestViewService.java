package com.example.requestservice.service;

import com.example.requestservice.model.RequestView;
import com.example.requestservice.repo.RequestViewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class RequestViewService {

    private final RequestViewRepository repo;

    public RequestViewService(RequestViewRepository repo) {
        this.repo = repo;
    }

    public Page<RequestView> getFiltered(
            String host,
            String path,
            Pageable pageable,
            Specification<RequestView> spec
    ) {
        Specification<RequestView> baseSpec = Specification.where(null);

        if (host != null && !host.isEmpty()) {
            baseSpec = baseSpec.and((root, query, cb) -> cb.equal(root.get("host"), host));
        }
        if (path != null && !path.isEmpty()) {
            baseSpec = baseSpec.and((root, query, cb) -> cb.equal(root.get("path"), path));
        }

        if (spec != null) {
            baseSpec = baseSpec.and(spec);
        }

        return repo.findAll(baseSpec, pageable);
    }
}
