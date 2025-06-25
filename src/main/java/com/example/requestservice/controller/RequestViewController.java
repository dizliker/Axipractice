package com.example.requestservice.controller;

import com.example.requestservice.model.RequestView;
import com.example.requestservice.service.RequestViewService;
import com.example.requestservice.spec.RequestViewSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/view")
public class RequestViewController {

    private final RequestViewService service;

    public RequestViewController(RequestViewService service) {
        this.service = service;
    }

    @GetMapping
    public Page<RequestView> getView(
            @RequestParam(required = false) String host,
            @RequestParam(required = false) String path,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @RequestParam(required = false) Integer minHeaders,
            @RequestParam(required = false) Integer minParams,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        var spec = RequestViewSpecifications.filtered(host, path, from, to, minHeaders, minParams);
        return service.getFiltered(host, path, PageRequest.of(page, size), spec);
    }
}
