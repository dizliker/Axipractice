package com.example.requestservice.service;

import com.example.requestservice.model.RequestView;
import com.example.requestservice.repo.RequestViewRepository;
import com.example.requestservice.spec.RequestViewSpecifications;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RequestViewService {

    private final RequestViewRepository repo;

    public RequestViewService(RequestViewRepository repo) {
        this.repo = repo;
    }

    public Page<RequestView> getFiltered(
            String host,
            String path,
            LocalDateTime from,
            LocalDateTime to,
            Integer minHeaders,
            Integer minParams,
            int page,
            int size
    ) {

            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            var spec = RequestViewSpecifications.filtered(host, path, from, to, minHeaders, minParams);

            return repo.findAll(spec, pageable);


    }
}
