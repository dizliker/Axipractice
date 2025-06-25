package com.example.requestservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Entity
@Immutable
@Table(name = "request_view")
public class RequestView {

    @Id
    private Long id;

    private String host;

    private String path;

    private LocalDateTime timestamp;

    @Column(name = "avg_headers")
    private Double avgHeaders;  // изменено на Double

    @Column(name = "avg_params")
    private Double avgParams;   // изменено на Double

    public Long getId() {
        return id;
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Double getAvgHeaders() {
        return avgHeaders;
    }

    public Double getAvgParams() {
        return avgParams;
    }
}
