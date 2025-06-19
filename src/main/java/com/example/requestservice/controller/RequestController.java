package com.example.requestservice.controller;

import com.example.requestservice.dto.RequestInputDTO;
import com.example.requestservice.model.Request;
import com.example.requestservice.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/request")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    // POST /api/request/string
    @PostMapping("/string")
    public ResponseEntity<Request> handleString(@RequestBody String url) {
        Request savedRequest = requestService.processUrlInput(url);
        return ResponseEntity.ok(savedRequest);
    }

    // POST /api/request/json
    @PostMapping("/json")
    public ResponseEntity<Request> handleJson(@RequestBody RequestInputDTO dto) {
        Request savedRequest = requestService.processJsonInput(dto);
        return ResponseEntity.ok(savedRequest);
    }
}
