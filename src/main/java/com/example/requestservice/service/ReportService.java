package com.example.requestservice.service;

import com.example.requestservice.dto.ReportRequestDTO;
import com.example.requestservice.model.Report;
import com.example.requestservice.model.RequestView;
import com.example.requestservice.repo.ReportRepository;
import com.example.requestservice.repo.RequestViewRepository;
import com.example.requestservice.spec.RequestViewSpecifications;
import com.example.requestservice.util.CsvUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class ReportService {

    private static final Logger log = LoggerFactory.getLogger(ReportService.class);

    private final ReportRepository reportRepo;
    private final RequestViewService viewService;
    private final String reportsDir;

    public ReportService(
            ReportRepository reportRepo,
            RequestViewService viewService,
            @Value("${reports.dir:src/main/resources/reports}") String reportsDir
    ) {
        this.reportRepo = reportRepo;
        this.viewService = viewService;
        this.reportsDir = reportsDir;

        File dir = new File(reportsDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public Long createReport(ReportRequestDTO dto) {
        Report r = new Report();
        r.setHost(dto.getHost());
        r.setPath(dto.getPath());
        r.setStartDate(dto.getStartDate());
        r.setEndDate(dto.getEndDate());
        r.setMinHeaders(dto.getMinHeaders());
        r.setMinParams(dto.getMinParams());
        r.setDone(false);
        r.setCreatedAt(LocalDateTime.now());

        r = reportRepo.save(r); // Сохраняем до запуска async

        try {
            generateReportAsync(r);
        } catch (Exception e) {
            r.setErrorMessage("Ошибка при запуске генерации отчета: " + e.getMessage());
            reportRepo.save(r);
            log.info("Creating report with filters: host={}, path={}, startDate={}, endDate={}, minHeaders={}, minParams={}",
                    dto.getHost(), dto.getPath(), dto.getStartDate(), dto.getEndDate(), dto.getMinHeaders(), dto.getMinParams());

            throw new RuntimeException("Не удалось создать отчет. Статус смотри через /api/report/" + r.getId());
        }

        return r.getId();
    }




    @Async
    public void generateReportAsync(Report report) {
        try {
            Specification<RequestView> spec = RequestViewSpecifications.filtered(
                    report.getHost(),
                    report.getPath(),
                    report.getStartDate(),
                    report.getEndDate(),
                    report.getMinHeaders(),
                    report.getMinParams()
            );

            PageRequest pageable = PageRequest.of(0, Integer.MAX_VALUE);

            List<RequestView> data = viewService.getFiltered(
                    report.getHost(),
                    report.getPath(),
                    pageable,
                    spec
            ).getContent();

            String csv = reportsDir + File.separator + "report_" + report.getId() + ".csv";
            CsvUtil.writeCsv(csv, data);

            report.setCsvPath(csv);
            report.setDone(true);
            report.setErrorMessage(null);
            reportRepo.save(report);

        } catch (Exception e) {
            report.setErrorMessage("Ошибка генерации отчета: " + e.getMessage());
            report.setDone(true);
            reportRepo.save(report);
            log.error("Ошибка генерации отчета", e);
        }
    }



    public List<Report> getAll() {
        return reportRepo.findAll();
    }

    public Page<RequestView> getPagedData(Long id, PageRequest pr) {
        Report r = reportRepo.findById(id).orElseThrow();

        Specification<RequestView> spec = RequestViewSpecifications.filtered(
                r.getHost(),
                r.getPath(),
                r.getStartDate(),
                r.getEndDate(),
                r.getMinHeaders(),
                r.getMinParams()
        );

        return viewService.getFiltered(
                r.getHost(),
                r.getPath(),
                pr,
                spec
        );
    }


    public Resource getCsvResource(Long id) {
        Optional<Report> opt = reportRepo.findById(id);
        if (opt.isEmpty() || opt.get().getCsvPath() == null) {
            return null;
        }
        return new FileSystemResource(opt.get().getCsvPath());
    }

    public Optional<Report> getById(Long id) {
        return reportRepo.findById(id);
    }

}
