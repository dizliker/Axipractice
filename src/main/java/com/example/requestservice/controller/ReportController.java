package com.example.requestservice.controller;

import com.example.requestservice.dto.ReportCreationResponseDTO;
import com.example.requestservice.dto.ReportRequestDTO;
import com.example.requestservice.model.Report;
import com.example.requestservice.model.RequestView;
import com.example.requestservice.service.ReportService;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // Создание отчёта
    @PostMapping
    public ResponseEntity<ReportCreationResponseDTO> create(@RequestBody ReportRequestDTO dto) {
        Long id = reportService.createReport(dto);
        ReportCreationResponseDTO response = new ReportCreationResponseDTO(id, "Report creation started");
        return ResponseEntity.ok(response);
    }

    // Список отчётов
    @GetMapping
    public List<Report> list() {
        return reportService.getAll();
    }

    // Данные отчёта постранично
    @GetMapping("/{id}/data")
    public Page<RequestView> data(@PathVariable Long id,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        return reportService.getPagedData(id, PageRequest.of(page, size));
    }

    // Скачать CSV
    @GetMapping("/{id}/csv")
    public ResponseEntity<Resource> csv(@PathVariable Long id) {
        Resource res = reportService.getCsvResource(id);
        if (res == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report_" + id + ".csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(res);
    }
}