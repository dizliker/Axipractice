package com.example.requestservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String host;
    private String path;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "min_headers")
    private Integer minHeaders;

    @Column(name = "min_params")
    private Integer minParams;

    private boolean done = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "csv_path")
    private String csvPath;

    // Геттеры и сеттеры
    public Long getId() { return id; }

    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public Integer getMinHeaders() { return minHeaders; }
    public void setMinHeaders(Integer minHeaders) { this.minHeaders = minHeaders; }

    public Integer getMinParams() { return minParams; }
    public void setMinParams(Integer minParams) { this.minParams = minParams; }

    public boolean isDone() { return done; }
    public void setDone(boolean done) { this.done = done; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getCsvPath() { return csvPath; }
    public void setCsvPath(String csvPath) { this.csvPath = csvPath; }
}